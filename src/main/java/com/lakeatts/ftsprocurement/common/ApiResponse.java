package com.lakeatts.ftsprocurement.common;

public class ApiResponse<T> {

    private String status;
    private String message;
    private String error;
    private T data;

    public ApiResponse() {}

    public ApiResponse(String status, String message, String error, T data) {
        this.status = status;
        this.message = message;
        this.error = error;
        this.data = data;
    }

    public static <T> ApiResponse<T> success(String message, T data) {
        return new ApiResponse<>("SUCCESS", message, null, data);
    }

    public static <T> ApiResponse<T> fail(String message, String error) {
        return new ApiResponse<>("FAILED", message, error, null);
    }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public String getError() { return error; }
    public void setError(String error) { this.error = error; }

    public T getData() { return data; }
    public void setData(T data) { this.data = data; }
}
