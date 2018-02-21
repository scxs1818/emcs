package com.emcs.busniess.recharge;
import com.emcs.Constant.ErrorCodeConstant.*;
import com.emcs.busniess.common.LimitValidate;
import com.emcs.supers.PubService;
import com.emcs.supers.ServiceTransactionalY;
import com.emcs.Constant.BusiConstant;
import com.emcs.exception.BusiException;
import com.emcs.supers.SupperService;
import com.emcs.util.CheckEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/2/4.
 */
@Service
public class MemberRecharge extends ServiceTransactionalY {
    @Autowired
    CustRecharge custRecharge;
    @Autowired
    MerchRecharge merchRecharge;
    @Autowired
    LimitValidate validate;
    @Override
    public void process(Map<String, Object> data) {
        //初始化会员和会员账户状态
        data.put("status","N");
        data.put("acct_status","N");

        //校验平台
        if(oneSelect.selectIsExistVaPlatInfo(data)==0)
            throw new BusiException(PlatErrorCode.VAP001.code(), PlatErrorCode.VAP001.val());

        List<Map<String,Object>> acctList = new ArrayList<>();
        if(BusiConstant.ROLE_CUST.equals(data.get("role_type"))){
            data.put("tran_type", BusiConstant.TranType.CUST_RECHARGE.val());
            data.put("payee_type",BusiConstant.ROLE_CUST);
            data.put("cust_id",data.get("payee_id"));

            validate.validateRepeat(data);

            validate.validatePayee(data);
            data.put("payee_virid",((Map<String,Object>)data.get("payeeInfo")).get("cust_virid"));
            acctList = oneSelect.selectVaCustAcctInfo(data);
            if(CheckEmpty.isEmpty(acctList))throw new BusiException("未绑卡或者卡有异常","123456");
            data.put("payer_acct_no",acctList.get(0).get("acct_no"));

            validate.businessValidate(data);
            custRecharge.process(data);
        }else if(BusiConstant.ROLE_MERCH.equals(data.get("role_type"))){
            data.put("tran_type", BusiConstant.TranType.MERCH_RECHARGE.val());
            data.put("payee_type",BusiConstant.ROLE_MERCH);
            data.put("merch_id",data.get("payee_id"));
            validate.validateRepeat(data);
            validate.validatePayee(data);

            data.put("payee_virid",((Map<String,Object>)data.get("payeeInfo")).get("merch_virid"));
            acctList = oneSelect.selectVaMerchAcctInfo(data);
            if(CheckEmpty.isEmpty(acctList))throw new BusiException("未绑卡或者卡有异常","123456");
            data.put("payer_acct_no",acctList.get(0).get("acct_no"));

            validate.businessValidate(data);
            merchRecharge.process(data);
        }else{
            throw new BusiException(PubErrorCode.VAZ007.code(),PubErrorCode.VAZ007.val());
        }
    }
}