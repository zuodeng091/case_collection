package com.casecollection.backend.constants.enums;

/**
 * Created by luqq on 16/5/8.
 */
public enum DictionaryTypeEnum {

    PROVINCE(1,"省份"),
    ENROLL_TYPE(2,"招生类型"),
    ENROLL_BATCH(3,"招生批次"),
    COPYRIGHT(5,"版权所有者"),
    ENROLLQUERYURL(6,"录取查询链接"),
    USER_ADVICE(7,"用户建议类型"),
    DEGREE(8,"学位"),
    ;

    private Integer value;

    private String message;

    DictionaryTypeEnum(Integer value, String message) {
        this.value = value;
        this.message = message;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
