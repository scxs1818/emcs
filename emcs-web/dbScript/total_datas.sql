INSERT INTO CM_BUSINESS_PARA VALUES('1','recharge_cnt','3','商户日最大充值次数','1',(select CURDATE() from dual),'admin');
INSERT INTO CM_BUSINESS_PARA VALUES('1','recharge_amt','20000','商户单笔最大充值金额','1',(select CURDATE() from dual),'admin');
INSERT INTO CM_BUSINESS_PARA VALUES('2','recharge_cnt','3','会员日最大充值次数','1',(select CURDATE() from dual),'admin');
INSERT INTO CM_BUSINESS_PARA VALUES('2','recharge_amt','5000','会员单笔最大充值金额','1',(select CURDATE() from dual),'admin');

INSERT INTO CM_BUSINESS_PARA VALUES('3','withdraw_cnt','3','商户日最大提现次数','1',(select CURDATE() from dual),'admin');
INSERT INTO CM_BUSINESS_PARA VALUES('3','withdraw_amt','20000','商户单笔最大提现金额','1',(select CURDATE() from dual),'admin');
INSERT INTO CM_BUSINESS_PARA VALUES('4','withdraw_cnt','3','会员日最大提现次数','1',(select CURDATE() from dual),'admin');
INSERT INTO CM_BUSINESS_PARA VALUES('4','withdraw_amt','5000','会员单笔最大提现金额','1',(select CURDATE() from dual),'admin');

INSERT INTO CM_BUSINESS_PARA VALUES('56','transfer_cnt','3','商户日最大转账次数','1',(select CURDATE() from dual),'admin');
INSERT INTO CM_BUSINESS_PARA VALUES('56','transfer_amt','20000','商户单笔最大转账金额','1',(select CURDATE() from dual),'admin');
INSERT INTO CM_BUSINESS_PARA VALUES('78','transfer_cnt','3','会员日最大转账次数','1',(select CURDATE() from dual),'admin');
INSERT INTO CM_BUSINESS_PARA VALUES('78','transfer_amt','5000','会员单笔最大转账金额','1',(select CURDATE() from dual),'admin');

INSERT INTO CM_BUSINESS_PARA VALUES('9','purchase_cnt','10','商户日最大采购次数','1',(select CURDATE() from dual),'admin');
INSERT INTO CM_BUSINESS_PARA VALUES('9','purchase_amt','20000','商户单笔最大采购金额','1',(select CURDATE() from dual),'admin');
INSERT INTO CM_BUSINESS_PARA VALUES('10','purchase_cnt','10','会员日最大采购次数','1',(select CURDATE() from dual),'admin');
INSERT INTO CM_BUSINESS_PARA VALUES('10','purchase_amt','5000','会员单笔最大采购金额','1',(select CURDATE() from dual),'admin');

insert into `CM_SYSTEM` values ((select CURDATE() from dual),(select CURDATE()-1 from dual),null,'Y');

INSERT INTO VA_SEQUENCE VALUES('PLAT_SEQ_NO',0,1,'平台编号');
INSERT INTO VA_SEQUENCE VALUES('PLAT_VIR_ACC_SEQ_NO',0,1,'平台虚拟账户编号');
INSERT INTO VA_SEQUENCE VALUES('PLAT_BAN_ACC_SEQ_NO',0,1,'平台银行账户编号');
INSERT INTO VA_SEQUENCE VALUES('MERCH_SEQ_NO',0,1,'商户编号');
INSERT INTO VA_SEQUENCE VALUES('MERCH_VIR_ACC_SEQ_NO',0,1,'商户虚拟账户编号');
INSERT INTO VA_SEQUENCE VALUES('MERCH_BAN_ACC_SEQ_NO',0,1,'商户银行账户编号');
INSERT INTO VA_SEQUENCE VALUES('CUST_SEQ_NO',0,1,'会员编号');
INSERT INTO VA_SEQUENCE VALUES('CUST_VIR_ACC_SEQ_NO',0,1,'会员虚拟账户编号');
INSERT INTO VA_SEQUENCE VALUES('CUST_BAN_ACC_SEQ_NO',0,1,'会员银行账户编号');

INSERT INTO `VA_VIRTUAL_ACCT_TYPE` VALUES ('101','平台虚拟账户','1','Y','Y','Y',132450.00000000,'Y','F',20.3600000,'ADMIN','ADMIN','');
INSERT INTO `VA_VIRTUAL_ACCT_TYPE` VALUES ('201','商户虚拟充值账户','2','Y','Y','Y',100000.00000000,'Y','R',0,'ADMIN','ADMIN','');
INSERT INTO `VA_VIRTUAL_ACCT_TYPE` VALUES ('202','商户虚拟交易账户','2','Y','Y','Y',12500.00000000,'Y','F',12.50000000,'ADMIN','ADMIN','');
INSERT INTO `VA_VIRTUAL_ACCT_TYPE` VALUES ('203','商户虚拟结算账户','2','Y','Y','Y',132450.00000000,'Y','F',20.3600000,'ADMIN','ADMIN','');
INSERT INTO `VA_VIRTUAL_ACCT_TYPE` VALUES ('301','个人虚拟账户','3','Y','Y','Y',50000.00000000,'Y','F',50.00000000,'ADMIN','ADMIN','');

COMMIT;