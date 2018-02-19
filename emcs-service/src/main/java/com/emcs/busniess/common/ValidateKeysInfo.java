package com.emcs.busniess.common;

import com.emcs.supers.PubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by Administrator on 2018/2/19.
 */
@Service
public class ValidateKeysInfo extends PubService{
    @Autowired
    ValidateFourKeysInfo validateFourKeysInfo;
    @Autowired
    ValidateTwoKeysInfo validateTwoKeysInfo;

    @Override
    public void process(Map<String, Object> data) {
        validateFourKeysInfo.process(data);
        validateTwoKeysInfo.process(data);
    }
}
