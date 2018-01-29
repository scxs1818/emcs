package com.emcs.normal.validation.rule;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by edianzu on 2018/1/25.
 */
public interface Validation {

    public static List<Validation> fundValidations = new ArrayList<Validation>();

    /**
     * 规则校验
     * @param obj
     * @return
     */
    public boolean validation(Object obj);

}
