package com.emcs.normal.validation.rule;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by edianzu on 2018/1/25.
 */
public interface ValidationFilter {

    /**
     * 规则校验
     * @param rule
     * @param validationResult
     * @return
     */
    public void doFilter(Map<String, Object> rule, Map<String, Object> validationResult);

    /**
     * 添加下一个校验器
     * @param validationFilter
     */
    public void addFilter(ValidationFilter validationFilter);

}
