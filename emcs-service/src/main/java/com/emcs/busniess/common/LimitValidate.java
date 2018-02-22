package com.emcs.busniess.common;

import com.emcs.Constant.BusiConstant;
import com.emcs.Constant.ErrorCodeConstant.*;
import com.emcs.cache.CacheUtil;
import com.emcs.exception.BusiException;
import com.emcs.supers.PubServiceY;
import com.emcs.util.CheckEmpty;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Service
public class LimitValidate extends PubServiceY {
    @Override
    public void process(Map<String, Object> data) {
        limitValidate(data);
    }

    private void limitValidate(Map<String, Object> data) {
        if (oneSelect.selectIsExistVaPlatInfo(data) == 0)
            throw new BusiException(PlatErrorCode.VAP001.code(), PlatErrorCode.VAP001.val());

        validatePayer(data);
        validatePayee(data);
        validateRepeat(data);
    }

    public void validateRepeat(Map<String, Object> data) {
        Object tranType = data.get("tran_type");
        int cnt;
        if (BusiConstant.TranType.CUST_PURCHASE_APPLY.val().equals(tranType)||
                BusiConstant.TranType.CUST_PURCHASE_CONFIRM.val().equals(tranType)||
                BusiConstant.TranType.CUST_PURCHASE_REVOKE.val().equals(tranType)) {
            cnt = oneSelect.selectVaOrderSeqForRepeat(data);
        }else if (BusiConstant.TranType.TRANSFER_MERCH_TO_MERCH.val().equals(tranType) ||
                BusiConstant.TranType.TRANSFER_MERCH_TO_CUST.val().equals(tranType) ||
                BusiConstant.TranType.TRANSFER_CUST_TO_MERCH.val().equals(tranType) ||
                BusiConstant.TranType.TRANSFER_CUST_TO_CUST.val().equals(tranType)) {
            cnt = oneSelect.selectVaTransferSeqForRepeat(data);
        } else if (BusiConstant.TranType.MERCH_WITHDRAW.val().equals(tranType) ){
            cnt = oneSelect.selectVaMerchWithdrawSeqForRepeat(data);
        }else if (BusiConstant.TranType.CUST_WITHDRAW.val().equals(tranType)) {
            cnt = oneSelect.selectVaCustWithdrawSeqForRepeat(data);
        }else if (BusiConstant.TranType.MERCH_RECHARGE.val().equals(tranType)) {
            cnt = oneSelect.selectVaMerchRechargeSeqForRepeat(data);
        }else if (BusiConstant.TranType.CUST_RECHARGE.val().equals(tranType)) {
            cnt = oneSelect.selectVaCustRechargeSeqForRepeat(data);
        }else{
          throw new BusiException("未知业务类型");
        }
        if(cnt>0)throw new BusiException(PubErrorCode.VAZ020.code(),PubErrorCode.VAZ020.val());
    }

    public void validatePayee(Map<String, Object> data) {

        Object payeeType = data.get("payee_type");
        List<Map<String, Object>> virAcctBalList;
        if (BusiConstant.Role.CUST.val().equals(payeeType)) {
            if (oneSelect.selectIsExistVaCustInfo(data) == 0)
                throw new BusiException(PubErrorCode.VAZ019.code(), PubErrorCode.VAZ019.val());
            virAcctBalList = manySelect.manyVaCustVirtualAcctBalLock(data);
        }else if (BusiConstant.Role.MERCH.val().equals(payeeType)) {
            if (oneSelect.selectIsExistVaMerchInfo(data) == 0)
                throw new BusiException(PubErrorCode.VAZ019.code(), PubErrorCode.VAZ019.val());
            virAcctBalList = manySelect.manyVaMerchVirtualAcctBalLock(data);
        } else {
            throw new BusiException(PubErrorCode.VAZ017.code(), PubErrorCode.VAZ017.val());
        }

        //2.判断付款方虚拟账户是否正常
        if (CheckEmpty.isEmpty(virAcctBalList))
            throw new BusiException(PubErrorCode.VAZ014.code(), PubErrorCode.VAZ014.val());
        Map<String, Object> virAcctBalMap = virAcctBalList.get(0);
        data.put("payeeInfo", virAcctBalMap);
        //3.判断商户虚拟账户是否存在转入限制
        if (!"Y".equals(virAcctBalMap.get("is_in")))
            throw new BusiException(PubErrorCode.VAZ015.code(), PubErrorCode.VAZ015.val());

        log.info("virAcctBalMap="+virAcctBalMap);

        //4.总限额校验
        BigDecimal total_limit = (BigDecimal) virAcctBalMap.get("total_limit");
        BigDecimal actural_bal = (BigDecimal) virAcctBalMap.get("actural_bal");
        BigDecimal balance_value = (BigDecimal) virAcctBalMap.get("balance_value");

        if ("Y".equals(virAcctBalMap.get("is_total_limit"))) {
            BigDecimal tran_amt = new BigDecimal(data.get("tran_amt") + "");
            if (total_limit.compareTo(actural_bal.add(tran_amt)) == -1)
                throw new BusiException(PubErrorCode.VAZ010.code(), PubErrorCode.VAZ010.val());
        }

        //5.数据组装
        if (BusiConstant.Role.CUST.val().equals(payeeType)) {
            data.put("payee_virid",virAcctBalMap.get("cust_virid"));
        }else{
            data.put("payee_virid",virAcctBalMap.get("merch_virid"));
        }
    }

    public void businessValidate(Map<String, Object> data) {
        BigDecimal tran_amt = new BigDecimal(data.get("tran_amt") + "");
        String tranType = data.get("tran_type")+"";
        //6.从缓存里取商户的单笔限额
        Map<String, Object> cacheObject = CacheUtil.getInstance().getCacheObj(oneSelect, BusiConstant.CACHE_CM_BUSINESS_PARA);
        tranType = ("5".equals(tranType) || "6".equals(tranType)) ? "56" : ("7".equals(tranType) || "8".equals(tranType)) ? "78" : tranType;
        String limitCnt = cacheObject.get(tranType + BusiConstant.PIPE + BusiConstant.LIMIT_CNT) + "";
        String sumLimitAmt = cacheObject.get(tranType + BusiConstant.PIPE + BusiConstant.SUM_LIMIT_AMT) + "";
        String sigLimitAmt = cacheObject.get(tranType + BusiConstant.PIPE + BusiConstant.SIG_LIMIT_AMT) + "";
        boolean isLimitCnt = CheckEmpty.isEmpty(limitCnt), isSumLimitAmt = CheckEmpty.isEmpty(sumLimitAmt), isSigLimitAmt = CheckEmpty.isEmpty(sigLimitAmt);

        //7.判断充值金额是否超出单笔限额
        if (isSigLimitAmt && tran_amt.compareTo(new BigDecimal(sigLimitAmt + "")) == 1)
            throw new BusiException(PubErrorCode.VAZ008.code(), PubErrorCode.VAZ008.val());

        //8.判断日交易次数是否超出允许日交易总次数
        List<Map<String, Object>> sumList = oneSelect.selectVaMerchRechargeSeqSum(data);
        if (isLimitCnt && Integer.parseInt(sumList.get(0).get("sum_cnt") + "") > Integer.parseInt(limitCnt))
            throw new BusiException(PubErrorCode.VAZ009.code(), PubErrorCode.VAZ009.val());

        //9.判断日交易总额是否超出允许日交易总金额
        if (isSumLimitAmt && Integer.parseInt(sumList.get(0).get("sum_amt") + "") > Integer.parseInt(sumLimitAmt))
            throw new BusiException(PubErrorCode.VAZ021.code(), PubErrorCode.VAZ021.val());
    }

    public void validatePayer(Map<String, Object> data) {
        log.info("validatePayer="+data);
        Object payerType = data.get("payer_type"), tranType = data.get("tran_type");

        List<Map<String, Object>> virAcctBalList;
        if (BusiConstant.Role.CUST.val().equals(payerType)) {
            if (oneSelect.selectIsExistVaCustInfo(data) == 0)
                throw new BusiException(PubErrorCode.VAZ018.code(), PubErrorCode.VAZ018.val());

            virAcctBalList = manySelect.manyVaCustVirtualAcctBalLock(data);
        }else if (BusiConstant.Role.MERCH.val().equals(payerType)) {
            if (oneSelect.selectIsExistVaMerchInfo(data) == 0)
                throw new BusiException(PubErrorCode.VAZ018.code(), PubErrorCode.VAZ018.val());

            virAcctBalList = manySelect.manyVaMerchVirtualAcctBalLock(data);
        } else {
            throw new BusiException(PubErrorCode.VAZ007.code(), PubErrorCode.VAZ007.val());
        }

        //2.判断付款方虚拟账户是否正常
        if (CheckEmpty.isEmpty(virAcctBalList))
            throw new BusiException(PubErrorCode.VAZ013.code(), PubErrorCode.VAZ013.val());
        Map<String, Object> virAcctBalMap = virAcctBalList.get(0);
        data.put("payerInfo", virAcctBalMap);

        //3.判断商户虚拟账户是否存在转出限制
        if (!"Y".equals(virAcctBalMap.get("is_out")))
            throw new BusiException(PubErrorCode.VAZ011.code(), PubErrorCode.VAZ011.val());

        BigDecimal balance_limit = (BigDecimal) virAcctBalMap.get("balance_limit");
        BigDecimal actural_bal = (BigDecimal) virAcctBalMap.get("actural_bal");
        BigDecimal usable_bal = (BigDecimal) virAcctBalMap.get("usable_bal");
        BigDecimal recharge_bal = (BigDecimal) virAcctBalMap.get("recharge_bal");
        BigDecimal balance_value = (BigDecimal) virAcctBalMap.get("balance_value");
        BigDecimal tran_amt = new BigDecimal(data.get("tran_amt") + "");

        //4.校验余额
        if (BusiConstant.TranType.CUST_PURCHASE_APPLY.val().equals(tranType) ||
                BusiConstant.TranType.TRANSFER_MERCH_TO_MERCH.val().equals(tranType) ||
                BusiConstant.TranType.TRANSFER_MERCH_TO_CUST.val().equals(tranType) ||
                BusiConstant.TranType.TRANSFER_CUST_TO_MERCH.val().equals(tranType) ||
                BusiConstant.TranType.TRANSFER_CUST_TO_CUST.val().equals(tranType)) {
            if (usable_bal.add(recharge_bal).compareTo(tran_amt) == -1)
                throw new BusiException(PubErrorCode.VAZ022.code(), PubErrorCode.VAZ022.val());

        } else if (BusiConstant.TranType.MERCH_WITHDRAW.val().equals(tranType) ||
                BusiConstant.TranType.CUST_WITHDRAW.val().equals(tranType)) {
            if (usable_bal.compareTo(tran_amt) == -1)
                throw new BusiException(PubErrorCode.VAZ016.code(), PubErrorCode.VAZ016.val());
        }

        //5.备付金校验
        if ("Y".equals(virAcctBalMap.get("is_balance_limit"))) {
            if ("V".equals(virAcctBalMap.get("balance_type"))) {//固定值
                if (balance_limit.compareTo(actural_bal.subtract(tran_amt)) == -1)
                    throw new BusiException(PubErrorCode.VAZ012.code(), PubErrorCode.VAZ012.val());
            } else if ("P".equals(virAcctBalMap.get("balance_type"))) {//百分比
                if (balance_limit.multiply(balance_value).compareTo(actural_bal.subtract(tran_amt)) == -1)
                    throw new BusiException(PubErrorCode.VAZ012.code(), PubErrorCode.VAZ012.val());
            }
        }

        //6.数据组装
        if (BusiConstant.Role.CUST.val().equals(payerType)) {
            data.put("payer_virid",virAcctBalMap.get("cust_virid"));
        }else{
            data.put("payer_virid",virAcctBalMap.get("merch_virid"));
        }
    }
}