package com.bettercode.connect.engine;

import com.bettercode.connect.entity.ExcelFile;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.exceptions.NotOfficeXmlFileException;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.openxml4j.opc.PackageAccess;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.util.SAXHelper;
import org.apache.poi.xssf.eventusermodel.ReadOnlySharedStringsTable;
import org.apache.poi.xssf.eventusermodel.XSSFReader;
import org.apache.poi.xssf.eventusermodel.XSSFSheetXMLHandler;
import org.apache.poi.xssf.model.StylesTable;
import org.apache.poi.xssf.usermodel.XSSFComment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.ContentHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ExcelTemplate {
    private final static Logger logger = LoggerFactory.getLogger(ExcelTemplate.class);

    public List<Object> getRows(ExcelFile uploadExcelFile, ExcelRowMapper excelRowMapper) {
        List<Object> objectList = new ArrayList<>();
        File tempFile = null;
        try {
            tempFile = uploadExcelFile.getOriginalFile();

            try (OPCPackage opcPackage = OPCPackage.open(tempFile.getPath(), PackageAccess.READ)) {
                ReadOnlySharedStringsTable strings = new ReadOnlySharedStringsTable(opcPackage);
                XSSFReader xssfReader = new XSSFReader(opcPackage);
                StylesTable styles = xssfReader.getStylesTable();
                XSSFReader.SheetIterator iter = (XSSFReader.SheetIterator) xssfReader.getSheetsData();

                try (InputStream stream = iter.next()) {
                    DataFormatter formatter = new DataFormatter();
                    InputSource sheetSource = new InputSource(stream);
                    try {
                        XMLReader sheetParser = SAXHelper.newXMLReader();
                        ContentHandler handler = new XSSFSheetXMLHandler(
                            styles, null, strings, new XSSFSheetXMLHandler.SheetContentsHandler() {
                            private int currentRowIndex = -1;
                            private int currentColumnIndex = -1;
                            private boolean firstRow = true;

                            private Object object = null;

                            @Override
                            public void startRow(int rowNum) {
                                if(rowNum == 0) {
                                    firstRow = true;
                                } else {
                                    firstRow = false;
                                    object = excelRowMapper.create();
                                }
                                currentRowIndex = rowNum;
                                currentColumnIndex = -1;
                            }

                            @Override
                            public void endRow(int rowNum) {
                                if(this.object != null) {
                                    objectList.add(object);
                                }
                            }

                            @Override
                            public void cell(String cellReference, String formattedValue, XSSFComment xssfComment) {
                                if(!firstRow) {
                                    // Did we miss any cells?
                                    int thisColumnIndex = (new CellReference(cellReference)).getCol();
                                    int missedCols = thisColumnIndex - currentColumnIndex - 1;
                                    for (int i = 1; i <= missedCols; i++) {
                                        excelRowMapper.mapRow(this.object, currentRowIndex, (currentColumnIndex + i), null);
                                    }

                                    currentColumnIndex = thisColumnIndex;
                                    excelRowMapper.mapRow(this.object, currentRowIndex, currentColumnIndex, formattedValue);
                                }
                            }

                            @Override
                            public void headerFooter(String s, boolean b, String s1) {
                            }
                        }, formatter, false);

                        sheetParser.setContentHandler(handler);
                        sheetParser.parse(sheetSource);
                    } catch(ParserConfigurationException e) {
                        logger.error("excel file parsing error", e);
                        throw new RuntimeException(e);
                    }
                }
            } catch (InvalidFormatException e) {
                throw new IllegalArgumentException("The file only supports Excel.");
            } catch (OpenXML4JException | SAXException e) {
                logger.error("excel file parsing error", e);
                throw new RuntimeException(e);
            }
        } catch (NotOfficeXmlFileException noxfe) {
            throw new IllegalArgumentException("The file only supports Excel.");
        } catch (IOException e) {
            logger.error("excel file parsing error", e);
            throw new RuntimeException(e);
        } finally {
            if(tempFile != null) {
                tempFile.deleteOnExit();
            }
        }

        return objectList;
    }
}