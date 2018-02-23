package com.emcs.busniess.common;

import com.emcs.Constant.BusiConstant;
import com.emcs.cache.CacheData;
import com.emcs.supers.PubServiceY;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
@Service
public class InsertCmTranSeq extends PubServiceY {
    @Transactional(propagation= Propagation.NOT_SUPPORTED)
    @Override
    public void process(Map<String, Object> data) {
        Object trandate = CacheData.getCacheObj(oneSelect,BusiConstant.Cache.CM_SYSTEM.val()).get("run_date");
        data.put("tran_date",trandate);
        data.put("tran_time",new SimpleDateFormat(BusiConstant.FOR_STAN_06).format(new Date()));
        data.put("channel_date",trandate);
        data.put("tran_status","00");
        data.put("pub_seq_no",oneSelect.getNextVal(BusiConstant.Quence.CM_TRAN_SEQ.val()));
        oneDML.insertCmTranSeq(data);
    }
}