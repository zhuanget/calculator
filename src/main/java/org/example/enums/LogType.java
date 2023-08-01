package org.example.enums;

public enum LogType {
    INFO("info"),
    ERROR("error"),
    WARNING("warning"),
    ;
    private final String logType;

    LogType(String logType) {
        this.logType = logType;
    }

    public String getLogType() {
        return logType;
    }
}
