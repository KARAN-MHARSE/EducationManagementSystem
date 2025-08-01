package com.aurionpro.ems.dto;

public class OperationResult {
    private boolean success;
    private String message;

    public OperationResult(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    // getters
    public boolean isSuccess() { return success; }
    public String getMessage() { return message; }
}

