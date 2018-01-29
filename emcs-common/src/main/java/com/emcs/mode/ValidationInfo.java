package com.emcs.mode;

/**
 * Created by edianzu on 2018/1/24.
 */
public class ValidationInfo {
    private String fieldName;
    private Object fieldValue;
    private String validationDesc;

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public Object getFieldValue() {
        return fieldValue;
    }

    public void setFieldValue(Object fieldValue) {
        this.fieldValue = fieldValue;
    }

    public String getValidationDesc() {
        return validationDesc;
    }

    public void setValidationDesc(String validationDesc) {
        this.validationDesc = validationDesc;
    }
}
