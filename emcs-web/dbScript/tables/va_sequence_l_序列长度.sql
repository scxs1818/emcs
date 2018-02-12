drop table if exists va_sequence_l;
  create table va_sequence_l(
      SEQ_NAME VARCHAR(20) COMMENT '名称',
    _LENGTH INT(2)  COMMENT '设定长度',
    PRIMARY KEY (SEQ_NAME)
  );