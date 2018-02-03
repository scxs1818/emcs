package com.emcs;


import com.emcs.serviceImpl.busniess.PlatformRegister;
import com.emcs.util.CommonResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class TcPlatTesting {
	@Autowired
	PlatformRegister platRegister;

	@Test
	public void testTcPlat(){
		System.out.printf("commom#############=");
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("pay_merch_id","129");
		map.put("plat_name","测试平台");
		map.put("legal_person","122");
		map.put("status","N");
		map.put("payment_type","0");
		map.put("acct_id","0");
		map.put("acct_type","0");
		map.put("acct_no","122");
		map.put("currency","RMB");
		map.put("vir_acct_id","00001");
		map.put("vir_acct_type","1");
		map.put("vir_acct_sort","00001");
		map.put("vir_acct_name","00001");
		map.put("acct_status","N");
		map.put("rel_bank_acct","1111111111111111");
		map.put("is_in","Y");
		map.put("is_out","Y");
		map.put("actural_bal",1233);
		map.put("usable_bal",13);
		map.put("freeze_bal",900);
		CommonResult coomon =platRegister.doService(map);
		System.out.printf("commom="+coomon);
	}
}
