package com.emcs.controller;

import com.emcs.busniess.order.PurchaseApply;
import com.emcs.busniess.order.PurchaseConfirm;
import com.emcs.busniess.order.PurchaseRevoke;
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
    PurchaseApply apply;
    @Autowired
    PurchaseRevoke revoke;
    @Autowired
    PurchaseConfirm confirm;

    @RequestMapping("/apply")
    public CommonResult purchaseApply(@RequestParam Map<String,Object> param) {
        return  apply.doService(param);
    }

    @RequestMapping("/revoke")
    public CommonResult purchaseRevoke(@RequestParam Map<String,Object> param) {
        return  revoke.doService(param);
    }

    @RequestMapping("/confirm")
    public CommonResult purchaseConfirm(@RequestParam Map<String,Object> param) {
        return  confirm.doService(param);
    }
}
