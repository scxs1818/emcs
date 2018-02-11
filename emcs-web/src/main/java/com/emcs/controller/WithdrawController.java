package com.emcs.controller;

import com.emcs.busniess.withDraw.MerberWithdraw;
import com.emcs.util.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 提现
 * Created by Administrator on 2018/2/4.
 */

@RequestMapping("/withdraw")
@RestController
public class WithdrawController {
    @Autowired
    MerberWithdraw withdraw;

    @RequestMapping("/merber")
    public CommonResult merberWithdraw(@RequestParam Map<String,Object> param) {
        return  withdraw.doService(param);
    }
}