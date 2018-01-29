package com.emcs.controller;

import com.emcs.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class TestController {
    @Autowired
    TestService testService;

    @RequestMapping("select")
    public List<Map<String,Object>> selectAll(){
       return testService.selectAll();
    }
}
