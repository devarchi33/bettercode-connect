package com.bettercode.connect.engine.mapper;

import com.bettercode.connect.engine.AbstractExcelItemProcessor;
import com.bettercode.connect.engine.ExcelRowMapper;
import org.springframework.stereotype.Component;

@Component
public class BettercodeConnectStockExcelItemProcessor extends AbstractExcelItemProcessor implements ExcelRowMapper<BettercodeConnectStock> {
    @Override
    public BettercodeConnectStock create() {
        return new BettercodeConnectStock();
    }

    @Override
    public void mapRow(BettercodeConnectStock bettercodeConnectStock, int rowIndex, int columnIndex, String cellValue) {
        switch (columnIndex) {
            case 0 :
                bettercodeConnectStock.setSkuCode(getStringValueFromCell(cellValue, rowIndex, columnIndex).toUpperCase());
                break;
            case 1 :
                bettercodeConnectStock.setStyleCode(getStringValueFromCell(cellValue, rowIndex, columnIndex).toUpperCase());
                break;
            case 2 :
                bettercodeConnectStock.setStyleName(getStringValueFromCell(cellValue, rowIndex, columnIndex).toUpperCase());
                break;
            case 3 :
                bettercodeConnectStock.setQty(getIntegerValueFromCell(cellValue, rowIndex, columnIndex));
                break;
        }
    }
}
