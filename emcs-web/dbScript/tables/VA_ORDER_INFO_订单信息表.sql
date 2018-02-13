drop table if exists `va_order_info`;
create table `va_order_info`(
	`inner_oder_no` varchar(32) comment '内部订单流水号',
	`order_no`  varchar(32) not null comment '订单号',
	`order_no_old`  varchar(32) not null comment '原订单号',
	`pub_seq_no` varchar(32) not null comment '公共流水号',
	`plat_id` varchar(32) not null comment '平台编号',
	`plat_virid` varchar(6) not null comment '平台虚拟账户编号',
	`payer_id`  varchar(32) not null comment '付款方编号',
	`payer_virid`  varchar(32) not null comment '付款方虚拟账户编号',
	`payee_id` varchar(32) not null comment '收款方编号',
	`payee_virid`  varchar(32) not null comment '收款方虚拟账户编号',
	`pay_type` varchar(4) not null comment '支付方式',
	`tran_amt` decimal(10,2) comment '交易金额=使用的可用金额+使用的充值金额',
	`usable_bal` decimal(10,2) comment '使用的可用金额',
	`recharge_bal` decimal(10,2) comment '使用的充值金额',
	`tran_type` varchar(4) not null comment '交易类型',
	`order_status`  varchar(4) not null comment '订单状态',
	`create_date` date  not null comment '订单创建时间',
	`update_date` date comment '订单更新时间',
	primary key(`inner_oder_no`)
)comment '订单信息表';