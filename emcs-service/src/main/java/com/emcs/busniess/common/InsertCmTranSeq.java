package com.emcs.busniess.common;

import com.emcs.Constant.BusiConstant;
import com.emcs.supers.PubService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;
@Service
public class InsertCmTranSeq extends PubService{
    @Override
    public void process(Map<String, Object> param) {
        param.put("tran_date","20180215");
        param.put("channel_date","20180215");
        param.put("tran_status","w");
        String seqNo = oneSelect.getNextVal(BusiConstant.Quence.CM_TRAN_SEQ.gname());
        param.put("pub_seq_no","pub"+seqNo);
        oneDML.insertCmTranSeq(param);
    }
}
