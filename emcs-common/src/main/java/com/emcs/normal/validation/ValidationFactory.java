package com.emcs.normal.validation;

import com.emcs.normal.validation.rule.ValidationFilter;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by edianzu on 2018/1/25.
 */
public class ValidationFactory {

    //获取所有校验类
    private static  ValidationFilter validationFilter = null;

    private static  ValidationFactory  validationFactory= null;

    /**
     * 单例生产自己
     * @return
     */
    public static ValidationFactory getValidationFactory(){
        if(validationFactory == null){
            return new ValidationFactory();
        }
        return validationFactory;
    }

    /**
     * 获取所有的校验类
     * @param pack
     * @return
     */
    public static ValidationFilter getValidation(String pack) {
        //如果存在则直接返回
        if(validationFilter != null ){
            return validationFilter;
        }

        Set<Class<?>> classes = BeanFactory.getClasses(pack);
        //暂时保存上一次的filter
        ValidationFilter preValidationFilter = null;
        for (Class<?> zClass : classes) {
            try {
                //获取当前校验器
                ValidationFilter tempValidationFilter = (ValidationFilter) zClass.newInstance();
                //判断是否为链表的第一个节点，如果是则保存第一个节点的引用
                if (validationFilter == null){
                    validationFilter = tempValidationFilter;
                    preValidationFilter = tempValidationFilter;
                    continue;
                }
                //像上一检验器中保存当前校验器
                preValidationFilter.addFilter(tempValidationFilter);
                //将当前引用赋予给上一个校验器，指针一向想在的校验器
                preValidationFilter = tempValidationFilter;
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return validationFilter;
    }
}
