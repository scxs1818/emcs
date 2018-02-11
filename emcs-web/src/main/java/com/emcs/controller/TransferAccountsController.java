package com.emcs.controller;

import com.emcs.busniess.transferAccounts.TransferAccounts;
import com.emcs.util.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 转账
 * Created by Administrator on 2018/2/5.
 */
@RequestMapping("/move")
@RestController
public class TransferAccountsController {
    @Autowired
    TransferAccounts transferAccounts;
    @RequestMapping("/vir")
    public CommonResult transferAccounts(@RequestParam Map<String,Object> param) {
        return  transferAccounts.doService(param);
    }

}
