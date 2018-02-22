drop table if exists `cm_business_code`;
create table   `cm_business_code`(
`business_code`        varchar(11)        not null comment '业务编码',
`business_name`        varchar(50)  comment '业务编码名称',
`super_business_code`  varchar(11)  comment '上级业务编码',
`code_levels`          varchar(1)  comment '编码层次:包括1级、2级和3级',
`charge_sign`          varchar(1)  comment '收费标志，y是 n否',
`limit_sign`           varchar(1)  comment '限额标志，y是 n否',
`business_type`        varchar(2)  comment '业务编码类型:01-查询 02-金融 03-管理类 04-限制类 05-验证类 06-代理业务类  07-冲正类 08-消息通知类  99-其它',
`is_financial`     varchar(1)  comment '是否账务交易:y-是,n-不是',
`time_control_flag`    varchar(1)  comment '时间控制标志：y-限制；n-不限制',
primary key (`business_code`)
) comment '业务码表';