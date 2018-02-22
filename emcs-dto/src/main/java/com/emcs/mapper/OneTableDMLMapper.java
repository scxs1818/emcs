package com.emcs.mapper;

import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * Created by Administrator on 2018/2/2.
 */
public interface OneTableDMLMapper{
    int insertVaPlatAccInfo(Map<String, Object> map);
    int insertVaPlatInfo(Map<String,Object> map);
    int insertVaPlatVirtualAcctBal(Map<String, Object> params);
    int updateVaPlatVirtualAcctBalAdd(Map<String, Object> params);
    int updateVaPlatVirtualAcctBalSub(Map<String, Object> params);
    int insertVaPlatVirtualAcct(Map<String,Object> params);

    int updateVaMerchVirtualAcctBalAddForRefund(Map<String, Object> map);
    int insertVaMerchAccInfo(Map<String, Object> map);
    int insertVaMerchInfo(Map<String,Object> map);
    int insertVaMerchVirtualAcctBal(Map<String, Object> params);
    int updateVaMerchVirtualAcctBalAdd(Map<String, Object> params);
    int insertVaMerchRechargeSeq(Map<String, Object> params);
    int insertVaMerchWithdrawSeq(Map<String, Object> params);
    int updateVaMerchVirtualAcctBalSub(Map<String, Object> params);
    int insertVaMerchVirtualAcct(Map<String,Object> params);
    int dayEndTransferAmtForMerch(Map<String,Object> params);
    int deleteVaMerchAcctInfo(Map<String,Object> params);


    int updateVaCustVirtualAcctBalAddForRefund(Map<String, Object> map);
    int insertVaCustAccInfo(Map<String, Object> map);
    int insertVaCustInfo(Map<String,Object> map);
    int insertVaCustVirtualAcctBal(Map<String, Object> params);
    int updateVaCustVirtualAcctBalAdd(Map<String, Object> params);
    int updateVaCustVirtualAcctBalSub(Map<String, Object> params);
    int insertVaCustVirtualAcct(Map<String,Object> params);
    int insertVaCustRechargeSeq(Map<String,Object> params);
    int insertVaCustWithdrawSeq(Map<String,Object> params);
    int dayEndTransferAmtForCust(Map<String,Object> params);
    int deleteVaCustAcctInfo(Map<String,Object> params);

    int insertCmAcctTranSeq(Map<String,Object> params);
    int updateCmAcctTranSeq(Map<String,Object> params);

    int insertCmTranSeq(Map<String,Object> params);
    int updateCmTranSeq(Map<String,Object> params);

    int insertVaOrderInfo(Map<String,Object> params);
    int updateVaOrderInfo(Map<String,Object> params);
    int insertVaOrderSeq(Map<String,Object> params);

    int insertVaVirAcctSeq(Map<String,Object> params);
    int updateVaVirAcctSeq(Map<String,Object> params);
    int insertVaBindSeq(Map<String,Object> params);


    int insertVaTransferSeq(Map<String,Object> params);
    int insertTestUser(Map<String,Object> params);
    int updateProduceDay(Map<String,Object> params);

    int insertEodProcPrdLog(Map<String,Object> params);
    int insertEodProcLog(Map<String,Object> params);
    int updateEodProcPrdLog(Map<String,Object> params);





}