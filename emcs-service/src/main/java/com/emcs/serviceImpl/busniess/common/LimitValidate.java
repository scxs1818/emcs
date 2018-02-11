package com.emcs.serviceImpl.busniess.common;

import com.emcs.Constant.BusiConstant;
import com.emcs.Constant.ErrorCodeConstant.*;
import com.emcs.Super.PubService;
import com.emcs.exception.BusiException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class LimitValidate extends PubService {
    @Override
    public void process(Map<String, Object> param) {
        Object roletype=param.get("role_type");
        if(BusiConstant.Role.CUST.equals(roletype)){
            custLimitValidate(param);
        }if(BusiConstant.Role.MERCH.equals(roletype)){
            merchLimitValidate(param);
        }else{
            throw new BusiException(PubErrorCode.VAZ007.code(), PubErrorCode.VAZ007.val());
        }
    }
    private void custLimitValidate(Map<String, Object> param) {

        List<Map<String,Object>> virAcctList;
        Map<String,Object> virAcctMap;
        virAcctList = manySelect.manyVaCustVirtualAcctBalLock(param);
        if(virAcctList==null||virAcctList.size()==0)throw new BusiException(CustErrorCode.VAC001.code(),CustErrorCode.VAC001.val());
        virAcctMap = virAcctList.get(0);
        if(!"Y".equals(virAcctMap.get("is_out")))throw new BusiException(CustErrorCode.VAC006.code(),CustErrorCode.VAC006.val());
    }

    private void merchLimitValidate(Map<String, Object> param) {
        List<Map<String,Object>> virAcctList;
        Map<String,Object> virAcctMap;
        virAcctList = manySelect.manyVaMerchVirtualAcctBalLock(param);
        //1判断付款方虚拟账户是否正常
        if(virAcctList==null||virAcctList.size()==0)throw new BusiException(MerchErrorCode.VAB001.code(),MerchErrorCode.VAB001.val());
        virAcctMap = virAcctList.get(0);
        //2判断商户虚拟账户是否存在转出限制
        if(!"Y".equals(virAcctMap.get("is_out")))throw new BusiException(MerchErrorCode.VAB006.code(),MerchErrorCode.VAB006.val());
        virAcctMap.put("single_limit",2000);

        //3判断充值金额是否超出单笔限额
        if(new BigDecimal(param.get("tran_amt")+"").compareTo(new BigDecimal(virAcctMap.get("single_limit")+""))==1)throw new BusiException("商户充值已经超出单笔限额","600009");

        //4判断充值金额是否超出日限额
        List<Map<String,Object>> sumList = oneSelect.selectVaMerchRechargeAmtDay(param);
        BigDecimal sumAmt =(BigDecimal) sumList.get(0).get("sum_amt");
        Object tranType = param.get("tran_type");
        if(BusiConstant.TranType.CUST_RECHARGE.equals(tranType)||BusiConstant.TranType.MERCH_RECHARGE.equals(tranType)){

        }
        if(sumAmt.compareTo(new BigDecimal(virAcctMap.get("total_limit")+""))==1)throw new BusiException(PubErrorCode.VAZ008.code(),PubErrorCode.VAZ008.val());

        //5判断交易次数是否超出日交易最大次数
        Integer sumCnt =Integer.parseInt( sumList.get(0).get("sum_cnt")+"");
        if(new BigDecimal(sumCnt).compareTo(new BigDecimal(3))==1)throw new BusiException(PubErrorCode.VAZ009.code(),PubErrorCode.VAZ009.val());
    }
}