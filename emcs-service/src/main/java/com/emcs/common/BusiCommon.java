package com.emcs.common;

/**
 * Created by Administrator on 2018/2/3.
 */
public class BusiCommon {
    public static final int SEQ_NO_PLAT_LENGTH = 4;
    public static final int SEQ_NO_PLAT_BAN_ACC_LENGTH = 6;
    public static final int SEQ_NO_MERCH_LENGTH = 8;
    public static final int SEQ_NO_MERCH_BAN_LENGTH = 8;
    public static final int SEQ_NO_MERCH_VIR_LENGTH = 8;
    public static final int SEQ_NO_CUST_LENGTH = 12;
    public static final int SEQ_NO_CUST_BAN_LENGTH = 12;
    public static final int SEQ_NO_CUST_VIR_LENGTH = 12;

    public static final String SEQ_NAME_MERCH = "MERCH_SEQ_NO";
    public static final String SEQ_NAME_PLAT = "PLAT_SEQ_NO";
    public static final String SEQ_NAME_CUST = "CUST_SEQ_NO";
    public static final String SEQ_NAME_PLAT_BAN_ACC = "PLAT_BAN_ACC_SEQ_NO";
    public static final String SEQ_NAME_MERCH_BAN_ACC = "MERCH_BAN_ACC_SEQ_NO";
    public static final String SEQ_NAME_MERCH_VIR_ACC = "MERCH_VIR_ACC_SEQ_NO";
    public static final String SEQ_CUST_MERCH_BAN_ACC = "CUST_BAN_ACC_SEQ_NO";
    public static final String SEQ_CUST_MERCH_VIR_ACC = "CUST_VIR_ACC_SEQ_NO";


    /**平台*/
    public static final String ROLE_PLAT = "1";
    /**商户*/
    public static final String ROLE_MERCH = "2";
    /**个人*/
    public static final String ROLE_CUST = "3";
    /**虚拟账户*/
    public static final String ACCT_VIR = "V";
    /**银行账户*/
    public static final String ACCT_BAN = "B";


    public static final String[] VIR_SUB_ACCT_PLAT = new String[]{"01","02","03"};

}