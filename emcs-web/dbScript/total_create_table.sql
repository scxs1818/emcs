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

DROP TABLE IF EXISTS `CM_BUSINESS_PARA`;
CREATE TABLE `CM_BUSINESS_PARA` (
   `TRAN_TYPE`            VARCHAR(4)                   NOT NULL COMMENT '交易类型',
   `PARA_KEY`             VARCHAR(50)                   NOT NULL COMMENT '业务参数代码',
   `PARA_VALUE`           VARCHAR(32)                   NOT NULL COMMENT '参数值',
   `PARA_DESC`            VARCHAR(100)                  NOT NULL COMMENT '参数描述',
   `PARA_STATUS`          VARCHAR(4)                    NOT NULL COMMENT '参数状态',
   `UPDATE_DATE`          DATE                          NULL COMMENT '参数维护日期',
   `UPDATE_USER`          VARCHAR(32)                   NULL COMMENT '操作员',
   PRIMARY KEY  (`TRAN_TYPE`,`PARA_KEY`)
)COMMENT '业务参数表';

DROP TABLE IF EXISTS  `CM_SYSTEM`;
CREATE TABLE `CM_SYSTEM` (
   `RUN_DATE`             varchar(8)                           NOT NULL COMMENT '系统日期',
   `PREV_RUN_DATE`       varchar(8)                           NOT NULL COMMENT '前一工作日',
   `CORE_RUN_DATE`       varchar(8)                             COMMENT '三方日期',
   `SER_STATUS_FLAG`	VARCHAR(2)		       NOT NULL COMMENT '服务状态标志:N-关闭,Y-开启'
)COMMENT '系统表';

DROP TABLE  IF EXISTS `CM_TRAN_SEQ` ;
CREATE TABLE `CM_TRAN_SEQ`(
  `PUB_SEQ_NO`           VARCHAR (32) COMMENT '交易公共流水号' ,
  `ORDER_NO`           VARCHAR(32) COMMENT '订单号' ,
  `TRAN_DATE`           DATE                      NOT NULL COMMENT '交易日期',
  `TRAN_TIME`           VARCHAR(10) COMMENT '交易时间',
  `CHANNEL_SEQ_NO`   VARCHAR(32) COMMENT '渠道流水号',
  `CHANNEL_DATE`          DATE COMMENT '渠道日期',
  `CHANNEL_CODE`        VARCHAR(20) COMMENT '渠道关键编码' ,
  `SOURCE_TYPE`         VARCHAR(16) COMMENT '渠道类型代码',
  `TRAN_TYPE`           VARCHAR(20) COMMENT '交易类型',
  `BUSINESS_CODE`       VARCHAR(11) COMMENT '业务类型编码',
  `USER_ID`             VARCHAR(32) COMMENT '用户ID',
  `PLAT_ID`	      VARCHAR(8) COMMENT '交易平台编号',
  `MERCH_ID`	      VARCHAR(16) COMMENT '商户编号',
	`CUST_ID`				VARCHAR(18) COMMENT '会员编号',
  `ACCT_NO`	      VARCHAR(30) COMMENT '账户',
  `CURRENCY`                 VARCHAR(4) COMMENT '币种代码',
  `TRAN_AMT`            DECIMAL(12,2) COMMENT '交易金额',
  `BRANCH_ID`           VARCHAR(16) COMMENT '交易机构',
  `MSG_TYPE`            VARCHAR(10) COMMENT 'MBSD报文类型',
  `MSG_CODE`            VARCHAR(10) COMMENT 'MBSD报文编码',
  `TRAN_STATUS`         VARCHAR(2)  COMMENT '交易状态代码:01-成功,02-失败',
  `RET_CODE`            VARCHAR(16) COMMENT '响应码',
  `FAIL_REASON`         VARCHAR(512) COMMENT '失败原因',
   PRIMARY KEY(`PUB_SEQ_NO`)
) COMMENT '交易流水记录表';

drop table if exists `eod_proc_log`;
create table `eod_proc_log`  (
   `proc_log_seq`             varchar(32)              not null comment '日终日志流水号',
   `tran_time`       varchar(9) comment '时间',
   `tran_date`            varchar(8) comment '日期',
   `prd_no`               varchar(4) comment '产品编号',
   `status`               varchar(6) comment '执行结果',
   `remark`               varchar(100) comment '备注',
   primary key (`proc_log_seq`)
) comment '产品日志表';

drop table  if exists `eod_proc_prd_log`;
create table `eod_proc_prd_log`(
  `proc_log_seq`      varchar(32)               not null comment '日终日志流水号',
  `prd_no`        varchar(4)                not null comment '产品编号',
  `tran_date`     varchar(8)                not null comment '日期',
  `step_no`       varchar(2)                not null comment '任务步骤号',
  `step_desc`     varchar(100) comment '任务步骤描述',
  `class_bean`  varchar(100) comment '要执行的服务',
  `status`        varchar(6) comment '执行结果',
  `remark`        varchar(100) comment '备注',
  primary key (`tran_date`,`step_no`,`prd_no`)
)comment '日终任务执行记录表';

drop table if exists `eod_proc_rule`;
create table `eod_proc_rule`  (
   `prd_no`               varchar(30 )               not null comment '产品编号',
   `step_no`              varchar(2 )                not null comment '步骤号',
   `class_bean`         varchar(100 ) comment '要执行的服务',
   `step_desc`            varchar(100 )  comment '步骤描述',
   `status`              varchar (6 )  comment '1-启用,0-禁用',
   primary key(`prd_no`,`step_no`)
)comment '任务制定表';

drop table if exists `va_bind_seq`;
create table `va_bind_seq` (
  `bind_seq_no` varchar(32) not null  comment '绑卡流水号',
  `plat_id` varchar(32) comment '平台编号',
  `member_id` varchar(32) comment '会员编号',
  `acct_type` varchar(32) comment '账户类型',
  `acct_category` varchar(32) comment '账户类别:0-对公,1-对私',
  `acct_no` varchar(32) comment '账户',
  `acct_name` varchar(100) default null comment '账户名称',
  `tel_no` varchar(12) comment '手机号码',
  `global_id` varchar(20) comment '证件号码',
  `create_date` date comment '创建日期',
  primary key (`bind_seq_no`)
) comment='绑卡记录表' ;

DROP TABLE IF EXISTS `VA_CUST_ACCT_INFO`;
CREATE TABLE `VA_CUST_ACCT_INFO` (
  `ACCT_ID` VARCHAR(32) NOT NULL COMMENT '账户编号',
  `CUST_ID` VARCHAR(32) NOT NULL COMMENT '会员编号',
  `ACCT_TYPE` VARCHAR(32) NOT NULL COMMENT '账户类型 0：结算账户1：交易平台存管专户2：银行内部账户',
  `ACCT_NO` VARCHAR(32) NOT NULL COMMENT '银行账号',
  `ACCT_STATUS` VARCHAR(4) NOT NULL COMMENT '账户状态N：正常F：冻结C：注销',
  `TEL_NO` VARCHAR(12) NOT NULL COMMENT '手机号码',
  `ACCT_CATEGORY` VARCHAR(100) DEFAULT NULL  COMMENT '账户类别:1-对公,2-对私',
  `ACCT_NAME` VARCHAR(100) DEFAULT NULL COMMENT '账户名称',
  `IS_THIS_BANK` VARCHAR(4) DEFAULT NULL COMMENT '是否本行账户',
  `BANK_NO` VARCHAR(32) DEFAULT NULL COMMENT '开户行号',
  `BANK_NAME` VARCHAR(100) DEFAULT NULL COMMENT '开户行名称',
  `CURRENCY` VARCHAR(4) NOT NULL COMMENT '币种',
  `UPDATE_DATE` DATETIME DEFAULT NULL COMMENT '更新日期',
  `UPDATE_USER` VARCHAR(32) DEFAULT NULL COMMENT '更新操作员',
  PRIMARY KEY (`ACCT_ID`)
) COMMENT='会员银行账户信息表';

DROP TABLE IF EXISTS `VA_CUST_INFO`;
CREATE TABLE `VA_CUST_INFO` (
  `CUST_ID` VARCHAR(32) NOT NULL COMMENT '会员编号',
  `USER_ID` VARCHAR(32) NOT NULL COMMENT '会员用户编号',
  `CUST_NAME` VARCHAR(100) NOT NULL COMMENT '会员姓名',
  `STATUS` VARCHAR(4) NOT NULL COMMENT '会员状态 N:正常；C：注销；W:未激活',
  `GLOBAL_TYPE` VARCHAR(4) DEFAULT NULL COMMENT '证件类型',
  `GLOBAL_ID` VARCHAR(32) DEFAULT NULL COMMENT '证件号码',
  `TEL_NO` VARCHAR(32) DEFAULT NULL COMMENT '电话号码',
  `EMAIL` VARCHAR(32) DEFAULT NULL COMMENT '邮箱',
  `ADDRESS` VARCHAR(200) DEFAULT NULL COMMENT '居住地址',
  `PLAT_ID` VARCHAR(32) NOT NULL COMMENT '交易平台编号',
  `CREATE_DATE` DATETIME DEFAULT NULL COMMENT '建立日期',
  `CREATE_USER` VARCHAR(32) DEFAULT NULL COMMENT '建立操作员',
  `UPDATE_DATE` DATETIME DEFAULT NULL COMMENT '更新日期',
  `UPDATE_USER` VARCHAR(32) DEFAULT NULL COMMENT '更新操作员',
  `CANCEL_DATE` DATETIME DEFAULT NULL COMMENT '注销日期',
  `CANCEL_USER` VARCHAR(32) DEFAULT NULL COMMENT '注销操作员',
  `EFFECT_DATE` DATETIME DEFAULT NULL COMMENT '有效期',
  `REMARK` VARCHAR(200) DEFAULT NULL COMMENT '备注',
  `SOURCE_TYPE` VARCHAR(4) DEFAULT NULL COMMENT '来源',
  `CUST_TYPE` VARCHAR(4) DEFAULT NULL COMMENT '会员类别',
  `CUST_LEVEL` VARCHAR(4) DEFAULT NULL COMMENT '会员等级',
  `ALLOW_USER` VARCHAR(32) DEFAULT NULL COMMENT '审批操作员',
  `ALLOW_DATE` DATETIME DEFAULT NULL COMMENT '审批时间',
  `ALLOW_STATUS` VARCHAR(4) DEFAULT NULL COMMENT '审批状态',
  PRIMARY KEY (`CUST_ID`)
) ;

DROP TABLE IF EXISTS `VA_CUST_RECHARGE_SEQ`;
CREATE TABLE `VA_CUST_RECHARGE_SEQ` (
    `CUST_RECHARGE_SEQ` VARCHAR(32) NOT NULL COMMENT '会员充值流水号',
    `PUB_SEQ_NO` VARCHAR(32) NOT NULL COMMENT '公共流水号',
    `ORDER_NO` VARCHAR(32) NOT NULL COMMENT '业务流水号',
    `PLAT_ID` VARCHAR(32) NOT NULL COMMENT '平台编号',
    `PAYEE_ID` VARCHAR(32) NOT NULL COMMENT '收款方编号',
    `PAYEE_VIRID` VARCHAR(32) NOT NULL COMMENT '收款方虚拟账户编号',
    `PAYER_ACCT_NO` VARCHAR(32) NOT NULL COMMENT '付款方银行账号',
    `TRAN_TYPE` VARCHAR(4) NOT NULL COMMENT '交易类型',
    `TRAN_AMT` DECIMAL(20,8) NOT NULL COMMENT '交易金额',
    `TRAN_DATE` DATE NOT NULL COMMENT '交易日期',
    PRIMARY KEY (`CUST_RECHARGE_SEQ`)
    ) COMMENT='会员充值记录流水表';

DROP TABLE IF EXISTS `VA_CUST_VIRTUAL_ACCT_BAL_DAY`;
CREATE TABLE `VA_CUST_VIRTUAL_ACCT_BAL_DAY` (
  `CUST_VIRID` VARCHAR(32) NOT NULL COMMENT '个人虚拟账户编号',
  `VIR_ACCT_TYPE` VARCHAR(4) NOT NULL COMMENT '虚拟账户类型',
  `CUST_ID` VARCHAR(32) NOT NULL COMMENT '个人编号',
  `SYS_DATE` DATETIME NOT NULL COMMENT '系统日期',
  `ACTURAL_BAL` DECIMAL(20,8) NOT NULL COMMENT '当前余额',
  `USABLE_BAL` DECIMAL(20,8) NOT NULL COMMENT '可用余额',
  `FREEZE_BAL` DECIMAL(20,8) NOT NULL COMMENT '冻结金额',
  `RECHARGE_BAL` VARCHAR(32) NOT NULL COMMENT '充值金额',
  PRIMARY KEY (`CUST_VIRID`,`CUST_ID`,`SYS_DATE`)
)COMMENT='个人日终余额信息表' ;

DROP TABLE IF EXISTS `VA_CUST_VIRTUAL_ACCT_BAL`;
CREATE TABLE `VA_CUST_VIRTUAL_ACCT_BAL` (
  `CUST_VIRID` VARCHAR(32) NOT NULL COMMENT '会员虚拟账户编号',
  `VIR_ACCT_TYPE` VARCHAR(16) NOT NULL COMMENT '虚拟账户类型',
  `CUST_ID` VARCHAR(32) NOT NULL COMMENT '会员编号',
  `ACTURAL_BAL` DECIMAL(20,8) NOT NULL COMMENT '当前余额',
  `USABLE_BAL` DECIMAL(20,8) NOT NULL COMMENT '可用余额',
  `FREEZE_BAL` DECIMAL(20,8) NOT NULL COMMENT '冻结金额',
  `RECHARGE_BAL` DECIMAL(20,8) DEFAULT NULL COMMENT '充值金额',
  PRIMARY KEY (`CUST_VIRID`)
) COMMENT='会员虚拟账户余额信息表';

DROP TABLE IF EXISTS `VA_CUST_VIRTUAL_ACCT`;
CREATE TABLE `VA_CUST_VIRTUAL_ACCT` (
  `CUST_VIRID` VARCHAR(32) NOT NULL COMMENT '会员虚拟账户编号',
  `VIR_ACCT_TYPE` VARCHAR(16) NOT NULL COMMENT '虚拟账户类型',
  `VIR_ACCT_SORT` VARCHAR(16) NOT NULL COMMENT '账户归属类别',
  `VIR_ACCT_NAME` VARCHAR(100) NOT NULL COMMENT '虚拟账户名称',
  `CUST_ID` VARCHAR(32) NOT NULL COMMENT '会员编号',
  `CUST_NAME` VARCHAR(100) DEFAULT NULL COMMENT '会员名称',
  `USER_ID` VARCHAR(32) NOT NULL COMMENT '会员用户编号',
  `PLAT_ID` VARCHAR(32) NOT NULL COMMENT '交易平台编号',
  `PLAT_NAME` VARCHAR(100) DEFAULT NULL COMMENT '平台名称',
  `CURRENCY` VARCHAR(4) NOT NULL COMMENT '币种',
  `ACCT_STATUS` VARCHAR(4) NOT NULL COMMENT '账户状态',
  `acct_no` VARCHAR(32) DEFAULT NULL COMMENT '关联银行账户',
  `IS_IN` VARCHAR(4) DEFAULT NULL COMMENT '是否允许转入',
  `IS_OUT` VARCHAR(4) DEFAULT NULL COMMENT '是否允许转出',
  `IS_TOTAL_LIMIT` VARCHAR(4) DEFAULT NULL COMMENT '是否有总额限制',
  `TOTAL_LIMIT` DECIMAL(17,2) DEFAULT NULL COMMENT '总额限制',
  `IS_BALANCE_LIMIT` VARCHAR(4) DEFAULT NULL COMMENT '是否留备付金',
  `BALANCE_TYPE` VARCHAR(4) DEFAULT NULL COMMENT '备付金限额方式',
  `BALANCE_VALUE` DECIMAL(17,2) DEFAULT NULL COMMENT '备付金限额数值',
  `OPEN_DATE` DATETIME DEFAULT NULL COMMENT '开户日期',
  `OPEN_USER` VARCHAR(32) DEFAULT NULL COMMENT '开户操作员',
  `CANCEL_DATE` DATETIME DEFAULT NULL COMMENT '销户日期',
  `CANCEL_USER` VARCHAR(32) DEFAULT NULL COMMENT '销户操作员',
  `UPDATE_DATE` DATETIME DEFAULT NULL COMMENT '状态更新日期',
  `SOURCE_USER` VARCHAR(32) DEFAULT NULL COMMENT '状态更新操作员',
  `EFFECT_DATE` DATETIME DEFAULT NULL COMMENT '有效期',
  PRIMARY KEY (`CUST_VIRID`)
) ;

DROP TABLE IF EXISTS `VA_CUST_WITHDRAW_SEQ`;
CREATE TABLE `VA_CUST_WITHDRAW_SEQ` (
    `CUST_WITHDRAW_SEQ` VARCHAR(32) NOT NULL COMMENT '会员提现流水号',
    `PUB_SEQ_NO` VARCHAR(32) NOT NULL COMMENT '公共流水号',
    `ORDER_NO` VARCHAR(32) NOT NULL COMMENT '业务流水号',
    `PLAT_ID` VARCHAR(32) NOT NULL COMMENT '平台编号',
    `PAYER_ID` VARCHAR(32) NOT NULL COMMENT '付款方编号',
    `PAYER_VIRID` VARCHAR(32) NOT NULL COMMENT '付款方虚拟账户编号',
    `PAYEE_ACCT_NO` VARCHAR(32) NOT NULL COMMENT '收款方银行账号',
    `TRAN_TYPE` VARCHAR(4) NOT NULL COMMENT '交易类型',
    `TRAN_AMT` DECIMAL(20,8) NOT NULL COMMENT '交易金额',
    `TRAN_DATE` DATE NOT NULL COMMENT '交易日期',
    PRIMARY KEY (`CUST_WITHDRAW_SEQ`)
    ) COMMENT='会员提现记录流水表';

DROP TABLE IF EXISTS `VA_MERCH_ACCT_INFO`;
CREATE TABLE `VA_MERCH_ACCT_INFO` (
  `ACCT_ID` VARCHAR(32) NOT NULL COMMENT '账户编号',
  `MERCH_ID` VARCHAR(32) NOT NULL COMMENT '商户编号',
  `ACCT_TYPE` VARCHAR(32) NOT NULL COMMENT '账户类型 0-结算账户,1-交易平台存管专户,2-银行内部账户',
  `ACCT_CATEGORY` VARCHAR(32) NOT NULL COMMENT '账户类别:0-对公,1-对私',
  `ACCT_NO` VARCHAR(32) NOT NULL COMMENT '账户',
  `ACCT_STATUS` VARCHAR(4) NOT NULL COMMENT '账户状态N：正常F：冻结C：注销',
  `TEL_NO` VARCHAR(12) NOT NULL COMMENT '手机号码',
  `ACCT_NAME` VARCHAR(100) DEFAULT NULL COMMENT '账户名称',
  `IS_THIS_BANK` VARCHAR(4) DEFAULT NULL COMMENT '是否本行账户',
  `BANK_NO` VARCHAR(32) DEFAULT NULL COMMENT '开户行号',
  `BANK_NAME` VARCHAR(100) DEFAULT NULL COMMENT '开户行名称',
  `CURRENCY` VARCHAR(4) NOT NULL COMMENT '币种',
  `UPDATE_DATE` DATETIME DEFAULT NULL COMMENT '更新日期',
  `UPDATE_USER` VARCHAR(32) DEFAULT NULL COMMENT '更新操作员',
  PRIMARY KEY (`ACCT_ID`)
) COMMENT='商户银行账户信息表' ;

DROP TABLE IF EXISTS `VA_MERCH_INFO`;
CREATE TABLE `VA_MERCH_INFO` (
  `MERCH_ID` VARCHAR(32) NOT NULL COMMENT '商户编号',
  `STATUS` VARCHAR(4) NOT NULL COMMENT '商户状态  N：正常  C：注销 ',
  `PAY_MERCH_ID` VARCHAR(32) COMMENT '支付商户编号',
  `MERCH_NAME` VARCHAR(100) NOT NULL COMMENT '商户名称',
  `PLAT_ID` VARCHAR(32) NOT NULL COMMENT '交易平台编号',
  `LEGAL_PERSON` VARCHAR(32) NOT NULL COMMENT '法人姓名',
  `PAYMENT_TYPE` VARCHAR(4) NOT NULL COMMENT '结算类型 0-商户手动申请；1-日终自动结算',
  `GLOBAL_TYPE` VARCHAR(4) DEFAULT NULL COMMENT '法人证件类型',
  `GLOBAL_ID` VARCHAR(32) DEFAULT NULL COMMENT '法人证件号码',
  `TEL_NO` VARCHAR(32) DEFAULT NULL COMMENT '联系电话',
  `EMAIL` VARCHAR(32) DEFAULT NULL COMMENT '企业邮箱',
  `ADDRESS` VARCHAR(200) DEFAULT NULL COMMENT '企业地址',
  `URL` VARCHAR(200) DEFAULT NULL COMMENT '企业网址',
  `CREATE_DATE` DATETIME DEFAULT NULL COMMENT '建立日期',
  `CREATE_USER` VARCHAR(32) DEFAULT NULL COMMENT '建立操作员',
  `UPDATE_DATE` DATETIME DEFAULT NULL COMMENT '更新日期',
  `UPDATE_USER` VARCHAR(32) DEFAULT NULL COMMENT '更新操作员',
  `CANCEL_DATE` DATETIME DEFAULT NULL COMMENT '注销日期',
  `CANCEL_USER` VARCHAR(32) DEFAULT NULL COMMENT '注销操作员',
  `EFFECT_DATE` DATETIME DEFAULT NULL COMMENT '有效期',
  `SOURCE_TYPE` VARCHAR(4) DEFAULT NULL COMMENT '来源',
  `REMARK` VARCHAR(200) DEFAULT NULL COMMENT '备注',
  `MERCH_TYPE` VARCHAR(4) DEFAULT NULL COMMENT '商户类别',
  `MERCH_LEVEL` VARCHAR(4) DEFAULT NULL COMMENT '商户等级',
  `ALLOW_USER` VARCHAR(32) DEFAULT NULL COMMENT '审批操作员',
  `ALLOW_DATE` DATETIME DEFAULT NULL COMMENT '审批时间',
  `ALLOW_STATUS` VARCHAR(4) DEFAULT NULL COMMENT '审批状态',
  PRIMARY KEY (`MERCH_ID`)
) ;

DROP TABLE IF EXISTS `VA_MERCH_RECHARGE_SEQ`;
CREATE TABLE `VA_MERCH_RECHARGE_SEQ` (
    `MERCH_RECHARGE_SEQ` VARCHAR(32) NOT NULL COMMENT '商户充值流水号',
    `PUB_SEQ_NO` VARCHAR(32) NOT NULL COMMENT '公共流水号',
    `ORDER_NO` VARCHAR(32) NOT NULL COMMENT '业务流水号',
    `PLAT_ID` VARCHAR(32) NOT NULL COMMENT '平台编号',
    `PAYEE_ID` VARCHAR(32) NOT NULL COMMENT '收款方编号',
    `PAYEE_VIRID` VARCHAR(32) NOT NULL COMMENT '收款方虚拟账户编号',
    `PAYER_ACCT_NO` VARCHAR(32) NOT NULL COMMENT '付款账号',
    `TRAN_TYPE` VARCHAR(4) NOT NULL COMMENT '交易类型',
    `TRAN_AMT` DECIMAL(20,8) NOT NULL COMMENT '交易金额',
    `TRAN_DATE` DATE NOT NULL COMMENT '交易日期',
    PRIMARY KEY (`MERCH_RECHARGE_SEQ`)
    ) COMMENT='商户充值记录流表';

DROP TABLE IF EXISTS `VA_MERCH_VIRTUAL_ACCT_BAL_DAY`;
CREATE TABLE `VA_MERCH_VIRTUAL_ACCT_BAL_DAY` (
  `MERCH_VIRID` VARCHAR(32) NOT NULL COMMENT '商户虚拟账户编号',
  `VIR_ACCT_TYPE` VARCHAR(4) NOT NULL COMMENT '虚拟账户类型',
  `MERCH_ID` VARCHAR(32) NOT NULL COMMENT '商户编号',
  `SYS_DATE` DATETIME NOT NULL COMMENT '系统日期',
  `ACTURAL_BAL` DECIMAL(20,8) NOT NULL COMMENT '当前余额',
  `USABLE_BAL` DECIMAL(20,8) NOT NULL COMMENT '可用余额',
  `FREEZE_BAL` DECIMAL(20,8) NOT NULL COMMENT '冻结金额',
  `RECHARGE_BAL` VARCHAR(32) NOT NULL COMMENT '充值金额',
  PRIMARY KEY (`MERCH_VIRID`,`SYS_DATE`)
)COMMENT='商户日终余额信息表' ;

DROP TABLE IF EXISTS `VA_MERCH_VIRTUAL_ACCT_BAL`;
CREATE TABLE `VA_MERCH_VIRTUAL_ACCT_BAL` (
  `MERCH_VIRID` VARCHAR(32) NOT NULL COMMENT '商户虚拟账户编号',
  `VIR_ACCT_TYPE` VARCHAR(4) NOT NULL COMMENT '虚拟账户类型',
  `MERCH_ID` VARCHAR(32) NOT NULL COMMENT '商户编号',
  `ACTURAL_BAL` DECIMAL(20,8) NOT NULL COMMENT '当前余额',
  `USABLE_BAL` DECIMAL(20,8) NOT NULL COMMENT '可用余额',
  `FREEZE_BAL` DECIMAL(20,8) NOT NULL COMMENT '冻结金额',
  `RECHARGE_BAL` DECIMAL(20,8) DEFAULT NULL COMMENT '充值金额',
  PRIMARY KEY (`MERCH_VIRID`)
)COMMENT='商户虚拟账户余额信息表' ;

DROP TABLE IF EXISTS `VA_MERCH_VIRTUAL_ACCT`;
CREATE TABLE `VA_MERCH_VIRTUAL_ACCT` (
  `MERCH_VIRID` VARCHAR(32) NOT NULL COMMENT '商户虚拟账户编号',
  `MERCH_ID` VARCHAR(32) NOT NULL COMMENT '商户编号',
  `VIR_ACCT_TYPE` VARCHAR(16) NOT NULL COMMENT '账户类型',
  `VIR_ACCT_SORT` VARCHAR(16) NOT NULL COMMENT '账户归属类别 1：会员 2：商户 3：交易平台',
  `VIR_ACCT_NAME` VARCHAR(100) NOT NULL COMMENT '虚拟账户名称',
  `PLAT_ID` VARCHAR(32) NOT NULL COMMENT '交易平台编号',
  `CURRENCY` VARCHAR(4) NOT NULL COMMENT '币种',
  `ACCT_STATUS` VARCHAR(4) NOT NULL COMMENT '账户状态 N：正常 F：冻结C：注销',
  `acct_no` VARCHAR(32) NOT NULL COMMENT '关联银行账户',
  `IS_IN` VARCHAR(4) NOT NULL COMMENT '是否允许转入Y：是 N:否',
  `IS_OUT` VARCHAR(4) NOT NULL COMMENT '是否允许转出Y：是 N:否',
  `IS_TOTAL_LIMIT` VARCHAR(4) DEFAULT NULL COMMENT '是否有总额限制Y：是 N:否',
  `TOTAL_LIMIT` DECIMAL(17,2) DEFAULT NULL COMMENT '总额限制',
  `IS_BALANCE_LIMIT` VARCHAR(4) DEFAULT NULL COMMENT '是否留备付金Y：是 N:否',
  `BALANCE_TYPE` VARCHAR(4) DEFAULT NULL COMMENT '备付金限额方式',
  `BALANCE_VALUE` DECIMAL(17,2) DEFAULT NULL COMMENT '备付金限额数值',
  `OPEN_DATE` DATETIME DEFAULT NULL COMMENT '开户日期',
  `OPEN_USER` VARCHAR(32) DEFAULT NULL COMMENT '开户操作员',
  `CANCEL_DATE` DATETIME DEFAULT NULL COMMENT '销户日期',
  `CANCEL_USER` VARCHAR(32) DEFAULT NULL COMMENT '销户操作员',
  `UPDATE_DATE` DATETIME DEFAULT NULL COMMENT '状态更新日期',
  `SOURCE_USER` VARCHAR(32) DEFAULT NULL COMMENT '状态更新操作员',
  `EFFECT_DATE` DATETIME DEFAULT NULL COMMENT '有效期',
  `MERCH_NAME` VARCHAR(100) DEFAULT NULL COMMENT '商户名称',
  `PLAT_NAME` VARCHAR(32) DEFAULT NULL COMMENT '平台名称',
  PRIMARY KEY (`MERCH_VIRID`)
) COMMENT='商户虚拟账户信息表';

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
	`pub_seq_no` varchar(32) not null comment '公共流水号',
	`plat_id` varchar(32) not null comment '平台编号',
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

DROP TABLE IF EXISTS `VA_PLAT_ACCT_INFO`;
CREATE TABLE `VA_PLAT_ACCT_INFO` (
  `ACCT_ID` VARCHAR(32) NOT NULL COMMENT '账户编号',
  `PLAT_ID` VARCHAR(32) NOT NULL COMMENT '平台编号',
  `ACCT_TYPE` VARCHAR(32) NOT NULL COMMENT '账户类型 0-平台清算专户,1-平台结算账户,2-商户结算账户,3-支付机构内部账户',
  `ACCT_CATEGORY` VARCHAR(32) NOT NULL COMMENT '账户类别:0-对公,1-对私',
  `ACCT_NO` VARCHAR(32) NOT NULL COMMENT '账户',
  `ACCT_STATUS` VARCHAR(4) NOT NULL COMMENT '账户状态N：正常F：冻结C：注销',
  `TEL_NO` VARCHAR(12) NOT NULL COMMENT '手机号码',
  `ACCT_NAME` VARCHAR(100) DEFAULT NULL COMMENT '账户名称',
  `IS_THIS_BANK` VARCHAR(4) DEFAULT NULL COMMENT '是否本行账户',
  `BANK_NO` VARCHAR(32) DEFAULT NULL COMMENT '开户行号',
  `BANK_NAME` VARCHAR(100) DEFAULT NULL COMMENT '开户行名称',
  `CURRENCY` VARCHAR(4) NOT NULL COMMENT '币种',
  `UPDATE_DATE` DATETIME DEFAULT NULL COMMENT '更新日期',
  `UPDATE_USER` VARCHAR(32) DEFAULT NULL COMMENT '更新操作员',
  PRIMARY KEY (`ACCT_ID`)
) ;

DROP TABLE IF EXISTS `VA_PLAT_INFO`;
CREATE TABLE `VA_PLAT_INFO` (
  `PLAT_ID` VARCHAR(32) NOT NULL COMMENT '平台编号',
  `PAY_MERCH_ID` VARCHAR(32) NOT NULL COMMENT '支付商户编号',
  `PLAT_NAME` VARCHAR(100) NOT NULL COMMENT '平台名称',
  `LEGAL_PERSON` VARCHAR(32) NOT NULL COMMENT '法人姓名',
  `STATUS` VARCHAR(4) NOT NULL COMMENT '商户状态  N：正常  C：注销 ',
  `PAYMENT_TYPE` VARCHAR(4) NOT NULL COMMENT '结算类型 0-手动申请；1-日终自动结算',
  `GLOBAL_TYPE` VARCHAR(4) DEFAULT NULL COMMENT '法人证件类型',
  `GLOBAL_ID` VARCHAR(32) DEFAULT NULL COMMENT '法人证件号码',
  `TEL_NO` VARCHAR(32) DEFAULT NULL COMMENT '联系电话',
  `EMAIL` VARCHAR(32) DEFAULT NULL COMMENT '企业邮箱',
  `ADDRESS` VARCHAR(200) DEFAULT NULL COMMENT '企业地址',
  `URL` VARCHAR(200) DEFAULT NULL COMMENT '企业网址',
  `CREATE_DATE` DATETIME DEFAULT NULL COMMENT '建立日期',
  `CREATE_USER` VARCHAR(32) DEFAULT NULL COMMENT '建立操作员',
  `UPDATE_DATE` DATETIME DEFAULT NULL COMMENT '更新日期',
  `UPDATE_USER` VARCHAR(32) DEFAULT NULL COMMENT '更新操作员',
  `CANCEL_DATE` DATETIME DEFAULT NULL COMMENT '注销日期',
  `CANCEL_USER` VARCHAR(32) DEFAULT NULL COMMENT '注销操作员',
  `EFFECT_DATE` DATETIME DEFAULT NULL COMMENT '有效期',
  `SOURCE_TYPE` VARCHAR(4) DEFAULT NULL COMMENT '来源',
  `REMARK` VARCHAR(200) DEFAULT NULL COMMENT '备注',
  `PLAT_TYPE` VARCHAR(4) DEFAULT NULL COMMENT '平台类别',
  `PLAT_LEVEL` VARCHAR(4) DEFAULT NULL COMMENT '平台等级',
  `ALLOW_USER` VARCHAR(32) DEFAULT NULL COMMENT '审批操作员',
  `ALLOW_DATE` DATETIME DEFAULT NULL COMMENT '审批时间',
  `ALLOW_STATUS` VARCHAR(4) DEFAULT NULL COMMENT '审批状态',
  PRIMARY KEY (`PLAT_ID`)
) ;

DROP TABLE IF EXISTS `VA_PLAT_VIRTUAL_ACCT_BAL_DAY`;
CREATE TABLE `VA_PLAT_VIRTUAL_ACCT_BAL_DAY` (
  `PLAT_VIRID` VARCHAR(32) NOT NULL COMMENT '平台虚拟账户编号',
  `VIR_ACCT_TYPE` VARCHAR(4) NOT NULL COMMENT '虚拟账户类型',
  `PLAT_ID` VARCHAR(32) NOT NULL COMMENT '平台编号',
  `SYS_DATE` DATETIME NOT NULL COMMENT '系统日期',
  `ACTURAL_BAL` DECIMAL(20,8) NOT NULL COMMENT '当前余额',
  `USABLE_BAL` DECIMAL(20,8) NOT NULL COMMENT '可用余额',
  `FREEZE_BAL` DECIMAL(20,8) NOT NULL COMMENT '冻结金额',
  `RECHARGE_BAL` VARCHAR(32) NOT NULL COMMENT '充值金额',
  PRIMARY KEY (`PLAT_VIRID`,`PLAT_ID`,`SYS_DATE`)
)COMMENT='平台日终余额信息表' ;

DROP TABLE IF EXISTS `VA_PLAT_VIRTUAL_ACCT_BAL`;
CREATE TABLE `VA_PLAT_VIRTUAL_ACCT_BAL` (
  `PLAT_VIRID` VARCHAR(32) NOT NULL COMMENT '平台虚拟账户编号',
  `VIR_ACCT_TYPE` VARCHAR(4) NOT NULL COMMENT '虚拟账户类型',
  `PLAT_ID` VARCHAR(32) NOT NULL COMMENT '平台编号',
  `ACTURAL_BAL` DECIMAL(20,8) NOT NULL COMMENT '当前余额',
  `USABLE_BAL` DECIMAL(20,8) NOT NULL COMMENT '可用余额',
  `FREEZE_BAL` DECIMAL(20,8) NOT NULL COMMENT '冻结金额',
  `RECHARGE_BAL` DECIMAL(20,8) DEFAULT NULL COMMENT '充值金额',
  PRIMARY KEY (`PLAT_VIRID`)
) COMMENT '平台虚拟账户信息表';

DROP TABLE IF EXISTS `VA_PLAT_VIRTUAL_ACCT`;
CREATE TABLE `VA_PLAT_VIRTUAL_ACCT` (
  `PLAT_VIRID` VARCHAR(32) NOT NULL COMMENT '平台虚拟账户编号',
  `PLAT_ID` VARCHAR(32) NOT NULL COMMENT '平台编号',
  `VIR_ACCT_TYPE` VARCHAR(16) NOT NULL COMMENT '账户类型',
  `VIR_ACCT_SORT` VARCHAR(16) NOT NULL COMMENT '账户归属类别 1：会员 2：平台 3：交易平台',
  `VIR_ACCT_NAME` VARCHAR(100) NOT NULL COMMENT '虚拟账户名称',
  `CURRENCY` VARCHAR(4) NOT NULL COMMENT '币种',
  `ACCT_STATUS` VARCHAR(4) NOT NULL COMMENT '账户状态 N：正常 F：冻结C：注销',
  `acct_no` VARCHAR(32) NOT NULL COMMENT '关联银行账户',
  `IS_IN` VARCHAR(4) NOT NULL COMMENT '是否允许转入Y：是 N:否',
  `IS_OUT` VARCHAR(4) NOT NULL COMMENT '是否允许转出Y：是 N:否',
  `IS_TOTAL_LIMIT` VARCHAR(4) DEFAULT NULL COMMENT '是否有总额限制Y：是 N:否',
  `TOTAL_LIMIT` DECIMAL(17,2) DEFAULT NULL COMMENT '总额限制',
  `IS_BALANCE_LIMIT` VARCHAR(4) DEFAULT NULL COMMENT '是否留备付金Y：是 N:否',
  `BALANCE_TYPE` VARCHAR(4) DEFAULT NULL COMMENT '备付金限额方式',
  `BALANCE_VALUE` DECIMAL(17,2) DEFAULT NULL COMMENT '备付金限额数值',
  `OPEN_DATE` DATETIME DEFAULT NULL COMMENT '开户日期',
  `OPEN_USER` VARCHAR(32) DEFAULT NULL COMMENT '开户操作员',
  `CANCEL_DATE` DATETIME DEFAULT NULL COMMENT '销户日期',
  `CANCEL_USER` VARCHAR(32) DEFAULT NULL COMMENT '销户操作员',
  `UPDATE_DATE` DATETIME DEFAULT NULL COMMENT '状态更新日期',
  `SOURCE_USER` VARCHAR(32) DEFAULT NULL COMMENT '状态更新操作员',
  `EFFECT_DATE` DATETIME DEFAULT NULL COMMENT '有效期',
  `PLAT_NAME` VARCHAR(32) DEFAULT NULL COMMENT '平台名称',
  PRIMARY KEY (`PLAT_VIRID`)
) ;

DROP TABLE IF EXISTS VA_SEQUENCE_P;
  CREATE TABLE VA_SEQUENCE_P(
    SEQ_NAME VARCHAR(20) COMMENT '名称',
    _LENGTH INT(2)  COMMENT '设定长度',
    _PREFIX VARCHAR(8) comment '序列前缀',
    _DESC VARCHAR (50) COMMENT '描述',
    PRIMARY KEY (SEQ_NAME)
  );

DROP TABLE IF EXISTS VA_SEQUENCE_V;
CREATE TABLE VA_SEQUENCE_V(
  SEQ_NAME VARCHAR(20) COMMENT '名称',
  CURR_VAL INT COMMENT '当前值',
  _INCREMENT INT(3) COMMENT '递增步长',
  _DESC VARCHAR (50) COMMENT '描述',
  PRIMARY KEY (SEQ_NAME)
);

DROP TABLE IF EXISTS `VA_TRANSFER_SEQ`;
CREATE TABLE `VA_TRANSFER_SEQ`(
	`TRANSFER_SEQ_NO` VARCHAR(32) NOT NULL COMMENT '转账流水号',
	`PUB_SEQ_NO` VARCHAR(32) NOT NULL COMMENT '公共流水号',
	`ORDER_NO` VARCHAR(32) NOT NULL COMMENT '业务流水号',
	`PLAT_ID` VARCHAR(32) NOT NULL COMMENT '平台编号',
	`PAYER_ID`  VARCHAR(32) NOT NULL COMMENT '付款方编号',
	`PAYER_VIRID`  VARCHAR(32) NOT NULL COMMENT '付款方虚拟账户编号',
	`PAYEE_ID` VARCHAR(32) NOT NULL COMMENT '收款方编号',
	`PAYEE_VIRID`  VARCHAR(32) NOT NULL COMMENT '收款方虚拟账户编号',
	`PAY_TYPE` VARCHAR(4) NOT NULL COMMENT '支付方式',
	`TRAN_AMT` DECIMAL(10,2) COMMENT '交易金额=使用的可用金额+使用的充值金额',
	`TRAN_TYPE` VARCHAR(4) NOT NULL COMMENT '交易类型',
	`CREATE_DATE` DATE  NOT NULL COMMENT '订单创建时间',
	PRIMARY KEY(`TRANSFER_SEQ_NO`)
)COMMENT '转账流水表';

DROP TABLE IF EXISTS `VA_USER`;
CREATE TABLE `VA_USER`(
	`USER_ID` INT NOT NULL AUTO_INCREMENT,
	`NAME` VARCHAR(30),
	`AGE` INT DEFAULT 0,
	PRIMARY KEY(`USER_ID`)
);

DROP TABLE IF EXISTS `VA_VIRTUAL_ACCT_TYPE`;
CREATE TABLE `VA_VIRTUAL_ACCT_TYPE` (
  `VIR_ACCT_TYPE` VARCHAR(16) NOT NULL COMMENT '账户类型',
  `VIR_ACCT_DESC` VARCHAR(100) NOT NULL COMMENT '类型描述',
  `VIR_ACCT_SORT` VARCHAR(16) NOT NULL COMMENT '账户归属类别',
  `IS_IN` VARCHAR(4) NOT NULL COMMENT '是否允许转入',
  `IS_OUT` VARCHAR(4) NOT NULL COMMENT '是否允许转出',
  `IS_TOTAL_LIMIT` VARCHAR(4) DEFAULT NULL COMMENT '是否有总额限制',
  `TOTAL_LIMIT` DECIMAL(20,8) DEFAULT NULL COMMENT '总额限制',
  `IS_BALANCE_LIMIT` VARCHAR(4) DEFAULT NULL COMMENT '是否留备付金',
  `BALANCE_TYPE` VARCHAR(4) DEFAULT NULL COMMENT '备付金限额方式',
  `BALANCE_VALUE` DECIMAL(20,8) DEFAULT NULL COMMENT '备付金限额数值',
  `UPDATE_USER` VARCHAR(32) DEFAULT NULL COMMENT '更新柜员',
  `UPDATE_DATE` VARCHAR(14) DEFAULT NULL COMMENT '更新日期',
  `BZ` VARCHAR(100) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`VIR_ACCT_TYPE`)
);

DROP TABLE IF EXISTS `VA_VIR_ACCT_SEQ`;
CREATE TABLE `VA_VIR_ACCT_SEQ`(
	`VIR_ACCT_SEQ_NO` VARCHAR(32) COMMENT '虚拟账务流水号',
	`PUB_SEQ_NO` VARCHAR(32) NOT NULL COMMENT '公共流水号',
	`ORDER_NO`  VARCHAR(32) NOT NULL COMMENT '订单号',
	`ORDER_NO_OLD`  VARCHAR(32) NOT NULL COMMENT '原订单号',
	`TRAN_SEQ` VARCHAR(32) NOT NULL COMMENT '内部系统流水号',
	`PLAT_ID` VARCHAR(32) NOT NULL COMMENT '所属平台编号',
	`PAYER_ID`  VARCHAR(32) NOT NULL COMMENT '付款方编号',
	`PAYER_VIRID`  VARCHAR(32) NOT NULL COMMENT '付款方虚拟账户编号',
	`PAYER_SORT` varchar(4)  NOT NULL COMMENT '付款方类别',
	`PAYEE_ID` VARCHAR(32) NOT NULL COMMENT '收款方编号',
	`PAYEE_VIRID`  VARCHAR(32) NOT NULL COMMENT '收款方虚拟账户编号',
	`PAYEE_SORT` varchar(4)  NOT NULL COMMENT '收款方类别',
	`PAY_TYPE` VARCHAR(4) NOT NULL COMMENT '支付方式',
	`TRAN_AMT` DECIMAL(10,2) COMMENT '交易金额=使用的可用金额+使用的充值金额',
	`USABLE_BAL` DECIMAL(10,2) COMMENT '使用的可用金额',
	`RECHARGE_BAL` DECIMAL(10,2) COMMENT '使用的充值金额',
	`TRAN_TYPE` VARCHAR(4) NOT NULL COMMENT '交易类型',
	`TRAN_STATUS`  VARCHAR(4) NOT NULL COMMENT '交易状态',
	`TRAN_DATE` DATE  NOT NULL COMMENT '交易日期',
	`TRAN_TIME` DATE COMMENT '交易时间',
	PRIMARY KEY(VIR_ACCT_SEQ_NO)
)COMMENT '虚拟账务流水表';

