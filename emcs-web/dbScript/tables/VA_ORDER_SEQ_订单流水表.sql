drop table if exists `va_order_seq`;
create table `va_order_seq`(
	`oder_seq_no` varchar(32) comment '订单流水号',
	`order_no`  varchar(32) not null comment '订单号',
	`order_no_old`  varchar(32)  comment '原订单号',
	`pub_seq_no` varchar(32) not null comment '公共流水号',
	`plat_id` varchar(32) not null comment '平台编号',
	`payer_id`  varchar(32) not null comment '付款方编号',
	`payer_virid`  varchar(32) not null comment '付款方虚拟账户编号',
	`payee_id` varchar(32) not null comment '收款方编号',
	`payee_virid`  varchar(32) not null comment '收款方虚拟账户编号',
	`pay_type` varchar(4) not null comment '支付方式',
	`tran_amt` decimal(10,2) comment '交易金额=使用的可用金额+使用的充值金额',
	`tran_type` varchar(4) not null comment '交易类型',
	`create_date` date  not null comment '订单创建时间',
	primary key(`oder_seq_no`)
)comment '订单信息表';