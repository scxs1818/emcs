package com.emcs;


import com.emcs.service.busniess.PlatRegisterService;
import com.emcs.service.common.VaPlatAccInfoService;
import com.emcs.util.CommonResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
//@WebAppConfiguration
public class TcPlatTesting02 {
	@Autowired
	VaPlatAccInfoService platRegisterService;
	
	@Test
	public void testTcPlat02(){
//
//		Gson gson =new Gson();
//		TcPlatRequestParams param = new TcPlatRequestParams();
//		TcPlatInfo tcPlatInfo = new TcPlatInfo();
//		tcPlatInfo.setPay_merch_id("20001000000001");
//		tcPlatInfo.setPlat_name("测试平台1");
//		tcPlatInfo.setLegal_person("张三");
//		tcPlatInfo.setStatus("N");
//		tcPlatInfo.setAdress("上海市");
//		tcPlatInfo.setPayment_type("0");
//		tcPlatInfo.setAllow_user("1");
//		param.setTcPlatInfo(tcPlatInfo);
//
//		List<TcPlatAcctInfo>tcPlatAcctInfo = new ArrayList<TcPlatAcctInfo>();
//		TcPlatAcctInfo tcPlatAcctInfo1 = new TcPlatAcctInfo();
//		tcPlatAcctInfo1.setAcct_type("0");
//		tcPlatAcctInfo1.setAcct_no("12345");
//		tcPlatAcctInfo1.setAcct_ccy("RMB");
//		tcPlatAcctInfo1.setAcct_status("F");
//		TcPlatAcctInfo tcPlatAcctInfo2 = new TcPlatAcctInfo();
//		tcPlatAcctInfo2.setAcct_type("1");
//		tcPlatAcctInfo2.setAcct_no("1234555");
//		tcPlatAcctInfo2.setAcct_ccy("RMB");
//		tcPlatAcctInfo2.setAcct_status("F");
//		tcPlatAcctInfo.add(tcPlatAcctInfo1);
//		tcPlatAcctInfo.add(tcPlatAcctInfo2);
//		param.setTcPlatAcctInfo(tcPlatAcctInfo);
//
//
//		TcPlatVirtualAcct tcPlatVirtualAcct = new TcPlatVirtualAcct();
//		tcPlatVirtualAcct.setAcct_ccy("RMB");
//		tcPlatVirtualAcct.setPay_merch_id("123");
//		tcPlatVirtualAcct.setVir_acct_type("SS");
//		tcPlatVirtualAcct.setVir_acct_name("虚拟账户");
//		tcPlatVirtualAcct.setVir_acct_sort("1");
//		tcPlatVirtualAcct.setAcct_status("N");
//		tcPlatVirtualAcct.setIs_in("Y");
//		tcPlatVirtualAcct.setIs_out("Y");
//		param.setTcPlatVirtualAcct(tcPlatVirtualAcct);
//
//		TcPlatVirtualAcctBal tcPlatVirtualAcctBal =new TcPlatVirtualAcctBal();
//		tcPlatVirtualAcctBal.setVir_acct_type("s");
//		tcPlatVirtualAcctBal.setActural_bal(new BigDecimal(12000));
//		tcPlatVirtualAcctBal.setUsable_bal(new BigDecimal(12));
//		tcPlatVirtualAcctBal.setFreeze_bal(new BigDecimal(120));
//		param.setTcPlatVirtualAcctBal(tcPlatVirtualAcctBal);
//		String params =gson.toJson(param);
//		System.out.println("params"+params);
//		CommonResult result = registerPlatService.register(params);
//		System.out.println("result=="+result);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("pay_merch_id","123");
		map.put("plat_name","测试平台");
		map.put("legal_person","122");
		map.put("status","N");
		map.put("payment_type","0");
		map.put("acct_id","0");
		map.put("acct_type","0");
		map.put("acct_no","122");
		map.put("acct_ccy","RMB");
		map.put("vir_acct_id","00001");
		map.put("vir_acct_type","00001");
		map.put("vir_acct_sort","00001");
		map.put("vir_acct_name","00001");
		map.put("acct_status","N");
		map.put("rel_bank_acct","1111111111111111");
		map.put("is_in","Y");
		map.put("is_out","Y");
		map.put("actural_bal",1233);
		map.put("usable_bal",13);
		map.put("freeze_bal",900);
		int coomon =platRegisterService.insertVaPlatAccInfo(map);
		System.out.printf("commom="+coomon);










	}
	
}
