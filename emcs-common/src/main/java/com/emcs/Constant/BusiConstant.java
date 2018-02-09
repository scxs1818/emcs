package com.emcs.Constant;

/**
 * Created by Administrator on 2018/2/3.
 */
public interface BusiConstant {
    public enum Quence{
        PLAT("PLAT_SEQ_NO",4,"平台序列"),
        PLAT_VIRT("PLAT_VIR_ACC_SEQ_NO",4,"平台虚拟账户序列"),
        PLAT_BANK("PLAT_BAN_ACC_SEQ_NO",6,"平台银行账户序列"),

        MERCH("MERCH_SEQ_NO",8,"商户序列"),
        MERCH_VIRT("MERCH_VIR_ACC_SEQ_NO",8,"商户虚拟账户序列"),
        MERCH_BANK("MERCH_BAN_ACC_SEQ_NO",8,"商户银行账户序列"),

        CUST("CUTS_SEQ_NO",12,"个人序列"),
        CUST_VIRT("CUST_VIR_ACC_SEQ_NO",12,"个人虚拟账户序列"),
        CUST_BANK("SEQ_CUST_MERCH_BAN_ACC",12,"个人银行账户序列"),
        ;
        private int length;
        private String name;
        private String desc;
        Quence(String name, int length,String desc) {
            this.length = length;
            this.name = name;
            this.desc=desc;
        }

        public int length() {
            return length;
        }

        public String gname() {
            return name;
        }
        public String desc() {
            return desc;
        }
    }

    public enum Role{
        PLAT("1","平台"),
        MERCH("2","商户"),
        CUST("3","个人"),
        ;
        Role(String value,String desc){
            this.value=value;
            this.desc=desc;
        }
        private String value;
        private String desc;
        public String desc() {
            return desc;
        }
        public String vaue(){
            return value;
        }
    }

    public enum TranType{
        MERCH_RECHARGE("1","商户充值"),
        CUST_RECHARGE("2","个人充值"),

        MERCH_WITHDRAW ("3","商户提现"),
        CUST_WITHDRAW("4","个人提现"),

        TRANSFER_MERCH_TO_MERCH ("5","商户->商户转账"),
        TRANSFER_MERCH_TO_CUST("6","商户->个人转账"),
        TRANSFER_CUST_TO_MERCH ("7","个人->商户转账"),
        TRANSFER_CUST_TO_CUST("8","个人->个人转账"),

        MERCH_PURCHASE_APPLY("9","商户采购"),
        CUST_PURCHASE_APPLY("10","个人采购"),

        MERCH_PURCHASE_REVOKE("11","商户采购撤销"),
        CUST_PURCHASE_REVOKE("12","个人采购撤销"),

        MERCH_PURCHASE_CONFIRM("13","商户采购确认"),
        CUST_PURCHASE_CONFIRM("14","个人采购确认"),
        ;
        private String value;
        private String desc;
        TranType(String value,String desc){
            this.value=value;
            this.desc=desc;
        }
        public String vaue(){
            return value;
        }
        public String desc(){
            return desc;
        }
    }

    public enum Cache{
        CM_SYSTEM("CM_SYSTEM","系统表"),
        CM_BUSINESS_PARA("CM_BUSINESS_PARA","业务参数表"),
        VA_VIRTUAL_ACCT_TYPE("VA_VIRTUAL_ACCT_TYPE","虚拟账户类型"),
        ;
        Cache(String value,String desc){
            this.value=value;
            this.desc=desc;
        }
        private String value;
        private String desc;
        public String desc() {
            return desc;
        }
        public String vaue(){
            return value;
        }
    }

    public enum AcctProperty{
        ACCT_BAN("B","银行账户"),
        ACCT_VIR("V","虚拟账户"),
        ;
        private String value;
        private String desc;
        AcctProperty(String value,String desc){
            this.value=value;
            this.desc=desc;
        }
        public String value(){
            return value;
        }
        public String desc(){
            return desc;
        }
    }

    int SEQ_NO_PLAT_LENGTH = 4;
    int SEQ_NO_PLAT_BAN_ACC_LENGTH = 6;
    int SEQ_NO_MERCH_LENGTH = 8;
    int SEQ_NO_MERCH_BAN_LENGTH = 8;
    int SEQ_NO_MERCH_VIR_LENGTH = 8;
    int SEQ_NO_CUST_LENGTH = 12;
    int SEQ_NO_CUST_BAN_LENGTH = 12;
    int SEQ_NO_CUST_VIR_LENGTH = 12;

    String CACHE_VA_VIRTUAL_ACCT_TYPE = "va_virtual_acct_type";
    String CACHE_CM_SYSTEM = "cm_system ";
    String CACHE_CM_BUSINESS_PARA = "cm_business_para";

    String SEQ_NAME_MERCH = "MERCH_SEQ_NO";
    String SEQ_NAME_PLAT = "PLAT_SEQ_NO";
    String SEQ_NAME_CUST = "CUST_SEQ_NO";
    String SEQ_NAME_PLAT_BAN_ACC = "PLAT_BAN_ACC_SEQ_NO";
    String SEQ_NAME_MERCH_BAN_ACC = "MERCH_BAN_ACC_SEQ_NO";
    String SEQ_NAME_MERCH_VIR_ACC = "MERCH_VIR_ACC_SEQ_NO";
    String SEQ_CUST_MERCH_BAN_ACC = "CUST_BAN_ACC_SEQ_NO";
    String SEQ_CUST_MERCH_VIR_ACC = "CUST_VIR_ACC_SEQ_NO";


    /**平台*/
    String ROLE_PLAT = "1";
    /**商户*/
    String ROLE_MERCH = "2";
    /**个人*/
    String ROLE_CUST = "3";

    /**虚拟账户*/
    String ACCT_VIR = "V";
    /**银行账户*/
    String ACCT_BAN = "B";

    String[] VIR_SUB_ACCT_PLAT = new String[]{"01","02","03"};
}