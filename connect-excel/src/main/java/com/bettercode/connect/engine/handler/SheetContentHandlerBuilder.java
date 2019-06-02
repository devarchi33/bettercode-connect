package com.bettercode.connect.engine.handler;

import com.bettercode.connect.engine.ExcelRowMapper;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.eventusermodel.XSSFSheetXMLHandler;
import org.apache.poi.xssf.usermodel.XSSFComment;

import java.util.List;

public class SheetContentHandlerBuilder {

  private List<Object> excelDataList;

  public SheetContentHandlerBuilder(List<Object> excelDataList) {
    this.excelDataList = excelDataList;
  }

  public List<Object> getExcelDataList() {
    return excelDataList;
  }

  public XSSFSheetXMLHandler.SheetContentsHandler create(ExcelRowMapper excelRowMapper) {
    return new XSSFSheetXMLHandler.SheetContentsHandler() {
      private int currentRowIndex = -1;
      private int currentColumnIndex = -1;
      private boolean firstRow = true;

      private Object object = null;

      @Override
      public void startRow(int rowNum) {
        if(!excelRowMapper.toString().contains("BettercodeConnectAccount")) {
          if (rowNum == 0) {
            firstRow = true;
          } else {
            firstRow = false;
            object = excelRowMapper.create();
          }
          currentRowIndex = rowNum;
          currentColumnIndex = -1;
        } else {
          if (rowNum < 7) {
            firstRow = true;
          } else {
            firstRow = false;
            object = excelRowMapper.create();
          }
          currentRowIndex = rowNum;
          currentColumnIndex = -1;
        }
      }

      @Override
      public void endRow(int rowNum) {
        if(this.object != null) {
          excelDataList.add(object);
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
    };
  }
}
