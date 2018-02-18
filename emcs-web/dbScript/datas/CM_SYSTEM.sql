TRUNCATE TABLE CM_SYSTEM;
INSERT INTO `CM_SYSTEM` VALUES ((select date_format(now(),'%Y%m%d')),(select date_format(now(),'%Y%m%d') FROM DUAL),NULL,'Y');