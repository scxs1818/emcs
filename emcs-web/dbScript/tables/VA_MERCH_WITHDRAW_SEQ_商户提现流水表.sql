drop table if exists `va_merch_withdraw_seq`;
create table `va_merch_withdraw_seq` (
    `merch_withdraw_seq` varchar(32) not null comment '商户提现流水号',
    `pub_seq_no` varchar(32) not null comment '公共流水号',
    `order_no` varchar(32) not null comment '业务流水号',
    `plat_id` varchar(32) not null comment '平台编号',
    `payer_id` varchar(32) not null comment '付款方编号',
    `payer_virid` varchar(32) not null comment '付款方虚拟账户编号',
    `payee_acct_no` varchar(32) not null comment '收款账号',
    `tran_type` varchar(4) not null comment '交易类型',
    `tran_amt` decimal(20,8) not null comment '交易金额',
    `tran_date` date not null comment '交易日期',
    primary key (`merch_withdraw_seq`)
    ) comment='商户提现记录流表';