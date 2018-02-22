package com.emcs.busniess.common;

import com.emcs.Constant.BusiConstant;
import com.emcs.supers.PubServiceY;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * Created by Administrator on 2018/2/4.
 */
@Service
public class InsertCmAcctTranSeq extends PubServiceY {
    @Transactional(propagation= Propagation.NOT_SUPPORTED)
    @Override
    public void process(Map<String, Object> data) {
//        数据封装带补充
        data.put("tran_seq_no",oneSelect.getNextVal(BusiConstant.Quence.TRAN_SEQ_NO.val()));
        oneDML.insertCmAcctTranSeq(data);
    }
}