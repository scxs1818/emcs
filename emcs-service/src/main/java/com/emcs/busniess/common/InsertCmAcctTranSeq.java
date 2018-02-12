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
    public void process(Map<String, Object> param) {
//        数据封装带补充
        Map sysMap = (Map)param.get("SYS_HEAD");
        param.put("TRAN_DATE",new Date());
        String seqNo = oneSelect.getNextVal(BusiConstant.Quence.CM_TRAN_SEQ.gname());
        param.put("cm_tran_seq",seqNo);
        oneDML.insertCmAcctTranSeq(param);
    }
}