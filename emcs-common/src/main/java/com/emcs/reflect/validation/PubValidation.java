package com.emcs.reflect.validation;

import com.emcs.mode.ValidationInfo;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.regex.Pattern;

/**
 * Created by edianzu on 2018/1/22.
 */
public class PubValidation<T> {
    private Properties properties = new Properties();
    /**
     * 校验数据
     * @param obj
     * @return
     */
    public List<List<ValidationInfo>> validation(Object obj){
        List<List<ValidationInfo>> validationinfos = new ArrayList<List<ValidationInfo>>();
        try{
            //当前校验属性
            Field[] fields = obj.getClass().getDeclaredFields();
            for (Field field : fields){
                List<ValidationInfo> validations = validationField(obj,field);
                //调用具体校验
                if(validations.size() != 0){
                    validationinfos.add(validations);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return validationinfos;
    }


    /**
     * 校验某个属性
     * @param field
     * @return
     */
    public  List<ValidationInfo> validationField(Object obj,Field field){
        List<ValidationInfo> validationInfos = new ArrayList<ValidationInfo>();
        //获取加载属性配置文件
        try{
            properties.load(this.getClass().getResourceAsStream("/validation.properties"));
        }catch (Exception e){
            e.printStackTrace();
        }

        //校验结果集
        StringBuilder  resFileValidation = new StringBuilder();

        //防止空指针异常
        if(field == null){
            return validationInfos;
        }

        try{
            //获取属性名
            String fieldName = field.getName();
            //获取属性描述
            PropertyDescriptor propertyDescriptor = new PropertyDescriptor(fieldName,obj.getClass());
            Method method = propertyDescriptor.getReadMethod();
            //获取属性注解
            PubAnnotation annotation = field.getAnnotation(PubAnnotation.class);
            //判null操作
            if(annotation == null){
                return validationInfos;
            }

            //获取属性值
            Object fieldValue = getMethodValue(obj,method);

            //判断逻辑
            //非空校验
            if(!"N".equals(annotation.require())){
                if(fieldValue == null || "".equals(fieldValue)){
                    getTips(validationInfos,fieldValue,fieldName);
                }
            }

            //匹配检验
            if(!"\\.".equals(annotation.match())){
                Pattern pattern = Pattern.compile(annotation.match());
                if(!pattern.matcher(fieldValue.toString()).matches()){
                    getTips(validationInfos,fieldValue,fieldName);
                }
            }

            //匹配最大值
            if(annotation.maxValue() != Integer.MIN_VALUE){

                String temoValue = fieldValue.toString();
                if(temoValue != null){

                }
                if(annotation.maxValue() != Integer.MIN_VALUE){
                    getTips(validationInfos,fieldValue,fieldName);
                }
            }

            //匹配最小值
            if(!"NNNN".equals(annotation.minValue())){
                if(annotation.minValue() != Integer.MIN_VALUE){
                    getTips(validationInfos,fieldValue,fieldName);
                }
            }

            //匹配非null校验
            if(!"notNull".equals(annotation.notNull())){
                if(fieldValue.toString() == null){
                    getTips(validationInfos,fieldValue,fieldName);
                }
            }

            //匹配非空校验
            if(!"notEmpty".equals(annotation.notEmpty())){
                if(fieldValue.toString() == null || "".equals(fieldValue.toString())){
                    getTips(validationInfos,fieldValue,fieldName);
                }
            }


        }catch (Exception e){
            e.printStackTrace();
        }

        return validationInfos;
    }

    /**
     * 获取方法返回值
     * @param obj
     * @param method
     * @return
     */
    public Object getMethodValue(Object obj,Method method){
        Object res = null;
        try {
            res  = method.invoke(obj,new Object[]{});
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return res;
    }


    /**
     * 组装校验提示信息
     * @param validationInfos
     * @param fieldValue
     * @param fieldName
     */
    private void getTips(List<ValidationInfo> validationInfos,Object fieldValue,String fieldName){
        StringBuilder stringBuilder = new StringBuilder();
        ValidationInfo validationInfo = new ValidationInfo();
        validationInfo.setFieldName(fieldName);
        validationInfo.setFieldValue(fieldValue);
        validationInfo.setValidationDesc(properties.getProperty(stringBuilder.append("validation.").append(fieldName).toString()));
        validationInfos.add(validationInfo);
    }
}
