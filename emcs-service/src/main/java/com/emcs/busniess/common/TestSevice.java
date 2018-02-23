package com.emcs.busniess.common;

import com.emcs.mapper.OneTableSelectMapper;
import com.emcs.pub.runtime.core.Logger;
import com.emcs.pub.runtime.core.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Map;

/**
 * Created by Administrator on 2018/2/23.
 */
@Service
public class TestSevice {
    protected Logger log = LoggerFactory.getLogger(TestSevice.class);
    @Resource
    OneTableSelectMapper oneSelect;
    public static TestSevice testSevice;
    @PostConstruct
    public void init() {
        System.out.println("userMapper init");
        testSevice = this;
    }
    public void process() {
//        数据封装带补充
//        WebApplicationContext wac = ContextLoader.getCurrentWebApplicationContext();
//        try{
//            OneTableSelectMapper oneSelect = (OneTableSelectMapper) ContextLoader.getCurrentWebApplicationContext().getBean("oneTableSelectMapper");
//            oneSelect.selectVaMerchInfo(null);
//
//        }catch (Exception e){
//
//        }
        if(testSevice==null){
            log.info("is null");


        }else{
        log.info("not null");
            testSevice.oneSelect.selectVaMerchInfo(null);
        }
        testSevice = null;
    }
}
