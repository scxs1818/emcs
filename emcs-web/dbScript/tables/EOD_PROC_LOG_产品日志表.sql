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
