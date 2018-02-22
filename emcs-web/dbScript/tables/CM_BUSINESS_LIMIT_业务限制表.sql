drop table if exists `cm_business_limit`;
create table `cm_business_limit` (
   `limit_id`             varchar(16)                   not null comment '限制编号',
   `business_code`        varchar(16)                   not null comment '业务编码',
   `limit_type`          varchar(4)                   not null comment '限制类型:1-单笔限额,2-单日允许交易最大次数,3-单日总限额',
   `limit_value`         decimal(20,8)                  null   comment '单笔限额',
   `effect_begin`         date                           null comment '生效日期',
   `effect_end`           date                           null comment '失效日期',
   `effect_flag`          varchar(4)                    null comment '生效标志y:生效n:失效',
   `update_date`          varchar(14)                   null comment '更新日期',
   `update_user`          varchar(32)                   null comment '更新操作员',
   primary key  (`limit_id`)
) comment '业务限制表';