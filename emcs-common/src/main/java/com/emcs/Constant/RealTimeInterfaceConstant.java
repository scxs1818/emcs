package com.emcs.Constant;

/**
 * Created by edianzu on 2018/1/23.
 */
public interface RealTimeInterfaceConstant {

    /**
     * 平台接口提示码
     */
    public enum Platform implements EnumProperty {

        RGI0P00000("RGI0P00000","RGI0P00000","平台注册成功"),
        RGI0P00001("RGI0P00001","RGI0P00001","平台注册失败"),
        RGI0P00002("RGI0P00002","RGI0P00002","平台注册异常"),
        RGI0P00003("RGI0P00003","RGI0P00003","平台注册中");

        private String value;
        private String code;
        private String desc;

        Platform(String value, String code, String desc) {
            this.value = value;
            this.code = code;
            this.desc = desc;
        }

        public String value(){
            return value;
        }
        public String code(){
            return code;
        }
        public String desc(){
            return desc;
        }
    }

    /**
     * 商户接口提示码
     */
    public enum Merchant implements EnumProperty {

        RGI0B00000("RGI0B00000","RGI0B00000","商户注册成功"),
        RGI0B00001("RGI0B00001","RGI0B00001","商户注册失败"),
        RGI0B00002("RGI0B00002","RGI0B00002","商户注册异常"),
        RGI0B00003("RGI0B00003","RGI0B00003","商户注册中");

        private String value;
        private String code;
        private String desc;

        Merchant(String value, String code, String desc) {
            this.value = value;
            this.code = code;
            this.desc = desc;
        }

        public String value(){
            return value;
        }
        public String code(){
            return code;
        }
        public String desc(){
            return desc;
        }
    }

    /**
     * 用户接口提示码
     */
    public enum Consumer implements EnumProperty {

        RGI0U00000("RGI0U00000","RGI0U00000","用户注册成功"),
        RGI0U00001("RGI0U00001","RGI0U00001","用户注册失败"),
        RGI0U00002("RGI0U00002","RGI0U00002","用户注册异常"),
        RGI0U00003("RGI0U00003","RGI0U00003","用户注册中");

        private String value;
        private String code;
        private String desc;

        Consumer(String value, String code, String desc) {
            this.value = value;
            this.code = code;
            this.desc = desc;
        }

        public String value(){
            return value;
        }
        public String code(){
            return code;
        }
        public String desc(){
            return desc;
        }
    }
}
