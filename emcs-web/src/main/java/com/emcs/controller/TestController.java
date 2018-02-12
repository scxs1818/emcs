package com.emcs.controller;

import com.emcs.busniess.other.MakeInsertSql;
import com.emcs.busniess.test.TestCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2018/2/11.
 */
@RequestMapping("/test")
@Controller
public class TestController {

    @Autowired
    TestCache testCache;
    @RequestMapping("cache")
    public void testCache(){
        Map<String,Object> param = new HashMap<>();
        testCache.process(param);
    }
    @Autowired
    MakeInsertSql mis;
    @RequestMapping("makeInsert")
    public void testMakeInsertSql(Map<String,Object> param){
        mis.process(param);
    }
}
