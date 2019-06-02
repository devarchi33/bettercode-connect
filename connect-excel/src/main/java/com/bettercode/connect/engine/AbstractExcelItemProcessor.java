package com.bettercode.connect.engine;

import com.bettercode.connect.exception.IllegalExcelException;

// https://kanetami.tistory.com/entry/%EC%A0%95%EA%B7%9C%EC%8B%9D%EC%9C%BC%EB%A1%9C-%ED%8A%B9%EC%A0%95%EB%AC%B8%EC%9E%90-%EC%A0%9C%EA%B1%B0 - 정규식으로 특정문자 제거
// https://mainia.tistory.com/4457 - 숫자에 화폐단위 표기
public abstract class AbstractExcelItemProcessor {
    protected long getLongValueFromCell(String value, int rowIndex, int columnIndex) {
        validateValue(value, rowIndex, columnIndex);

        try {
            return Long.valueOf(value);
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
            return Double.valueOf(value);
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
            return Integer.valueOf(value);
        } catch (NumberFormatException e) {
            IllegalExcelException illegalExcelException= new IllegalExcelException(IllegalExcelException.ExcelErrorCode.VALUE_NUMBER_ONLY_ALLOWED);
            illegalExcelException.addErrorMessage("row", String.valueOf(rowIndex + 1));
            illegalExcelException.addErrorMessage("column", String.valueOf(columnIndex + 1));

            throw illegalExcelException;
        }
    }

    protected String getStringValueFromCell(String value, int rowIndex, int columnIndex) {
        return value != null ? value.trim() : "";
    }

    private void validateValue(String value, int rowIndex, int columnIndex) {
        if(value == null || value.trim().isEmpty()) {
            IllegalExcelException illegalExcelException= new IllegalExcelException(IllegalExcelException.ExcelErrorCode.VALUE_REQUIRED);
            illegalExcelException.addErrorMessage("value", value);
            illegalExcelException.addErrorMessage("row", String.valueOf(rowIndex + 1));
            illegalExcelException.addErrorMessage("column", String.valueOf(columnIndex + 1));

            throw illegalExcelException;
        }
    }
}
