package com.emcs.Constant;

/**
 * Created by Administrator on 2018/2/10.
 */
public interface ErrorCodeConstant {
    public enum PubErrorCode implements ErrorCode{
        VAZ001("VAZ001","交易失败"),
        VAZ002("VAZ002","金额不合法"),
        VAZ003("VAZ003","身份证不合法"),
        VAZ004("VAZ004","手机号码不合法"),
        VAZ005("VAZ005","必输字段为空"),
        VAZ006("VAZ006","执行数据操作失败"),
        VAZ007("VAZ007","角色类型错误"),
        VAZ008("VAZ008","已经超出日限额"),
        VAZ009("VAZ009","已经超出日交易次数"),


        ;
        public String code() {
            return code;
        }
        public String val() {
            return val;
        }
        private String code;
        private String val;
        PubErrorCode(String code,String val){
            this.code=code;
            this.val=val;
        }
    }

    public enum PlatErrorCode implements ErrorCode{
        VAP001("VAP001","平台信息不存在或处于异常状态"),
        VAP002("VAP002","平台信息不存在"),
        VAP003("VAP003","平台处于异常状态"),
        VAP004("VAP004","平台信息已存在"),
        VAP005("VAP005","平台注册失败"),
        VAP006("VAP006","平台注册失败"),
        ;

        public String code() {
            return code;
        }
        public String val() {
            return val;
        }
        private String code;
        private String val;
        PlatErrorCode(String code,String val){
            this.code=code;
            this.val=val;
        }
    }
    public enum MerchErrorCode implements ErrorCode{
        VAB001("VAB001","商户信息不存在或处于异常状态"),
        VAB002("VAB002","商户信息不存在"),
        VAB003("VAB003","商户处于异常状态"),
        VAB004("VAB004","商户信息已存在"),
        VAB005("VAB005","商户注册失败"),
        VAB006("VAB006","该商户虚拟账户不允许转出"),

        ;
        private String code;
        private String val;
        MerchErrorCode(String code,String val){
            this.code=code;
            this.val=val;
        }

        @Override
        public String code() {
            return code;
        }

        @Override
        public String val() {
            return val;
        }
    }


    public enum CustErrorCode implements ErrorCode{
        VAC001("VAC001","会员信息不存在或处于异常状态"),
        VAC002("VAC002","会员信息不存在"),
        VAC003("VAC003","会员处于异常状态"),
        VAC004("VAC004","会员信息已存在"),
        VAC005("VAC005","会员注册失败"),
        VAC006("VAC006","该会员虚拟账户不允许转出"),
        ;

        private String code;
        private String val;
        CustErrorCode(String code,String val){
            this.code=code;
            this.val=val;
        }

        @Override
        public String code() {
            return code;
        }

        @Override
        public String val() {
            return val;
        }
    }


}
interface ErrorCode{
    String code();
    String val();
}