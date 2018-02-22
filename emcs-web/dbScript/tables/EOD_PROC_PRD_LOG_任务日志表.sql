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