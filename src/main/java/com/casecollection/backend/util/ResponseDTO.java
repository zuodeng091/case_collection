package com.casecollection.backend.util;

public class ResponseDTO {

    private boolean success;
    private Object data;
    private String message;
    
    
    
    public ResponseDTO(boolean success) {
        this.success = success;
    }
    
    public ResponseDTO(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public ResponseDTO(boolean success, Object data) {
        this.success = success;
        this.data = data;
    }

    public static ResponseDTO getSuccessResult() {
        return new ResponseDTO(true);
    }

    public static ResponseDTO getSuccessResultWithData(Object data) {
        return new ResponseDTO(true, data);
    }
    
    public static ResponseDTO getFailResult(String msg) {
        return new ResponseDTO(false, msg);
    }
    
    public boolean isSuccess() {
        return success;
    }
    public void setSuccess(boolean success) {
        this.success = success;
    }
    public Object getData() {
        return data;
    }
    public void setData(Object data) {
        this.data = data;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

}
