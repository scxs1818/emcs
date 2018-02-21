drop table if exists `eod_proc_rule`;
create table `eod_proc_rule`  (
   `prd_no`               varchar(30 )               not null comment '产品编号',
   `step_no`              varchar(2 )                not null comment '步骤号',
   `class_bean`         varchar(100 ) comment '要执行的服务',
   `step_desc`            varchar(100 )  comment '步骤描述',
   `status`              varchar (6 )  comment '1-启用,0-禁用',
   primary key(`prd_no`,`step_no`)
)comment '任务制定表';