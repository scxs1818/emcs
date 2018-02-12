truncate table cm_business_para;
insert into cm_business_para values('1','limit_cnt','3','商户日最大充值次数','1',(select curdate() from dual),'admin');
insert into cm_business_para values('1','limit_amt','20000','商户单笔最大充值金额','1',(select curdate() from dual),'admin');
insert into cm_business_para values('2','limit_cnt','3','会员日最大充值次数','1',(select curdate() from dual),'admin');
insert into cm_business_para values('2','limit_amt','5000','会员单笔最大充值金额','1',(select curdate() from dual),'admin');
insert into cm_business_para values('3','limit_cnt','3','商户日最大提现次数','1',(select curdate() from dual),'admin');
insert into cm_business_para values('3','limit_amt','20000','商户单笔最大提现金额','1',(select curdate() from dual),'admin');
insert into cm_business_para values('4','limit_cnt','3','会员日最大提现次数','1',(select curdate() from dual),'admin');
insert into cm_business_para values('4','limit_amt','5000','会员单笔最大提现金额','1',(select curdate() from dual),'admin');
insert into cm_business_para values('56','limit_cnt','3','商户日最大转账次数','1',(select curdate() from dual),'admin');
insert into cm_business_para values('56','limit_amt','20000','商户单笔最大转账金额','1',(select curdate() from dual),'admin');
insert into cm_business_para values('78','limit_cnt','3','会员日最大转账次数','1',(select curdate() from dual),'admin');
insert into cm_business_para values('78','limit_amt','5000','会员单笔最大转账金额','1',(select curdate() from dual),'admin');
insert into cm_business_para values('9','limit_cnt','10','商户日最大采购次数','1',(select curdate() from dual),'admin');
insert into cm_business_para values('9','limit_amt','20000','商户单笔最大采购金额','1',(select curdate() from dual),'admin');
insert into cm_business_para values('10','limit_cnt','10','会员日最大采购次数','1',(select curdate() from dual),'admin');
insert into cm_business_para values('10','limit_amt','5000','会员单笔最大采购金额','1',(select curdate() from dual),'admin');

truncate table cm_system;
insert into `cm_system` values ((select curdate() from dual),(select curdate()-1 from dual),null,'y');

truncate table va_sequence;
insert into va_sequence values('plat_seq_no',0,1,4,'平台编号');
insert into va_sequence values('plat_vir_acc_seq_no',0,1,4,'平台虚拟账户编号');
insert into va_sequence values('plat_ban_acc_seq_no',0,1,6,'平台银行账户编号');
insert into va_sequence values('merch_seq_no',0,1,8,'商户编号');
insert into va_sequence values('merch_vir_acc_seq_no',0,1,8,'商户虚拟账户编号');
insert into va_sequence values('merch_ban_acc_seq_no',0,1,8,'商户银行账户编号');
insert into va_sequence values('cust_seq_no',0,1,12,'会员编号');
insert into va_sequence values('cust_vir_acc_seq_no',0,1,12,'会员虚拟账户编号');
insert into va_sequence values('cust_ban_acc_seq_no',0,1,12,'会员银行账户编号');
insert into va_sequence values('transfer_seq_no',0,1,16,'转账流水号');


truncate table va_virtual_acct_type;
insert into `va_virtual_acct_type` values ('101','平台虚拟账户','1','y','y','y',132450.00000000,'y','f',20.3600000,'admin','admin','');
insert into `va_virtual_acct_type` values ('201','商户虚拟充值账户','2','y','y','y',100000.00000000,'y','r',0,'admin','admin','');
insert into `va_virtual_acct_type` values ('202','商户虚拟交易账户','2','y','y','y',12500.00000000,'y','f',12.50000000,'admin','admin','');
insert into `va_virtual_acct_type` values ('203','商户虚拟结算账户','2','y','y','y',132450.00000000,'y','f',20.3600000,'admin','admin','');
insert into `va_virtual_acct_type` values ('301','个人虚拟账户','3','y','y','y',50000.00000000,'y','f',50.00000000,'admin','admin','');

commit;