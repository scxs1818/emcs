package com.emcs.controller;
import com.emcs.mapper.OneTableSelectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/2/2.
 */
@RestController
public class TestOneTableController {
    @Autowired
    OneTableSelectMapper OTSS;
    @RequestMapping("selectVirAcctTypeInfo")
    public List<Map<String, Object>> selectAll() {
        return  OTSS.selectVirAcctType();
    }
}
