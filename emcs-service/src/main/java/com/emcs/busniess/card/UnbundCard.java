package com.emcs.busniess.card;

import com.emcs.Constant.BusiConstant;
import com.emcs.Constant.ErrorCodeConstant;
import com.emcs.exception.BusiException;
import com.emcs.supers.InServiceY;
import com.emcs.util.CheckEmpty;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**卡解绑
 * Created by Administrator on 2018/2/6.
 */
@Service
public class UnbundCard extends InServiceY {
    @Override
    protected void process(Map<String, Object> data) {

        //校验平台
        if(oneSelect.selectIsExistVaPlatInfo(data)==0)
            throw new BusiException(ErrorCodeConstant.PlatErrorCode.VAP001.code(), ErrorCodeConstant.PlatErrorCode.VAP001.val());

        List<Map<String,Object>> acctList = new ArrayList<>();
        Map<String,Object> param = new HashMap<>();
        param.put("status","N");
        List<Map<String,Object>> meberList;
        data.put("create_date",data.get("tran_date")+""+data.get("tran_time"));
        if(BusiConstant.ROLE_CUST.equals(data.get("role_type"))){
            param.put("cust_id",data.get("member_id"));
            meberList = oneSelect.selectVaCustInfo(param);
            if(CheckEmpty.isEmpty(meberList))
                throw new BusiException(ErrorCodeConstant.CustErrorCode.VAC001.code(), ErrorCodeConstant.CustErrorCode.VAC001.val());

            if(oneDML.deleteVaCustAcctInfo(data)!=1)
                throw new BusiException(ErrorCodeConstant.PubErrorCode.VAZ026.code(), ErrorCodeConstant.PubErrorCode.VAZ026.val());

            data.put("bind_seq_no",oneSelect.getNextVal(BusiConstant.Quence.BIND_SEQ_NO.val()));
            oneDML.insertVaBindSeq(data);
        }else if(BusiConstant.ROLE_MERCH.equals(data.get("role_type"))){
            param.put("merch_id",data.get("member_id"));
            meberList = oneSelect.selectVaMerchInfo(param);
            if(CheckEmpty.isEmpty(meberList))
                throw new BusiException(ErrorCodeConstant.MerchErrorCode.VAB001.code(), ErrorCodeConstant.MerchErrorCode.VAB001.val());

            if(oneDML.deleteVaMerchAcctInfo(data)!=1)
                throw new BusiException(ErrorCodeConstant.PubErrorCode.VAZ026.code(), ErrorCodeConstant.PubErrorCode.VAZ026.val());

            data.put("bind_seq_no",oneSelect.getNextVal(BusiConstant.Quence.BIND_SEQ_NO.val()));
            oneDML.insertVaBindSeq(data);
        }else{
            throw new BusiException(ErrorCodeConstant.PubErrorCode.VAZ007.code(), ErrorCodeConstant.PubErrorCode.VAZ007.val());
        }
    }
}