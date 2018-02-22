drop table if exists  `cm_system`;
create table `cm_system` (
   `run_date`             varchar(8)                           not null comment '系统日期',
   `prev_run_date`       varchar(8)                           not null comment '前一工作日',
   `core_run_date`       varchar(8)                             comment '三方日期',
   `ser_status_flag`	varchar(2)		       not null comment '服务状态标志:n-关闭,y-开启'
)comment '系统表';