package com.bettercode.connect.engine.template;

import com.bettercode.connect.engine.ExcelRowMapper;
import com.bettercode.connect.engine.handler.AccountSheetContentHandlerBuilder;
import com.bettercode.connect.engine.mapper.AccountRecord;
import com.bettercode.connect.entity.WorkExcelFile;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.exceptions.NotOfficeXmlFileException;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.openxml4j.opc.PackageAccess;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.util.SAXHelper;
import org.apache.poi.xssf.eventusermodel.ReadOnlySharedStringsTable;
import org.apache.poi.xssf.eventusermodel.XSSFReader;
import org.apache.poi.xssf.eventusermodel.XSSFSheetXMLHandler;
import org.apache.poi.xssf.model.StylesTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.ContentHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;

public class AccountExcelTemplate {
    private final static Logger logger = LoggerFactory.getLogger(AccountExcelTemplate.class);

    public AccountRecord getRows(WorkExcelFile uploadExcelFile, ExcelRowMapper excelRowMapper) throws IOException {
        AccountSheetContentHandlerBuilder sheetContentHandlerBuilder = new AccountSheetContentHandlerBuilder(new AccountRecord());

        try {
            try (OPCPackage opcPackage = OPCPackage.open(uploadExcelFile.getOriginalFile().getPath(), PackageAccess.READ)) {
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
                            styles, null, strings,
                            sheetContentHandlerBuilder.create(excelRowMapper),
                            formatter, false);
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
            if(uploadExcelFile.getOriginalFile() != null) {
                uploadExcelFile.getOriginalFile().deleteOnExit();
            }
        }

        return sheetContentHandlerBuilder.getBettercodeConnectAccount();
    }
}