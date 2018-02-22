package com.emcs.busniess.common;

import com.emcs.supers.PubServiceY;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by Administrator on 2018/2/19.
 */
@Service
public class ValidateTwoKeysInfo extends PubServiceY {

    @Override
    public void process(Map<String, Object> data) {
        log.info("校验二要素成功");
    }
}
