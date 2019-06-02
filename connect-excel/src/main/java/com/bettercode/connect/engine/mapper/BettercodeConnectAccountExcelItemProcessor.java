package com.bettercode.connect.engine.mapper;

import com.bettercode.connect.engine.AbstractExcelItemProcessor;
import com.bettercode.connect.engine.ExcelRowMapper;
import org.springframework.stereotype.Component;

@Component
public class BettercodeConnectAccountExcelItemProcessor extends AbstractExcelItemProcessor implements ExcelRowMapper<BettercodeConnectAccount> {
    @Override
    public BettercodeConnectAccount create() {
        return new BettercodeConnectAccount();
    }

    @Override
    public void mapRow(BettercodeConnectAccount bettercodeConnectAccount, int rowIndex, int columnIndex, String cellValue) {
        switch (columnIndex) {
            case 0 :
                bettercodeConnectAccount.setId(getStringValueFromCell(cellValue, rowIndex, columnIndex));
                break;
            case 1 :
                bettercodeConnectAccount.setTransactionTime(getStringValueFromCell(cellValue, rowIndex, columnIndex));
                break;
            case 2 :
                bettercodeConnectAccount.setReason(getStringValueFromCell(cellValue, rowIndex, columnIndex).toUpperCase());
                break;
            case 3 :
                bettercodeConnectAccount.setRecipient(getStringValueFromCell(cellValue, rowIndex, columnIndex));
                break;
            case 4:
                bettercodeConnectAccount.setForwardingAmount(getStringValueFromCell(cellValue, rowIndex, columnIndex));
            case 5:
                bettercodeConnectAccount.setReceivedAmount(getStringValueFromCell(cellValue, rowIndex, columnIndex));
            case 6:
                bettercodeConnectAccount.setBalance(getStringValueFromCell(cellValue, rowIndex, columnIndex));
            case 7:
                bettercodeConnectAccount.setMemo(getStringValueFromCell(cellValue, rowIndex, columnIndex));
            case 8:
                bettercodeConnectAccount.setProcessingPoint(getStringValueFromCell(cellValue, rowIndex, columnIndex));
            case 9:
                bettercodeConnectAccount.setSortation(getStringValueFromCell(cellValue, rowIndex, columnIndex));
        }
    }
}
