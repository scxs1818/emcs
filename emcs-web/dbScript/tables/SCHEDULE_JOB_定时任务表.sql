drop table if exists `schedule_job`;
create table `schedule_job`(
  `job_code`       varchar(50)              not null comment '任务代码',
  `job_name`       varchar(100)             not null comment '任务名称',
  `start_time`     varchar(8)               not null comment '启动时间',
  `end_time`       varchar(8)               not null comment '终止时间',
  `interval_time` integer                        not null comment '时间间隔（秒）',
  `exec_mode`      varchar(1)               not null comment '执行模式 0未执行完不会并发执行，1未执行完会并发执行',
  `exec_node`      varchar(20)              not null comment '执行节点',
  `class_bean`     varchar(50)              not null comment '服务',
  `valid_flag`     varchar(1)               not null comment '启用标志 0-关闭 1-启用',
  primary key(`job_code`)
)comment '定时任务配置表';