DROP TABLE IF EXISTS  `CM_SYSTEM`;
CREATE TABLE `CM_SYSTEM` (
   `RUN_DATE`             DATE                           NOT NULL COMMENT '系统日期',
   `PREV_RUN_DATE`        DATE                           NOT NULL COMMENT '前一工作日',
   `CORE_RUN_DATE`        DATE                             COMMENT '三方日期',
   `SER_STATUS_FLAG`	VARCHAR(2)		       NOT NULL COMMENT '服务状态标志:N-关闭,Y-开启'
)COMMENT '系统表';