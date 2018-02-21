package com.emcs.mapper;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/2/2.
 */
public interface OneTableSelectMapper{

    List<Map<String,Object>> selectVaVirtualAcctType(Map<String, Object> param);
    int selectIsExistVaMerchInfo(Map<String, Object> param);
    int selectIsExistVaPlatInfo(Map<String, Object> param);
    int selectIsExistVaCustInfo(Map<String, Object> param);

    List<Map<String,Object>> selectPlatInfo(Map<String, Object> param);
    List<Map<String,Object>> selectPlatInfoSim(Map<String, Object> param);


    List<Map<String,Object>> selectMerchInfo(Map<String, Object> param);
    List<Map<String,Object>> selectVaMerchVirtualAcctInfo(Map<String, Object> param);
    List<Map<String,Object>> selectVaMerchRechargeSeqSum(Map<String, Object> param);
    List<Map<String,Object>> selectVaMerchVirtualAcctBalLock(Map<String, Object> param);
    List<Map<String,Object>> selectVaMerchAcctInfo(Map<String, Object> param);
    List<Map<String,Object>> selectVaMerchWithdrawSeqSum(Map<String, Object> param);
    List<Map<String,Object>> selectVaMerchInfo(Map<String, Object> param);
    List<Map<String,Object>> selectVaMerchInfoSim(Map<String, Object> param);


    List<Map<String,Object>> selectVaCustVirtualAcctInfo(Map<String, Object> param);
    List<Map<String,Object>> selectVaCustWithdrawSeqSum(Map<String, Object> param);
    List<Map<String,Object>> selectVaCustRechargeSeqSum(Map<String, Object> param);
    List<Map<String,Object>> selectVaCustVirtualAcctBalLock(Map<String, Object> param);
    List<Map<String,Object>> selectVaCustAcctInfo(Map<String, Object> param);
    List<Map<String,Object>> selectVaCustInfo(Map<String, Object> param);


    List<Map<String,Object>> selectEodProcRule(Map<String, Object> param);
    List<Map<String,Object>> selectEodProcLog(Map<String, Object> param);
    List<Map<String,Object>> selectScheduleJob(Map<String, Object> param);


    List<Map<String,Object>> selectVaOrderInfoForOld(Map<String, Object> param);
    List<Map<String,Object>> selectVaOrderInfoLock(Map<String, Object> param);


    List<Map<String,Object>> selectCmBusinessParaForCache(Map<String, Object> param);
    List<Map<String,Object>> selectCmSystemForCache(Map<String, Object> param);

    List<Map<String,Object>> selectVaVirAcctSeq(Map<String, Object> param);
    List<Map<String,Object>> selectDbTableColumns(Object param);
    List<Object> selectDbTables(Map<String,Object> param);
    String getNextVal(String param);

    int selectVaOrderSeqForRepeat(Map<String, Object> param);
    int selectVaOrderInfoForRepeat(Map<String, Object> param);
    int selectVaTransferSeqForRepeat(Map<String, Object> param);
    int selectVaCustRechargeSeqForRepeat(Map<String, Object> param);
    int selectVaMerchRechargeSeqForRepeat(Map<String, Object> param);
    int selectVaCustWithdrawSeqForRepeat(Map<String, Object> param);
    int selectVaMerchWithdrawSeqForRepeat(Map<String, Object> param);
}