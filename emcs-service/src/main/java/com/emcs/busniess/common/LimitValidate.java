package com.emcs.busniess.common;

import com.emcs.Constant.BusiConstant;
import com.emcs.Constant.ErrorCodeConstant.*;
import com.emcs.Super.PubService;
import com.emcs.cache.CacheUtil;
import com.emcs.exception.BusiException;
import com.emcs.util.CheckEmpty;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class LimitValidate extends PubService {
    @Override
    public void process(Map<String, Object> param) {
        Object roletype=param.get("role_type");
        List<Map<String,Object>> virAcctBalList;
        if(BusiConstant.Role.CUST.equals(roletype)){
            virAcctBalList = manySelect.manyVaCustVirtualAcctBalLock(param);
            limitValidate(param,virAcctBalList);
        }if(BusiConstant.Role.MERCH.equals(roletype)){
            virAcctBalList = manySelect.manyVaMerchVirtualAcctBalLock(param);
            limitValidate(param,virAcctBalList);
        }else{
            throw new BusiException(PubErrorCode.VAZ007.code(), PubErrorCode.VAZ007.val());
        }
    }

    private void limitValidate(Map<String, Object> param,List<Map<String,Object>> virAcctBalList) {
        //2.判断付款方虚拟账户是否正常
        if(CheckEmpty.isEmpty(virAcctBalList))throw new BusiException(PubErrorCode.VAZ013.code(),PubErrorCode.VAZ013.val());
        Map<String,Object> virAcctBalMap = virAcctBalList.get(0);

        //3.判断商户虚拟账户是否存在转出限制
        if(!"Y".equals(virAcctBalMap.get("is_out")))throw new BusiException(PubErrorCode.VAZ011.code(),PubErrorCode.VAZ011.val());

        //4.备付金校验
        BigDecimal balance_limit = (BigDecimal)virAcctBalMap.get("balance_limit");
        BigDecimal actural_bal = (BigDecimal)virAcctBalMap.get("actural_bal");
        BigDecimal balance_value = (BigDecimal)virAcctBalMap.get("balance_value");
        BigDecimal tran_amt =  new BigDecimal(param.get("tran_amt")+"");
        if("Y".equals(virAcctBalMap.get("is_balance_limit"))){
            if("V".equals(virAcctBalMap.get("balance_type"))){//固定值
                if(balance_limit.compareTo(actural_bal.subtract(tran_amt))==-1)
                    throw new BusiException(PubErrorCode.VAZ012.code(),PubErrorCode.VAZ012.val());
            }else if("P".equals(virAcctBalMap.get("balance_type"))){//百分比
                if(balance_limit.multiply(balance_value).compareTo(actural_bal.subtract(tran_amt))==-1)
                    throw new BusiException(PubErrorCode.VAZ012.code(),PubErrorCode.VAZ012.val());
            }
        }

        //5.从缓存里取商户的单笔限额
        Map<String,Object> cacheObject = CacheUtil.getInstance().getCacheData(oneSelect, BusiConstant.CACHE_CM_BUSINESS_PARA);
        Object tranType = param.get("tran_type");
        String limitAmt = cacheObject.get(tranType+BusiConstant.PIPE+BusiConstant.LIMIT_AMT)+"";
        Integer limitCnt = Integer.parseInt(cacheObject.get(tranType+BusiConstant.PIPE+BusiConstant.LIMIT_CNT)+"");

        //6.判断交易次数是否超出日交易最大次数
        List<Map<String,Object>> sumList = oneSelect.selectVaMerchRechargeAmtDay(param);
        Integer sumCnt =Integer.parseInt( sumList.get(0).get("sum_cnt")+"");
        if(limitCnt>sumCnt)throw new BusiException(PubErrorCode.VAZ009.code(),PubErrorCode.VAZ009.val());

        //7.判断充值金额是否超出单笔限额
        if(tran_amt.compareTo(new BigDecimal(limitAmt))==1)throw new BusiException(PubErrorCode.VAZ008.code(),PubErrorCode.VAZ008.val());
    }
}