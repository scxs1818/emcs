drop table if exists `va_bind_seq`;
create table `va_bind_seq` (
  `bind_seq_no` varchar(32) not null comment '绑卡流水号',
  `plat_id` varchar(32) not null comment '平台编号',
  `mermber_id` varchar(32) not null comment '会员编号',
  `acct_type` varchar(32) not null comment '账户类型',
  `acct_category` varchar(32) not null comment '账户类别:0-对公,1-对私',
  `acct_no` varchar(32) not null comment '账户',
  `acct_name` varchar(100) default null comment '账户名称',
  `tel_no` varchar(12) not null comment '手机号码',
  `global_id` varchar(20) not null comment '证件号码',
  `create_date` date comment '创建日期',
  primary key (`bind_seq_no`)
) comment='绑卡记录表' ;