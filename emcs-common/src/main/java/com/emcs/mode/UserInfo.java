package com.emcs.mode;

import com.emcs.Constant.CommonConstant;
import com.emcs.reflect.validation.PubAnnotation;

/**
 * Created by edianzu on 2018/1/22.
 */
public class UserInfo {

   @PubAnnotation(require = CommonConstant.FlagType.Y)
    private String usenName;

    @PubAnnotation(match = "\\d{9}")
    private Integer age;

    public String getUsenName() {
        return usenName;
    }

    public void setUsenName(String usenName) {
        this.usenName = usenName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
