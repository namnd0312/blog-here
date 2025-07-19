package com.namnd.enums;

public enum MessageEnum {
    SUCCESS("00", "OK", "SUCCESS", "SUCCESS"),
    BUSINESS_ERROR("01", "FAIL", "Business error.", "Business error: %s"),
    UN_HANDLE_ERROR("02", "FAIL", "Internal server error.", "Internal server error: %s"),
    SQL_EXCEPTION("03", "FAIL", "Database error.", "Database error: %s"),
    FIELD_INVALID("04", "FAIL", "Fields invalid error.", "Fields invalid error: %s"),
    NOT_FOUND("05", "FAIL", "Data not found.", "Data not found: %s");

    private final String code;
    private final String status;
    private final String message;
    private final String messageFormat;

    MessageEnum(String code, String status, String message, String messageFormat) {
        this.code = code;
        this.status = status;
        this.message = message;
        this.messageFormat = messageFormat;
    }

    public String formatMessage(Object... args) {
        return String.format(messageFormat, args);
    }

    public String getCode() {
        return code;
    }

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
