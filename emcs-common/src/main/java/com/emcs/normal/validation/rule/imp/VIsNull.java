package com.emcs.normal.validation.rule.imp;

import com.emcs.Constant.CommonConstant;
import com.emcs.normal.validation.rule.AbstractValidationFilter;
import com.emcs.normal.validation.rule.ValidationFilter;

import java.util.List;
import java.util.Map;

/**
 * Created by edianzu on 2018/1/25.
 */
public class VIsNull extends AbstractValidationFilter {

    @Override
    public void doFilter(Map<String, Object> rule, Map<String, Object> validationResult) {

        //链表返回点
        ValidationFilter validationFilter = this.getFilter();
        if(validationFilter == null){
            return;
        }

        //判断是否进行校验
        Object vName = rule.get("vRName");
        if(vName != null && CommonConstant.Validation.VISNULL.equals(vName)){
            //获取要校验的字段名
            Object vCName = rule.get("vCName");
            //获取校验值
            Object vCValue = rule.get(vCName);
            //开始校验
            if(vCValue == null){
                //添加校验结果
                validationResult.put(vCName.toString(),rule.get("vResult"));
            }
        }

        //进入下一个校验器
        validationFilter.doFilter(rule,validationResult);
    }
}
