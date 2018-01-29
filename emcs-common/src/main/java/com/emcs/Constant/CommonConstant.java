package com.emcs.Constant;

/**
 * Created by edianzu on 2018/1/23.
 */
public interface CommonConstant {
    //文件编码
    public static String FILE_ENCODING = "UTF-8";

    /**
     * 数据校验常量
     */
    public interface Validation{
        //非空校验
        public static  final String VISBLANK = "VIsBlank";
        //非null校验
        public static  final String VISNULL = "VIsNull";
        //正则表达式校验
        public static  final String VMATCH = "VMatch";
        //最大值校验
        public static  final String VMAX = "VMax";
        //最小值校验
        public static  final String VMIN = "VMin";
        //必须校验
        public static  final String VREQUIRED = "VRequired";

    }

    /**
     * 性别类型
     */
    public interface SexType {
        //女
        public static final String M = "M";
        //男
        public static final String F = "F";
        //不详
        public static final String N = "N";
    }

    /**
     * 是否标志
     */
    public interface  FlagType{
        //是
        public static final String Y = "Y";
        //否
        public static final String N = "N";
    }

    /**
     * 文件路劲
     */
    public  interface  FilePatch{
        //对账文件路径
        public  static final String CHECK_PATCH = "checkPatch";

    }
}
