package com.emcs.busniess.card;

import com.emcs.Constant.BusiConstant;
import com.emcs.Constant.ErrorCodeConstant.*;
import com.emcs.busniess.common.ValidateKeysInfo;
import com.emcs.exception.BusiException;
import com.emcs.supers.ServiceTransactionalY;
import com.emcs.util.CheckEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 绑卡
 * Created by Administrator on 2018/2/6.
 */
@Service
public class BindCard extends ServiceTransactionalY{
    @Autowired
    ValidateKeysInfo validateKeysInfo;
    @Override
    public void process(Map<String, Object> data) {

        //校验平台
        if(oneSelect.selectIsExistVaPlatInfo(data)==0)
            throw new BusiException(PlatErrorCode.VAP001.code(), PlatErrorCode.VAP001.val());

        List<Map<String,Object>> acctList = new ArrayList<>();
        Map<String,Object> param = new HashMap<>();
        param.put("status","N");
        List<Map<String,Object>> meberList;
        data.put("create_date",data.get("tran_date")+""+data.get("tran_time"));
        data.put("currency","RMB");
        if(BusiConstant.ROLE_CUST.equals(data.get("role_type"))){
            param.put("cust_id",data.get("member_id"));
            meberList = oneSelect.selectVaCustInfo(param);
            if(CheckEmpty.isEmpty(meberList))
                throw new BusiException(CustErrorCode.VAC001.code(),CustErrorCode.VAC001.val());
            if(!(data.get("global_id")+"").equals(meberList.get(0).get("global_id")))
                throw new BusiException(PubErrorCode.VAZ024.code(),PubErrorCode.VAZ024.val());

            meberList = null;
            meberList = oneSelect.selectVaCustAcctInfo(data);
            if(!CheckEmpty.isEmpty(meberList))
                throw new BusiException(PubErrorCode.VAZ025.code(),PubErrorCode.VAZ025.val());
            data.put("acct_status","N");
            oneDML.insertVaCustAccInfo(data);

            validateKeysInfo.process(data);

            data.put("bind_seq_no",oneSelect.getNextVal(BusiConstant.Quence.BIND_SEQ_NO.val()));
            oneDML.insertVaBindSeq(data);
        }else if(BusiConstant.ROLE_MERCH.equals(data.get("role_type"))){
            param.put("merch_id",data.get("member_id"));
            meberList = oneSelect.selectVaMerchInfo(param);
            if(CheckEmpty.isEmpty(meberList))
                throw new BusiException(MerchErrorCode.VAB001.code(),MerchErrorCode.VAB001.val());

            if(!(data.get("global_id")+"").equals(meberList.get(0).get("global_id")))
                throw new BusiException(PubErrorCode.VAZ024.code(),PubErrorCode.VAZ024.val());

            meberList = null;
            meberList = oneSelect.selectVaMerchAcctInfo(data);
            if(!CheckEmpty.isEmpty(meberList))
                throw new BusiException(PubErrorCode.VAZ025.code(),PubErrorCode.VAZ025.val());

            data.put("acct_status","N");
            oneDML.insertVaMerchAccInfo(data);

            validateKeysInfo.process(data);

            data.put("bind_seq_no",oneSelect.getNextVal(BusiConstant.Quence.BIND_SEQ_NO.val()));
            oneDML.insertVaBindSeq(data);
        }else{
            throw new BusiException(PubErrorCode.VAZ007.code(), PubErrorCode.VAZ007.val());
        }
    }
}