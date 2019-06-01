package com.bettercode.connect.engine;

public interface ExcelRowMapper<T> {
    T create();
    void mapRow(T obj, int rowIndex, int columnIndex, String cellValue);
}
