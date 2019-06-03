package com.bettercode.connect.engine.handler;

import com.bettercode.connect.engine.ExcelRowMapper;
import com.bettercode.connect.engine.mapper.AccountRecord;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.eventusermodel.XSSFSheetXMLHandler;
import org.apache.poi.xssf.usermodel.XSSFComment;

public class AccountSheetContentHandlerBuilder {

  private AccountRecord bettercodeConnectAccount;

  public AccountSheetContentHandlerBuilder(AccountRecord bettercodeConnectAccount) {
    this.bettercodeConnectAccount = bettercodeConnectAccount;
  }

  public AccountRecord getBettercodeConnectAccount() {
    return bettercodeConnectAccount;
  }

  public XSSFSheetXMLHandler.SheetContentsHandler create(ExcelRowMapper excelRowMapper) {
    return new XSSFSheetXMLHandler.SheetContentsHandler() {
      private int currentRowIndex = -1;
      private int currentColumnIndex = -1;
      private boolean isFirstRecord = true;
      private int row = 0;
      private Object object = null;

      @Override
      public void startRow(int rowNum) {
        if (rowNum < 7) {
          isFirstRecord = true;
          row++;
        } else {
          isFirstRecord = false;
          object = excelRowMapper.create();
        }
        currentRowIndex = rowNum;
        currentColumnIndex = -1;
      }

      @Override
      public void endRow(int rowNum) {
        if(this.object != null) {
          bettercodeConnectAccount.addBettercodeConnectAccountRecord(object);
        }
      }

      @Override
      public void cell(String cellReference, String formattedValue, XSSFComment xssfComment) {
        if (row == 1 && cellReference.equalsIgnoreCase("A1")) {
          bettercodeConnectAccount.setAccountNo(formattedValue);
          System.out.println("A1_FORMATTED_VALUE: " + formattedValue);
        } else if (row == 1 && cellReference.equalsIgnoreCase("F1")) {
          bettercodeConnectAccount.setFirstLoanDate(formattedValue);
          System.out.println("F1_FORMATTED_VALUE: " + formattedValue);
        } else if (row == 2 && cellReference.equalsIgnoreCase("A2")) {
          bettercodeConnectAccount.setTotalBalance(formattedValue);
          System.out.println("A2_FORMATTED_VALUE: " + formattedValue);
        } else if (row == 2 && cellReference.equalsIgnoreCase("F2")) {
          bettercodeConnectAccount.setLoanMaturityDate(formattedValue);
          System.out.println("F2_FORMATTED_VALUE: " + formattedValue);
        } else if (row == 3 && cellReference.equalsIgnoreCase("A3")) {
          bettercodeConnectAccount.setAccountName(formattedValue);
          System.out.println("A3_FORMATTED_VALUE: " + formattedValue);
        } else if (row == 3 && cellReference.equalsIgnoreCase("F3")) {
          bettercodeConnectAccount.setLoanLimit(formattedValue);
          System.out.println("F3_FORMATTED_VALUE: " + formattedValue);
        } else if (row == 4 && cellReference.equalsIgnoreCase("A4")) {
          bettercodeConnectAccount.setAllowanceAmount(formattedValue);
          System.out.println("A4_FORMATTED_VALUE: " + formattedValue);
        } else if (row == 4 && cellReference.equalsIgnoreCase("F4")) {
          bettercodeConnectAccount.setLoanInterestRate(formattedValue);
          System.out.println("F4_FORMATTED_VALUE: " + formattedValue);
        } else if (row == 5 && cellReference.equalsIgnoreCase("A5")) {
          bettercodeConnectAccount.setWidthdrawalSum(formattedValue);
          System.out.println("A5_FORMATTED_VALUE: " + formattedValue);
        } else if (row == 5 && cellReference.equalsIgnoreCase("F5")) {
          bettercodeConnectAccount.setDepositSum(formattedValue);
          System.out.println("F5_FORMATTED_VALUE: " + formattedValue);
        } else if (row == 6 && cellReference.equalsIgnoreCase("A6")) {
          bettercodeConnectAccount.setCheckPeriod(formattedValue);
          System.out.println("A6_FORMATTED_VALUE: " + formattedValue);
        }
        if(!isFirstRecord) {
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
