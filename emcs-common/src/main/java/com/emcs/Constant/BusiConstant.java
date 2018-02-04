package com.emcs.Constant;

/**
 * Created by Administrator on 2018/2/3.
 */
public interface BusiConstant {
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