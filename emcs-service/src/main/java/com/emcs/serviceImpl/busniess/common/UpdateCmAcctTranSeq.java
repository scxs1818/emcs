package com.emcs.serviceImpl.busniess.common;

import com.emcs.Super.PubService;
import com.emcs.Super.ServiceTransactionalY;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * Created by Administrator on 2018/2/4.
 */
@Transactional
@Service
public class UpdateCmAcctTranSeq extends PubService{
    @Override
    public void process(Map<String, Object> param) {
//        数据封装带补充
        oneDML.updateCmAcctTranSeq(param);
    }
}
