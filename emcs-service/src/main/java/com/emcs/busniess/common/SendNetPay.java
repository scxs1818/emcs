package com.emcs.busniess.common;

import com.emcs.supers.PubService;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by Administrator on 2018/2/4.
 */
@Service
public class SendNetPay extends PubService {
    @Override
    public void process(Map<String, Object> param) {
        //待补充
        log.info("调核心互联网成功");
    }
}
