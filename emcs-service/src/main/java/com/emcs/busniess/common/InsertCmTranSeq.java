package com.emcs.busniess.common;

import com.emcs.Constant.BusiConstant;
import com.emcs.supers.PubService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Map;
@Service
public class InsertCmTranSeq extends PubService{
    @Override
    public void process(Map<String, Object> data) {
        data.put("tran_date","20180215");
        data.put("channel_date","20180215");
        data.put("tran_status","w");
        data.put("pub_seq_no",oneSelect.getNextVal(BusiConstant.Quence.CM_TRAN_SEQ.val()));
        oneDML.insertCmTranSeq(data);
    }
}