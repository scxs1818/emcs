package com.emcs.util;

import org.hibernate.validator.internal.xml.FieldType;

import java.io.File;
import java.math.BigDecimal;
import java.sql.Array;
import java.util.Collection;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2018/2/11.
 */
public class CheckEmpty {
    public static void main(String[] args) {
        System.out.println(isAmount("13246.4",1));
    }

    /**比较两个字符串数字的大小,str1>str2返回1,str1=str2返回0,str1<str2返回-1*/
    public static int compare(String str1, String str2) {
        Integer t1 = Integer.parseInt(str1);
        Integer t2 = Integer.parseInt(str2);
        return (t1 > t2) ? 1 : (t1 == t2) ? 0 : -1;
    }

    /**比较两个对象是否相等*/
    public static boolean isEqual(Object a, Object b) {
        return a==b || (a != null && a.equals(b));
    }

    /**判断数字是否合法*/
    public static boolean isNumber(String str) {
        return Pattern.compile("([1-9]\\d*|0)(\\.\\d+)?").matcher(str).matches();
    }

    /**判断金额是否合法,默认最大小数位为2位*/
    public static boolean isAmount(String str) {
        return isAmount(str,2);
    }

    /**判断金额是否合法,'len'指定金额的小数部分最大位数 */
    public static boolean isAmount(String str,int len) {
        return Pattern.compile(getAmtRegExp(len)).matcher(str).matches();
    }

    private static String getAmtRegExp(int len) {
        if (len == 0) {
            return "[1-9]\\d*?";
        } else {
            return "([1-9]\\d*|0)(\\.\\d{1," + len + "})?";
        }
    }

    /**判断两位数金额，并且是正数：是-true，否-false*/
    public static boolean isPositiveNum(String str) {
        if ( !isAmount(str)) {
            return false;
        } else if(new BigDecimal(str).compareTo(new BigDecimal(0)) <1){
            return false;
        }else{
            return true;
        }
    }

    /**
     *判空处理 该方法需完善，请用到的同事在后面添加支持类型
     *
     * @param b
     * @return boolean false-非空 true-为空
     */
    public static boolean isEmpty(Object b) {
        if (b == null)
            return true;
        if (b instanceof String)
            return isEmpty((String) b);
        if (b instanceof Collection<?>)
            return isEmpty((Collection<?>) b);
        if (b instanceof Map)
            return isEmpty((Map) b);
        if (b instanceof Object[])
            return isEmpty((Object[]) b);
        if (b instanceof File)
            return isEmpty((File) b);
        if (b instanceof StringBuilder || b instanceof StringBuffer)
            return isEmpty(b.toString());
        if (b instanceof Number)
            return isEmpty((Number)b);
        return false;
    }

    public static boolean isEmpty(Number b) {
        if (b == null)return true;
        if(b instanceof BigDecimal)
            return ((BigDecimal)b).compareTo(new BigDecimal("0"))==0;
        return b.doubleValue()==0;
    }

    public static boolean isEmpty(Map m) {
        return m == null || m.size()==0;
    }

    public static boolean isEmpty(File f) {
        return f == null || !f.exists() || f.length() == 0;
    }

    public static boolean isEmpty(Object[] o) {
        return o == null || o.length == 0;
    }

    public static boolean isEmpty(Collection<?> c) {
        return c == null || c.isEmpty();
    }

    public static boolean isEmpty(String str) {
        return str == null || "".equals(str.trim());
    }
}
