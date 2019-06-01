package com.bettercode.connect.excel.exception;

import java.util.HashMap;
import java.util.Map;

public class IllegalExcelException extends RuntimeException {
    public enum ExcelErrorCode {
        VALUE_REQUIRED("101"),
        VALUE_NUMBER_ONLY_ALLOWED("102");

        private String errorCode;

        ExcelErrorCode(String errorCode) {
            this.errorCode = errorCode;
        }

        public String getCode() {
            return errorCode;
        }
    }

    private ExcelErrorCode errorCode;
    private Map<String, String> errorMessages;

    public IllegalExcelException(ExcelErrorCode errorCode) {
        this.errorCode = errorCode;
        this.errorMessages = new HashMap<>();
    }

    public ExcelErrorCode getErrorCode() {
        return errorCode;
    }

    public void addErrorMessage(String key, String value) {
        errorMessages.put(key, value);
    }

    public Map<String, String> getErrorMessages() {
        return errorMessages;
    }
}
