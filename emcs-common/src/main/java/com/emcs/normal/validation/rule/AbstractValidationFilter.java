package com.emcs.normal.validation.rule;

/**
 * Created by edianzu on 2018/1/29.
 */
public  abstract class AbstractValidationFilter implements ValidationFilter {

    public ValidationFilter validationFilter= null;

    @Override
    public void addFilter(ValidationFilter validationFilter) {
        this.validationFilter = validationFilter;
    }

    public ValidationFilter getFilter(){
        return this.validationFilter;
    }
}
