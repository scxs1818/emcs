DROP TABLE IF EXISTS va_sequence;
CREATE TABLE va_sequence(
  SEQ_NAME VARCHAR(20) COMMENT '名称',
  CURR_VAL INT COMMENT '当前值',
  _INCREMENT INT(3) COMMENT '递增步长',
  _LENGTH INT(2)  COMMENT '设定长度',
  _DESC VARCHAR (50) COMMENT '描述',
  PRIMARY KEY (SEQ_NAME)
);