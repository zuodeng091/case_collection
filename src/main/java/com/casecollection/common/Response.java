package com.casecollection.common;

/**
 * 数据响应对象 
 * @author luqq
 * 2016-03-22
 * @param <T>
 */
public class Response<T> {

	private T data;

    private int retCode;

    private String message;


    public int getRetCode(){
        return retCode;
    }

    public void setRetCode(int retCode){
        this.retCode = retCode;
        
    }

    public T getData(){
        return data;
    }

    public void setData(T data){
        this.data = data;
    }

    public String getMessage(){
        return message;
    }

    public void setMessage(String message){
        this.message = message;
    }
}
