package com.bettercode.connect.engine;

import com.bettercode.connect.exception.IllegalExcelException;

public abstract class AbstractExcelItemProcessor {
    protected long getLongValueFromCell(String value, int rowIndex, int columnIndex) {
        validateValue(value, rowIndex, columnIndex);

        try {
            return Long.valueOf(value).longValue();
        } catch (NumberFormatException e) {
            IllegalExcelException illegalExcelException= new IllegalExcelException(IllegalExcelException.ExcelErrorCode.VALUE_NUMBER_ONLY_ALLOWED);
            illegalExcelException.addErrorMessage("row", String.valueOf(rowIndex + 1));
            illegalExcelException.addErrorMessage("column", String.valueOf(columnIndex + 1));

            throw illegalExcelException;
        }
    }

    protected double getDoubleValueFromCell(String value, int rowIndex, int columnIndex) {
        validateValue(value, rowIndex, columnIndex);

        try {
            return Double.valueOf(value).doubleValue();
        } catch (NumberFormatException e) {
            IllegalExcelException illegalExcelException= new IllegalExcelException(IllegalExcelException.ExcelErrorCode.VALUE_NUMBER_ONLY_ALLOWED);
            illegalExcelException.addErrorMessage("row", String.valueOf(rowIndex + 1));
            illegalExcelException.addErrorMessage("column", String.valueOf(columnIndex + 1));

            throw illegalExcelException;
        }
    }

    protected int getIntegerValueFromCell(String value, int rowIndex, int columnIndex) {
        validateValue(value, rowIndex, columnIndex);

        try {
            return Integer.valueOf(value).intValue();
        } catch (NumberFormatException e) {
            IllegalExcelException illegalExcelException= new IllegalExcelException(IllegalExcelException.ExcelErrorCode.VALUE_NUMBER_ONLY_ALLOWED);
            illegalExcelException.addErrorMessage("row", String.valueOf(rowIndex + 1));
            illegalExcelException.addErrorMessage("column", String.valueOf(columnIndex + 1));

            throw illegalExcelException;
        }
    }

    protected String getStringValueFromCell(String value, int rowIndex, int columnIndex) {
        validateValue(value, rowIndex, columnIndex);
        return value.trim();
    }

    private void validateValue(String value, int rowIndex, int columnIndex) {
        if(value == null || value.trim().isEmpty()) {
            IllegalExcelException illegalExcelException= new IllegalExcelException(IllegalExcelException.ExcelErrorCode.VALUE_REQUIRED);
            illegalExcelException.addErrorMessage("row", String.valueOf(rowIndex + 1));
            illegalExcelException.addErrorMessage("column", String.valueOf(columnIndex + 1));

            throw illegalExcelException;
        }
    }
}
