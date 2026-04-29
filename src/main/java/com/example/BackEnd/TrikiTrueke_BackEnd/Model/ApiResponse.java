package com.Example.BackEnd.TrikiTrueke_BackEnd.Model;

import java.time.Instant;

public class ApiResponse<T> {
    private boolean success;
    private int status;
    private String message;
    private T data;
    private String path;
    private String timestamp;

    public ApiResponse() {
    }

    public ApiResponse(boolean success, int status, String message, T data, String path) {
        this.success = success;
        this.status = status;
        this.message = message;
        this.data = data;
        this.path = path;
        this.timestamp = Instant.now().toString();
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
