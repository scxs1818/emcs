truncate table schedule_job;
insert into  schedule_job values ('com.emcs.eod.DaySwich','日切','0:00','20:00','3','1','va','com.emcs.eod.DaySwich','1');
insert into  schedule_job values ('com.emcs.eod.AutoConfirmOrders','批量订单确认','0:00','20:00','3','1','va','com.emcs.eod.AutoConfirmOrders','1');
insert into  schedule_job values ('com.emcs.eod.FromRecharBalToUsableBal','日终余额转移','0:00','20:00','3','1','va','com.emcs.eod.FromRecharBalToUsableBal','1');
insert into  schedule_job values ('com.emcs.eod.AutoEod','自动日终','0:00','20:00','3','1','va','com.emcs.eod.AutoEod','1');
