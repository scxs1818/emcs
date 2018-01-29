package com.emcs.normal.validation;


import com.emcs.normal.validation.rule.ValidationFilter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by edianzu on 2018/1/25.
 */
public class ValidationFacade {


    /**
     * 校验
     * @param pack
     * @param rule
     * @param validationResult
     */
    private void filter(String pack,Map<String,Object> rule,Map<String,Object> validationResult) {
        //获取校验器
        ValidationFilter validationFilter = ValidationFactory.getValidationFactory().getValidation(pack);
        List<Map<String,Object>> rules = (List<Map<String,Object>>)rule.get("rules");
        Map<String,Object> columns = (Map<String,Object>)rule.get("columns");
        for(Map<String,Object> rul : rules){
            rul.put("vCValue",columns.get(rul.get("vCName")));
            Map<String,Object> vr = new HashMap<String,Object>();
            //处理校验
            validationFilter.doFilter(rul,vr);
            validationResult.putAll(vr);
        }
    }
}
