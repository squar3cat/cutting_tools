package com.app.tools.util.exception;

public class ErrorInfo {
    private final String url;
    private final com.app.tools.util.exception.ErrorType type;
    private final String[] details;

    public ErrorInfo(CharSequence url, com.app.tools.util.exception.ErrorType type, String... details) {
        this.url = url.toString();
        this.type = type;
        this.details = details;
    }
}