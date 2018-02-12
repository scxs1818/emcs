package com.emcs.busniess.common;

import com.emcs.supers.PubService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * Created by Administrator on 2018/2/5.
 */
@Transactional
@Service
public class InsertVaVirAcctSeq extends PubService {
    @Override
    public void process(Map<String, Object> param) {
        //封装带补充
        oneDML.insertVaVirAcctSeq(param);
    }
}
