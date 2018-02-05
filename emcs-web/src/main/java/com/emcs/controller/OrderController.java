package com.emcs.controller;

import com.emcs.serviceImpl.busniess.register.CustRegister;
import com.emcs.serviceImpl.busniess.register.MerchRegister;
import com.emcs.serviceImpl.busniess.register.PlatformRegister;
import com.emcs.util.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 订单
 * Created by Administrator on 2018/2/4.8
 * */
 @RequestMapping("/order")
 @RestController
public class OrderController {

    @Autowired
    PlatformRegister plat;
    @Autowired
    MerchRegister merch;
    @Autowired
    CustRegister cust;

    @RequestMapping("/apply")
    public CommonResult purchaseApply(@RequestParam Map<String,Object> param) {
        return  merch.doService(param);
    }

    @RequestMapping("/revoke")
    public CommonResult purchaseRevoke(@RequestParam Map<String,Object> param) {
        return  cust.doService(param);
    }

    @RequestMapping("/confirm")
    public CommonResult purchaseConfirm(@RequestParam Map<String,Object> param) {
        return  cust.doService(param);
    }
}
