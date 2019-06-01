package com.bettercode.connect.engine.mapper;

import com.bettercode.connect.engine.AbstractExcelItemProcessor;
import com.bettercode.connect.engine.ExcelRowMapper;
import org.springframework.stereotype.Component;

@Component
public class BarrelOmniStocklItemProcessor extends AbstractExcelItemProcessor implements ExcelRowMapper<BarrelOmniStock> {
    @Override
    public BarrelOmniStock create() {
        return new BarrelOmniStock();
    }

    @Override
    public void mapRow(BarrelOmniStock barrelOmniStock, int rowIndex, int columnIndex, String cellValue) {
        switch (columnIndex) {
            case 0 :
                barrelOmniStock.setSkuCode(getStringValueFromCell(cellValue, rowIndex, columnIndex).toUpperCase());
                break;
            case 1 :
                barrelOmniStock.setStyleCode(getStringValueFromCell(cellValue, rowIndex, columnIndex).toUpperCase());
                break;
            case 2 :
                barrelOmniStock.setStyleName(getStringValueFromCell(cellValue, rowIndex, columnIndex).toUpperCase());
                break;
            case 3 :
                barrelOmniStock.setQty(getIntegerValueFromCell(cellValue, rowIndex, columnIndex));
                break;
        }
    }
}
