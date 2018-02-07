DROP TABLE IF EXISTS `CM_BUSINESS_PARA`;
CREATE TABLE `CM_BUSINESS_PARA` (
   `PARA_KEY`             VARCHAR(50)                   NOT NULL COMMENT '业务参数代码',
   `PARA_VALUE`           VARCHAR(32)                   NOT NULL COMMENT '参数值',
   `PARA_DESC`            VARCHAR(100)                  NOT NULL COMMENT '参数描述',
   `PARA_STATUS`          VARCHAR(4)                    NOT NULL COMMENT '参数状态',
   `UPDATE_DATE`          DATE                          NULL COMMENT '参数维护日期',
   `UPDATE_USER`          VARCHAR(32)                   NULL COMMENT '操作员',
   PRIMARY KEY  (`PARA_KEY`)
)COMMENT '业务参数表';
