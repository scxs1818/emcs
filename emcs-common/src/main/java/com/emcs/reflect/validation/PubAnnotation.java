package com.emcs.reflect.validation;

import java.lang.annotation.*;

/**
 * Created by edianzu on 2018/1/22.
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface PubAnnotation {
    /**
     * 是否必须校验
     * @return
     */
   public String require() default "N";

    /**
     * 匹配检验
     * @return
     */
   public  String match() default "\\.";

    /**
     * 最大年龄校验
     * @return
     */
    public int maxValue()  default  Integer.MAX_VALUE;

    /**
     * 最小年龄校验
     * @return
     */
    public int minValue()  default  Integer.MIN_VALUE;

    /**
     * 非Null校验
     * @return
     */
    public String notNull() default "notNull";

    /**
     * 非空校验
     * @return
     */
    public String notEmpty() default "notEmpty";

}
