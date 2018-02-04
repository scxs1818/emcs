package com.emcs;


import com.emcs.serviceImpl.busniess.register.CustRegister;
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
public class testTcCust {
    @Autowired
    CustRegister merchRegister;

    @Test
    public void testTcPlat(){
        System.out.printf("commom#############=");
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("pay_merch_id","129");
        map.put("plat_name","测试平台");
        map.put("legal_person","122");
        map.put("plat_id","10001");
        map.put("status","N");
        map.put("merch_name","百货超市");
        map.put("payment_type","0");
        map.put("acct_id","0");
        map.put("acct_type","0");
        map.put("acct_no","122");
        map.put("cust_name","小李");
        map.put("currency","RMB");
        map.put("vir_acct_id","00001");
        map.put("user_id","u001");
        map.put("vir_acct_type","1");
        map.put("vir_acct_sort","00001");
        map.put("vir_acct_name","00001");
        map.put("acct_status","N");
        map.put("rel_bank_acct","1111111111111111");
        map.put("is_in","Y");
        map.put("is_out","Y");
        CommonResult coomon =merchRegister.doService(map);
        System.out.printf("commom="+coomon);
    }
}
