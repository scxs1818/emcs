drop table if exists  `cm_acct_tran_seq`;
create table `cm_acct_tran_seq` (
  `tran_seq_no`           varchar(32)       not null comment '银行账务流水号',
   `pub_seq_no` varchar(32) not null comment '公共流水号',
  `tran_date`             date comment '交易日期',
  `tran_time`             varchar(10) comment '交易时间',
  `curreny`               varchar(4) comment '币种',
  `tran_amt`              decimal(17,2) comment '交易金额',
  `tran_type`             varchar(16) comment '交易类型',
 `business_code`         varchar(16) comment '业务编码',
  `business_name`         varchar(100) comment '业务编码名称',
  `order_no`              varchar(32) comment '业务流水号',
  `order_no_old`             varchar(32) comment '原业务流水号',
  `ref_no`        varchar(32) comment '金融参考号',
  `merch_id`         varchar(32) comment '商户编号',
  `merch_name`       varchar(100) comment '商户名称',
  `cust_id` varchar(32) comment '会员编号',
  `cust_name`       varchar(100) comment '会员名称',
  `payer_name`            varchar(100) comment '付款方名称',
  `payer_vir_acct_no`     varchar(32) comment '付款人虚拟账户',
  `payee_name`            varchar(100) comment '收款方名称',
  `payee_vir_acct_no`     varchar(32) comment '收款方虚拟账户',
  `plat_id`              varchar(32) comment '平台编号',
  `plat_name`          varchar(100) comment '平台名称',
  `payer_acct_no`         varchar(32) comment '付款方账户',
  `payer_acct_name`       varchar(100) comment '付款方账户名称',
  `payer_acct_sort`       varchar(4) comment '付款方账户类别：0会员；1商户；2交易平台',
  `payer_is_this_bank`    varchar(4) comment '付款方账户是否我行',
  `payer_acct_bank_no`    varchar(32) comment '付款方账户行号',
  `payer_acct_bank_name`  varchar(100) comment '付款方账户行名称',
  `payee_acct_no`         varchar(32) comment '收款方账户账户',
  `payee_acct_name`       varchar(100) comment '收款方账户名称',
  `payee_acct_sort`       varchar(4) comment '收款方账户类别：0会员；1商户；2交易平台',
  `payee_is_this_bank`    varchar(4) comment '收款方账户是否我行',
  `payee_acct_bank_no`   varchar(32) comment '收款方账户行号',
  `payee_acct_bank_name`  varchar(100) comment '收款方账户行名称',
  `tran_status`           varchar(4) comment '交易状态:00-未发起,01-成功,02-失败,03-已冲正',
  `ret_code`              varchar(16) comment '响应码',
  `ret_msg`               varchar(200) comment '响应信息',
  `host_seq_no`          varchar(32) comment '关联系统流水号',
  `host_tran_status`      varchar(2) comment '关联系统交易状态',
  `is_check`              varchar(4) comment '是否参与对账',
  `is_recover`            varchar(4) comment '是否冲正',
  `reversed`              varchar(4) comment '是否已被冲正',
  `oper_time`             varchar(20) comment '操作时间',
  `user_id`               varchar(32) comment '柜员id',
  `contrast`              varchar(4) comment '对账状态:0-未对账,1-平账,2-差错,3-状态不一致,4-单边',
  `refund_status`         varchar(4) comment '退款状态',
  primary key (`tran_seq_no`)
)comment '账务流水表';

drop table if exists `cm_business_para`;
create table `cm_business_para` (
   `tran_type`            varchar(4)                   not null comment '交易类型',
   `para_key`             varchar(50)                   not null comment '业务参数代码',
   `para_value`           varchar(32)                   not null comment '参数值',
   `para_desc`            varchar(100)                  not null comment '参数描述',
   `para_status`          varchar(4)                    not null comment '参数状态',
   `update_date`          date                          null comment '参数维护日期',
   `update_user`          varchar(32)                   null comment '操作员',
   primary key  (`tran_type`,`para_key`)
)comment '业务参数表';

drop table if exists  `cm_system`;
create table `cm_system` (
   `run_date`             varchar(8)                           not null comment '系统日期',
   `prev_run_date`       varchar(8)                           not null comment '前一工作日',
   `core_run_date`       varchar(8)                             comment '三方日期',
   `ser_status_flag`	varchar(2)		       not null comment '服务状态标志:n-关闭,y-开启'
)comment '系统表';

drop table  if exists `cm_tran_seq` ;
create table `cm_tran_seq`(
  `pub_seq_no`           varchar (32) comment '交易公共流水号' ,
  `order_no`           varchar(32) comment '订单号' ,
  `tran_date`           date                      not null comment '交易日期',
  `tran_time`           varchar(10) comment '交易时间',
  `channel_seq_no`   varchar(32) comment '渠道流水号',
  `channel_date`          date comment '渠道日期',
  `channel_code`        varchar(20) comment '渠道关键编码' ,
  `source_type`         varchar(16) comment '渠道类型代码',
  `tran_type`           varchar(20) comment '交易类型',
  `business_code`       varchar(11) comment '业务类型编码',
  `user_id`             varchar(32) comment '用户id',
  `plat_id`	      varchar(8) comment '交易平台编号',
  `merch_id`	      varchar(16) comment '商户编号',
	`cust_id`				varchar(18) comment '会员编号',
  `acct_no`	      varchar(30) comment '账户',
  `currency`                 varchar(4) comment '币种代码',
  `tran_amt`            decimal(12,2) comment '交易金额',
  `branch_id`           varchar(16) comment '交易机构',
  `msg_type`            varchar(10) comment 'mbsd报文类型',
  `msg_code`            varchar(10) comment 'mbsd报文编码',
  `tran_status`         varchar(2)  comment '交易状态代码:01-成功,02-失败',
  `ret_code`            varchar(16) comment '响应码',
  `fail_reason`         varchar(512) comment '失败原因',
   primary key(`pub_seq_no`)
) comment '交易流水记录表';

drop table if exists `va_cust_acct_info`;
create table `va_cust_acct_info` (
  `acct_id` varchar(32) not null comment '账户编号',
  `cust_id` varchar(32) not null comment '会员编号',
  `acct_type` varchar(32) not null comment '账户类型 0：结算账户1：交易平台存管专户2：银行内部账户',
  `acct_no` varchar(32) not null comment '账户',
  `acct_status` varchar(4) not null comment '账户状态n：正常f：冻结c：注销',
  `acct_category` varchar(100) default null  comment '账户类别',
  `acct_name` varchar(100) default null comment '账户名称',
  `is_this_bank` varchar(4) default null comment '是否本行账户',
  `acct_br_no` varchar(32) default null comment '开户行号',
  `acct_br_name` varchar(100) default null comment '开户行名称',
  `currency` varchar(4) not null comment '币种',
  `update_date` datetime default null comment '更新日期',
  `update_user` varchar(32) default null comment '更新操作员',
  primary key (`acct_id`)
) comment='会员银行账户信息表';

drop table if exists `va_cust_info`;
create table `va_cust_info` (
  `cust_id` varchar(32) not null comment '会员编号',
  `user_id` varchar(32) not null comment '会员用户编号',
  `cust_name` varchar(100) not null comment '会员姓名',
  `status` varchar(4) not null comment '会员状态 n:正常；c：注销；w:未激活',
  `global_type` varchar(4) default null comment '证件类型',
  `global_id` varchar(32) default null comment '证件号码',
  `tel_no` varchar(32) default null comment '电话号码',
  `email` varchar(32) default null comment '邮箱',
  `address` varchar(200) default null comment '居住地址',
  `plat_id` varchar(32) not null comment '交易平台编号',
  `create_date` datetime default null comment '建立日期',
  `create_user` varchar(32) default null comment '建立操作员',
  `update_date` datetime default null comment '更新日期',
  `update_user` varchar(32) default null comment '更新操作员',
  `cancel_date` datetime default null comment '注销日期',
  `cancel_user` varchar(32) default null comment '注销操作员',
  `effect_date` datetime default null comment '有效期',
  `remark` varchar(200) default null comment '备注',
  `source_type` varchar(4) default null comment '来源',
  `cust_type` varchar(4) default null comment '会员类别',
  `cust_level` varchar(4) default null comment '会员等级',
  `allow_user` varchar(32) default null comment '审批操作员',
  `allow_date` datetime default null comment '审批时间',
  `allow_status` varchar(4) default null comment '审批状态',
  primary key (`cust_id`)
) ;

drop table if exists `va_cust_recharge_seq`;
create table `va_cust_recharge_seq` (
    `cust_recharge_seq` varchar(32) not null comment '会员充值流水号',
    `pub_seq_no` varchar(32) not null comment '公共流水号',
    `order_no` varchar(32) not null comment '业务流水号',
    `plat_id` varchar(32) not null comment '平台编号',
    `payee_id` varchar(32) not null comment '收款方编号',
    `payee_virid` varchar(32) not null comment '收款方虚拟账户编号',
    `payer_acct_no` varchar(32) not null comment '付款方银行账号',
    `tran_type` varchar(4) not null comment '交易类型',
    `tran_amt` decimal(20,8) not null comment '交易金额',
    `tran_date` date not null comment '交易日期',
    primary key (`cust_recharge_seq`)
    ) comment='会员充值记录流水表';

drop table if exists `va_cust_virtual_acct_bal_day`;
create table `va_cust_virtual_acct_bal_day` (
  `cust_virid` varchar(32) not null comment '个人虚拟账户编号',
  `vir_acct_type` varchar(4) not null comment '虚拟账户类型',
  `cust_id` varchar(32) not null comment '个人编号',
  `sys_date` datetime not null comment '系统日期',
  `actural_bal` decimal(20,8) not null comment '当前余额',
  `usable_bal` decimal(20,8) not null comment '可用余额',
  `freeze_bal` decimal(20,8) not null comment '冻结金额',
  `recharge_bal` varchar(32) not null comment '充值金额',
  primary key (`cust_virid`,`cust_id`,`sys_date`)
)comment='个人日终余额信息表' ;

drop table if exists `va_cust_virtual_acct_bal`;
create table `va_cust_virtual_acct_bal` (
  `cust_virid` varchar(32) not null comment '会员虚拟账户编号',
  `vir_acct_type` varchar(16) not null comment '虚拟账户类型',
  `cust_id` varchar(32) not null comment '会员编号',
  `actural_bal` decimal(20,8) not null comment '当前余额',
  `usable_bal` decimal(20,8) not null comment '可用余额',
  `freeze_bal` decimal(20,8) not null comment '冻结金额',
  `recharge_bal` decimal(20,8) default null comment '充值金额',
  primary key (`cust_virid`)
) comment='会员虚拟账户余额信息表';

drop table if exists `va_cust_virtual_acct`;
create table `va_cust_virtual_acct` (
  `cust_virid` varchar(32) not null comment '会员虚拟账户编号',
  `vir_acct_type` varchar(16) not null comment '虚拟账户类型',
  `vir_acct_sort` varchar(16) not null comment '账户归属类别',
  `vir_acct_name` varchar(100) not null comment '虚拟账户名称',
  `cust_id` varchar(32) not null comment '会员编号',
  `cust_name` varchar(100) default null comment '会员名称',
  `user_id` varchar(32) not null comment '会员用户编号',
  `plat_id` varchar(32) not null comment '交易平台编号',
  `plat_name` varchar(100) default null comment '平台名称',
  `currency` varchar(4) not null comment '币种',
  `acct_status` varchar(4) not null comment '账户状态',
  `rel_bank_acct` varchar(32) default null comment '关联银行账户',
  `is_in` varchar(4) default null comment '是否允许转入',
  `is_out` varchar(4) default null comment '是否允许转出',
  `is_total_limit` varchar(4) default null comment '是否有总额限制',
  `total_limit` decimal(17,2) default null comment '总额限制',
  `is_balance_limit` varchar(4) default null comment '是否留备付金',
  `balance_type` varchar(4) default null comment '备付金限额方式',
  `balance_value` decimal(17,2) default null comment '备付金限额数值',
  `open_date` datetime default null comment '开户日期',
  `open_user` varchar(32) default null comment '开户操作员',
  `cancel_date` datetime default null comment '销户日期',
  `cancel_user` varchar(32) default null comment '销户操作员',
  `update_date` datetime default null comment '状态更新日期',
  `source_user` varchar(32) default null comment '状态更新操作员',
  `effect_date` datetime default null comment '有效期',
  primary key (`cust_virid`)
) ;

drop table if exists `va_cust_withdraw_seq`;
create table `va_cust_withdraw_seq` (
    `cust_withdraw_seq` varchar(32) not null comment '会员提现流水号',
    `pub_seq_no` varchar(32) not null comment '公共流水号',
    `order_no` varchar(32) not null comment '业务流水号',
    `plat_id` varchar(32) not null comment '平台编号',
    `payer_id` varchar(32) not null comment '付款方编号',
    `payer_virid` varchar(32) not null comment '付款方虚拟账户编号',
    `payee_acct_no` varchar(32) not null comment '收款方银行账号',
    `tran_type` varchar(4) not null comment '交易类型',
    `tran_amt` decimal(20,8) not null comment '交易金额',
    `tran_date` date not null comment '交易日期',
    primary key (`cust_withdraw_seq`)
    ) comment='会员提现记录流水表';

drop table if exists `va_merch_acct_info`;
create table `va_merch_acct_info` (
  `acct_id` varchar(32) not null comment '账户编号',
  `merch_id` varchar(32) not null comment '商户编号',
  `acct_type` varchar(32) not null comment '账户类型 0：结算账户1：交易平台存管专户2：银行内部账户',
  `acct_category` varchar(32) not null comment '账户类别:0-对公,1-对私',
  `acct_no` varchar(32) not null comment '账户',
  `acct_name` varchar(100) default null comment '账户名称',
  `is_this_bank` varchar(4) default null comment '是否本行账户',
  `acct_br_no` varchar(32) default null comment '开户行号',
  `acct_br_name` varchar(100) default null comment '开户行名称',
  `currency` varchar(4) not null comment '币种',
  `update_date` datetime default null comment '更新日期',
  `update_user` varchar(32) default null comment '更新操作员',
  `acct_status` varchar(4) not null comment '账户状态n：正常f：冻结c：注销',
  primary key (`acct_id`)
) comment='商户银行账户信息表' ;

drop table if exists `va_merch_info`;
create table `va_merch_info` (
  `merch_id` varchar(32) not null comment '商户编号',
  `status` varchar(4) not null comment '商户状态  n：正常  c：注销 ',
  `pay_merch_id` varchar(32) comment '支付商户编号',
  `merch_name` varchar(100) not null comment '商户名称',
  `plat_id` varchar(32) not null comment '交易平台编号',
  `legal_person` varchar(32) not null comment '法人姓名',
  `payment_type` varchar(4) not null comment '结算类型 0-商户手动申请；1-日终自动结算',
  `global_type` varchar(4) default null comment '法人证件类型',
  `global_id` varchar(32) default null comment '法人证件号码',
  `tel_no` varchar(32) default null comment '联系电话',
  `email` varchar(32) default null comment '企业邮箱',
  `address` varchar(200) default null comment '企业地址',
  `url` varchar(200) default null comment '企业网址',
  `create_date` datetime default null comment '建立日期',
  `create_user` varchar(32) default null comment '建立操作员',
  `update_date` datetime default null comment '更新日期',
  `update_user` varchar(32) default null comment '更新操作员',
  `cancel_date` datetime default null comment '注销日期',
  `cancel_user` varchar(32) default null comment '注销操作员',
  `effect_date` datetime default null comment '有效期',
  `source_type` varchar(4) default null comment '来源',
  `remark` varchar(200) default null comment '备注',
  `merch_type` varchar(4) default null comment '商户类别',
  `merch_level` varchar(4) default null comment '商户等级',
  `allow_user` varchar(32) default null comment '审批操作员',
  `allow_date` datetime default null comment '审批时间',
  `allow_status` varchar(4) default null comment '审批状态',
  primary key (`merch_id`)
) ;

drop table if exists `va_merch_recharge_seq`;
create table `va_merch_recharge_seq` (
    `merch_recharge_seq` varchar(32) not null comment '商户充值流水号',
    `pub_seq_no` varchar(32) not null comment '公共流水号',
    `order_no` varchar(32) not null comment '业务流水号',
    `plat_id` varchar(32) not null comment '平台编号',
    `payee_id` varchar(32) not null comment '收款方编号',
    `payee_virid` varchar(32) not null comment '收款方虚拟账户编号',
    `payer_acct_no` varchar(32) not null comment '付款账号',
    `tran_type` varchar(4) not null comment '交易类型',
    `tran_amt` decimal(20,8) not null comment '交易金额',
    `tran_date` date not null comment '交易日期',
    primary key (`merch_recharge_seq`)
    ) comment='商户充值记录流表';

drop table if exists `va_merch_virtual_acct_bal_day`;
create table `va_merch_virtual_acct_bal_day` (
  `merch_virid` varchar(32) not null comment '商户虚拟账户编号',
  `vir_acct_type` varchar(4) not null comment '虚拟账户类型',
  `merch_id` varchar(32) not null comment '商户编号',
  `sys_date` datetime not null comment '系统日期',
  `actural_bal` decimal(20,8) not null comment '当前余额',
  `usable_bal` decimal(20,8) not null comment '可用余额',
  `freeze_bal` decimal(20,8) not null comment '冻结金额',
  `recharge_bal` varchar(32) not null comment '充值金额',
  primary key (`merch_virid`,`sys_date`)
)comment='商户日终余额信息表' ;

drop table if exists `va_merch_virtual_acct_bal`;
create table `va_merch_virtual_acct_bal` (
  `merch_virid` varchar(32) not null comment '商户虚拟账户编号',
  `vir_acct_type` varchar(4) not null comment '虚拟账户类型',
  `merch_id` varchar(32) not null comment '商户编号',
  `actural_bal` decimal(20,8) not null comment '当前余额',
  `usable_bal` decimal(20,8) not null comment '可用余额',
  `freeze_bal` decimal(20,8) not null comment '冻结金额',
  `recharge_bal` decimal(20,8) default null comment '充值金额',
  primary key (`merch_virid`)
)comment='商户虚拟账户余额信息表' ;

drop table if exists `va_merch_virtual_acct`;
create table `va_merch_virtual_acct` (
  `merch_virid` varchar(32) not null comment '商户虚拟账户编号',
  `merch_id` varchar(32) not null comment '商户编号',
  `vir_acct_type` varchar(16) not null comment '账户类型',
  `vir_acct_sort` varchar(16) not null comment '账户归属类别 1：会员 2：商户 3：交易平台',
  `vir_acct_name` varchar(100) not null comment '虚拟账户名称',
  `plat_id` varchar(32) not null comment '交易平台编号',
  `currency` varchar(4) not null comment '币种',
  `acct_status` varchar(4) not null comment '账户状态 n：正常 f：冻结c：注销',
  `rel_bank_acct` varchar(32) not null comment '关联银行账户',
  `is_in` varchar(4) not null comment '是否允许转入y：是 n:否',
  `is_out` varchar(4) not null comment '是否允许转出y：是 n:否',
  `is_total_limit` varchar(4) default null comment '是否有总额限制y：是 n:否',
  `total_limit` decimal(17,2) default null comment '总额限制',
  `is_balance_limit` varchar(4) default null comment '是否留备付金y：是 n:否',
  `balance_type` varchar(4) default null comment '备付金限额方式',
  `balance_value` decimal(17,2) default null comment '备付金限额数值',
  `open_date` datetime default null comment '开户日期',
  `open_user` varchar(32) default null comment '开户操作员',
  `cancel_date` datetime default null comment '销户日期',
  `cancel_user` varchar(32) default null comment '销户操作员',
  `update_date` datetime default null comment '状态更新日期',
  `source_user` varchar(32) default null comment '状态更新操作员',
  `effect_date` datetime default null comment '有效期',
  `merch_name` varchar(100) default null comment '商户名称',
  `plat_name` varchar(32) default null comment '平台名称',
  primary key (`merch_virid`)
) comment='商户虚拟账户信息表';

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

drop table if exists `va_order_seq`;
create table `va_order_seq`(
	`oder_seq_no` varchar(32) comment '订单流水号',
	`order_no`  varchar(32) not null comment '订单号',
	`order_no_old`  varchar(32) not null comment '原订单号',
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

drop table if exists `va_plat_acct_info`;
create table `va_plat_acct_info` (
  `acct_id` varchar(32) not null comment '账户编号',
  `plat_id` varchar(32) not null comment '平台编号',
  `acct_type` varchar(32) not null comment '账户类型 0-平台清算专户,1-平台结算账户,2-商户结算账户,3-支付机构内部账户',
  `acct_category` varchar(32) not null comment '账户类别:0-对公,1-对私',
  `acct_no` varchar(32) not null comment '账户',
  `acct_name` varchar(100) default null comment '账户名称',
  `is_this_bank` varchar(4) default null comment '是否本行账户',
  `acct_br_no` varchar(32) default null comment '开户行号',
  `acct_br_name` varchar(100) default null comment '开户行名称',
  `currency` varchar(4) not null comment '币种',
  `update_date` datetime default null comment '更新日期',
  `update_user` varchar(32) default null comment '更新操作员',
  `acct_status` varchar(4) not null comment '账户状态n：正常f：冻结c：注销',
  primary key (`acct_id`)
) ;

drop table if exists `va_plat_info`;
create table `va_plat_info` (
  `plat_id` varchar(32) not null comment '平台编号',
  `pay_merch_id` varchar(32) not null comment '支付商户编号',
  `plat_name` varchar(100) not null comment '平台名称',
  `legal_person` varchar(32) not null comment '法人姓名',
  `status` varchar(4) not null comment '商户状态  n：正常  c：注销 ',
  `payment_type` varchar(4) not null comment '结算类型 0-手动申请；1-日终自动结算',
  `global_type` varchar(4) default null comment '法人证件类型',
  `global_id` varchar(32) default null comment '法人证件号码',
  `tel_no` varchar(32) default null comment '联系电话',
  `email` varchar(32) default null comment '企业邮箱',
  `address` varchar(200) default null comment '企业地址',
  `url` varchar(200) default null comment '企业网址',
  `create_date` datetime default null comment '建立日期',
  `create_user` varchar(32) default null comment '建立操作员',
  `update_date` datetime default null comment '更新日期',
  `update_user` varchar(32) default null comment '更新操作员',
  `cancel_date` datetime default null comment '注销日期',
  `cancel_user` varchar(32) default null comment '注销操作员',
  `effect_date` datetime default null comment '有效期',
  `source_type` varchar(4) default null comment '来源',
  `remark` varchar(200) default null comment '备注',
  `plat_type` varchar(4) default null comment '平台类别',
  `plat_level` varchar(4) default null comment '平台等级',
  `allow_user` varchar(32) default null comment '审批操作员',
  `allow_date` datetime default null comment '审批时间',
  `allow_status` varchar(4) default null comment '审批状态',
  primary key (`plat_id`)
) ;

drop table if exists `va_plat_virtual_acct_bal_day`;
create table `va_plat_virtual_acct_bal_day` (
  `plat_virid` varchar(32) not null comment '平台虚拟账户编号',
  `vir_acct_type` varchar(4) not null comment '虚拟账户类型',
  `plat_id` varchar(32) not null comment '平台编号',
  `sys_date` datetime not null comment '系统日期',
  `actural_bal` decimal(20,8) not null comment '当前余额',
  `usable_bal` decimal(20,8) not null comment '可用余额',
  `freeze_bal` decimal(20,8) not null comment '冻结金额',
  `recharge_bal` varchar(32) not null comment '充值金额',
  primary key (`plat_virid`,`plat_id`,`sys_date`)
)comment='平台日终余额信息表' ;

drop table if exists `va_plat_virtual_acct_bal`;
create table `va_plat_virtual_acct_bal` (
  `plat_virid` varchar(32) not null comment '平台虚拟账户编号',
  `vir_acct_type` varchar(4) not null comment '虚拟账户类型',
  `plat_id` varchar(32) not null comment '平台编号',
  `actural_bal` decimal(20,8) not null comment '当前余额',
  `usable_bal` decimal(20,8) not null comment '可用余额',
  `freeze_bal` decimal(20,8) not null comment '冻结金额',
  `recharge_bal` decimal(20,8) default null comment '充值金额',
  primary key (`plat_virid`)
) comment '平台虚拟账户信息表';

drop table if exists `va_plat_virtual_acct`;
create table `va_plat_virtual_acct` (
  `plat_virid` varchar(32) not null comment '平台虚拟账户编号',
  `plat_id` varchar(32) not null comment '平台编号',
  `vir_acct_type` varchar(16) not null comment '账户类型',
  `vir_acct_sort` varchar(16) not null comment '账户归属类别 1：会员 2：平台 3：交易平台',
  `vir_acct_name` varchar(100) not null comment '虚拟账户名称',
  `currency` varchar(4) not null comment '币种',
  `acct_status` varchar(4) not null comment '账户状态 n：正常 f：冻结c：注销',
  `rel_bank_acct` varchar(32) not null comment '关联银行账户',
  `is_in` varchar(4) not null comment '是否允许转入y：是 n:否',
  `is_out` varchar(4) not null comment '是否允许转出y：是 n:否',
  `is_total_limit` varchar(4) default null comment '是否有总额限制y：是 n:否',
  `total_limit` decimal(17,2) default null comment '总额限制',
  `is_balance_limit` varchar(4) default null comment '是否留备付金y：是 n:否',
  `balance_type` varchar(4) default null comment '备付金限额方式',
  `balance_value` decimal(17,2) default null comment '备付金限额数值',
  `open_date` datetime default null comment '开户日期',
  `open_user` varchar(32) default null comment '开户操作员',
  `cancel_date` datetime default null comment '销户日期',
  `cancel_user` varchar(32) default null comment '销户操作员',
  `update_date` datetime default null comment '状态更新日期',
  `source_user` varchar(32) default null comment '状态更新操作员',
  `effect_date` datetime default null comment '有效期',
  `plat_name` varchar(32) default null comment '平台名称',
  primary key (`plat_virid`)
) ;

drop table if exists va_sequence_p;
  create table va_sequence_p(
    seq_name varchar(20) comment '名称',
    _length int(2)  comment '设定长度',
    _prefix varchar(8) comment '序列前缀',
    _desc varchar (50) comment '描述',
    primary key (seq_name)
  );

drop table if exists va_sequence_v;
create table va_sequence_v(
  seq_name varchar(20) comment '名称',
  curr_val int comment '当前值',
  _increment int(3) comment '递增步长',
  _desc varchar (50) comment '描述',
  primary key (seq_name)
);

drop table if exists `va_transfer_seq`;
create table `va_transfer_seq`(
	`transfer_seq_no` varchar(32) not null comment '转账流水号',
	`pub_seq_no` varchar(32) not null comment '公共流水号',
	`order_no` varchar(32) not null comment '业务流水号',
	`plat_id` varchar(32) not null comment '平台编号',
	`payer_id`  varchar(32) not null comment '付款方编号',
	`payer_virid`  varchar(32) not null comment '付款方虚拟账户编号',
	`payee_id` varchar(32) not null comment '收款方编号',
	`payee_virid`  varchar(32) not null comment '收款方虚拟账户编号',
	`pay_type` varchar(4) not null comment '支付方式',
	`tran_amt` decimal(10,2) comment '交易金额=使用的可用金额+使用的充值金额',
	`tran_type` varchar(4) not null comment '交易类型',
	`create_date` date  not null comment '订单创建时间',
	primary key(`transfer_seq_no`)
)comment '转账流水表';

drop table if exists `va_user`;
create table `va_user`(
	`user_id` int not null auto_increment,
	`name` varchar(30),
	`age` int default 0,
	primary key(`user_id`)
);

drop table if exists `va_virtual_acct_type`;
create table `va_virtual_acct_type` (
  `vir_acct_type` varchar(16) not null comment '账户类型',
  `vir_acct_desc` varchar(100) not null comment '类型描述',
  `vir_acct_sort` varchar(16) not null comment '账户归属类别',
  `is_in` varchar(4) not null comment '是否允许转入',
  `is_out` varchar(4) not null comment '是否允许转出',
  `is_total_limit` varchar(4) default null comment '是否有总额限制',
  `total_limit` decimal(20,8) default null comment '总额限制',
  `is_balance_limit` varchar(4) default null comment '是否留备付金',
  `balance_type` varchar(4) default null comment '备付金限额方式',
  `balance_value` decimal(20,8) default null comment '备付金限额数值',
  `update_user` varchar(32) default null comment '更新柜员',
  `update_date` varchar(14) default null comment '更新日期',
  `bz` varchar(100) default null comment '备注',
  primary key (`vir_acct_type`)
);

drop table if exists `va_vir_acct_seq`;
create table `va_vir_acct_seq`(
	`vir_acct_seq_no` varchar(32) comment '虚拟账务流水号',
	`pub_seq_no` varchar(32) not null comment '公共流水号',
	`order_no`  varchar(32) not null comment '订单号',
	`order_no_old`  varchar(32) not null comment '原订单号',
	`tran_seq` varchar(32) not null comment '内部系统流水号',
	`plat_id` varchar(32) not null comment '所属平台编号',
	`payer_id`  varchar(32) not null comment '付款方编号',
	`payer_virid`  varchar(32) not null comment '付款方虚拟账户编号',
	`payer_sort` varchar(4)  not null comment '付款方类别',
	`payee_id` varchar(32) not null comment '收款方编号',
	`payee_virid`  varchar(32) not null comment '收款方虚拟账户编号',
	`payee_sort` varchar(4)  not null comment '收款方类别',
	`pay_type` varchar(4) not null comment '支付方式',
	`tran_amt` decimal(10,2) comment '交易金额=使用的可用金额+使用的充值金额',
	`usable_bal` decimal(10,2) comment '使用的可用金额',
	`recharge_bal` decimal(10,2) comment '使用的充值金额',
	`tran_type` varchar(4) not null comment '交易类型',
	`tran_status`  varchar(4) not null comment '交易状态',
	`tran_date` date  not null comment '交易日期',
	`tran_time` date comment '交易时间',
	primary key(vir_acct_seq_no)
)comment '虚拟账务流水表';

