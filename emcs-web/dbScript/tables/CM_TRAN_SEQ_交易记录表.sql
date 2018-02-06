DROP TABLE  IF EXISTS CM_TRAN_SEQ ;
CREATE TABLE CM_TRAN_SEQ
(
  TRAN_SEQ           INT UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT '交易流水号' ,
  TRAN_DATE           DATE                      NOT NULL COMMENT '交易日期',
  TRAN_TIME           VARCHAR(10) COMMENT '交易时间',
  SYS_DATE       DATE COMMENT '系统时间' ,
  CHANNEL_CODE        VARCHAR(20) COMMENT '渠道关键编码' ,
  SOURCE_TYPE         VARCHAR(16) COMMENT '渠道类型代码',
  TRAN_TYPE           VARCHAR(20) COMMENT '交易类型',
  BUSINESS_CODE       VARCHAR(11) COMMENT '业务类型编码',
  USER_ID             VARCHAR(32) COMMENT '用户ID',
  PLAT_ID	      VARCHAR(32) COMMENT '交易平台编号',
  MERCH_ID	      VARCHAR(32) COMMENT '商户编号',
	CUST_ID				VARCHAR(32) COMMENT '会员编号',
  ACCT_NO	      VARCHAR(50) COMMENT '账户',
  CURRENCY                 VARCHAR(4) COMMENT '币种代码',
  TRAN_AMT            DECIMAL(12,2) COMMENT '交易金额',
  BRANCH_ID           VARCHAR(16) COMMENT '交易机构',
  MSG_TYPE            VARCHAR(10) COMMENT 'MBSD报文类型',
  MSG_CODE            VARCHAR(10) COMMENT 'MBSD报文编码',
  TRAN_STATUS         VARCHAR(2)          NOT NULL COMMENT '交易状态代码:01-成功,02-失败',
  RET_CODE            VARCHAR(16) COMMENT '平台响应码',
  RESP_CODE           VARCHAR(16) COMMENT '返回渠道响应码',
  FAIL_REASON         VARCHAR(512) COMMENT '失败原因'
) COMMENT '交易流水记录表';