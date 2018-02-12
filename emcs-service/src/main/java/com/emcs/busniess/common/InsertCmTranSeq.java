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
        Map sysMap = (Map)param.get("SYS_HEAD");
        param.put("TRAN_DATE",new Date());
        String seqNo = oneSelect.getNextVal(BusiConstant.Quence.CM_TRAN_SEQ.gname());
        param.put("pub_seq_no",seqNo);
        oneDML.insertCmTranSeq(param);
    }
}
