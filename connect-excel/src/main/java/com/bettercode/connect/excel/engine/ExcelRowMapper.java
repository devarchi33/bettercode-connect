package com.bettercode.connect.excel.engine;

public interface ExcelRowMapper<T> {
    T create();
    void mapRow(T obj, int rowIndex, int columnIndex, String cellValue);
}
