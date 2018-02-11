package com.emcs.controller;

import com.emcs.busniess.recharge.MerberRecharge;
import com.emcs.util.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 充值
 * Created by Administrator on 2018/2/4.
 */

@RequestMapping("/recharge")
@RestController
public class RechargeController {
    @Autowired
    MerberRecharge recharge;

    @RequestMapping("/merber")
    public CommonResult merberRecharge(@RequestParam Map<String,Object> param) {
        return  recharge.doService(param);
    }

}
