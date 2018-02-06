package com.emcs.Constant;

/**
 * Autor:WeiLW
 * Created by edianzu on 2018/1/23.
 */
public interface FileConstant {

    /**
     *对账提示码
     */
    enum  ReconciliationEnumConstant implements EnumProperty{
        RGB0F0R000("RGB0F0R000","RGB0F0R000","对账完成"),
        RGB0F0R001("RGB0F0R001","RGB0F0R001","对账开始"),
        RGB0F0R002("RGB0F0R002","RGB0F0R002","对账中"),
        RGB0F0R003("RGB0F0R003","RGB0F0R003","对账异常"),
        RGB0F0R004("RGB0F0R004","RGB0F0R004","无对账数据"),
        RGB0F0R005("RGB0F0R005","RGB0F0R005","对账文件不存在");

        private String value;
        private String code;
        private String desc;

        ReconciliationEnumConstant(String value, String code, String desc) {
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
     * 清算提示码
     */
    enum RegisterEnumConstant implements EnumProperty {

        RGB0F0S000("RGB0F0S000","RGB0F0S000","清算完成"),
        RGB0F0S001("RGB0F0S001","RGB0F0S001","清算开始"),
        RGB0F0S002("RGB0F0S002","RGB0F0S002","清算中"),
        RGB0F0S003("RGB0F0S003","RGB0F0S003","清算异常"),
        RGB0F0S004("RGB0F0S004","RGB0F0S004","无清算数据"),
        RGB0F0S005("RGB0F0S005","RGB0F0S005","清算文件不存在");

        private String value;
        private String code;
        private String desc;

        RegisterEnumConstant(String value, String code, String desc) {
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
