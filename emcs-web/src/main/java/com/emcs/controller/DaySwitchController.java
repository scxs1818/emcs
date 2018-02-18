package com.emcs.controller;

import com.emcs.eod.DaySwich;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2018/2/18.
 */
@RequestMapping("/eod")
@Controller
public class DaySwitchController {
    @Autowired
    DaySwich ds;
    @RequestMapping("switch")
    public void switchDay(){
        Map<String,Object> data = new HashMap<>();
        ds.process(data);
    }


}