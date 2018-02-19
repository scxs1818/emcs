package com.emcs.busniess.common;

import com.emcs.supers.PubService;

import java.util.Map;

/**
 * Created by Administrator on 2018/2/19.
 */
public class SendBindCard extends PubService {
    @Override
    public void process(Map<String, Object> data) {
        log.info("绑卡成功...");
    }
}
