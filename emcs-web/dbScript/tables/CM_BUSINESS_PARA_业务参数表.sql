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