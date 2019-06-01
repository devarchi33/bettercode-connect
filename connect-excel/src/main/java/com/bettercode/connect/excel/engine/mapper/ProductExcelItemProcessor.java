package com.bettercode.connect.excel.engine.mapper;

import com.bettercode.connect.excel.engine.AbstractExcelItemProcessor;
import com.bettercode.connect.excel.engine.ExcelRowMapper;
import org.springframework.stereotype.Component;

@Component
public class ProductExcelItemProcessor extends AbstractExcelItemProcessor implements ExcelRowMapper<Product> {
    @Override
    public Product create() {
        return new Product();
    }

    @Override
    public void mapRow(Product product, int rowIndex, int columnIndex, String cellValue) {
        switch (columnIndex) {
            case 0 :
                product.setId(getLongValueFromCell(cellValue, rowIndex, columnIndex));
                break;
            case 1 :
                product.setName(getStringValueFromCell(cellValue, rowIndex, columnIndex).toUpperCase());
                break;
            case 2 :
                product.setPrice(getDoubleValueFromCell(cellValue, rowIndex, columnIndex));
                break;
        }
    }
}
