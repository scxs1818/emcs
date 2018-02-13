package com.emcs.busniess.common;

import com.emcs.Constant.BusiConstant;
import com.emcs.supers.PubService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Map;

/**
 * Created by Administrator on 2018/2/4.
 */
@Transactional
@Service
public class InsertCmAcctTranSeq extends PubService {
    @Override
    public void process(Map<String, Object> data) {
//        数据封装带补充
        Map sysMap = (Map)data.get("SYS_HEAD");
        data.put("TRAN_DATE",new Date());
        String seqNo = oneSelect.getNextVal(BusiConstant.Quence.CM_TRAN_SEQ.gname());
        data.put("pub_seq_no",seqNo);
        oneDML.insertCmAcctTranSeq(data);
    }
}