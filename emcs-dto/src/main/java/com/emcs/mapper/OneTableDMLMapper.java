package com.emcs.mapper;

import java.util.Map;

/**
 * Created by Administrator on 2018/2/2.
 */
public interface OneTableDMLMapper{
    int insertVaPlatAccInfo(Map<String, Object> map);
    int insertVaPlatInfo(Map<String,Object> map);
    int insertVaPlatVirtualAcctBal(Map<String, Object> params);
    int insertVaPlatVirtualAcct(Map<String,Object> params);

    int insertVaMerchAccInfo(Map<String, Object> map);
    int insertVaMerchInfo(Map<String,Object> map);
    int insertVaMerchVirtualAcctBal(Map<String, Object> params);
    int insertVaMerchVirtualAcct(Map<String,Object> params);

    int insertVaCustAccInfo(Map<String, Object> map);
    int insertVaCustInfo(Map<String,Object> map);
    int insertVaCustVirtualAcctBal(Map<String, Object> params);
    int insertVaCustVirtualAcct(Map<String,Object> params);

    int insertCmAcctTranSeq(Map<String,Object> params);
    int updateCmAcctTranSeq(Map<String,Object> params);

    int insertCmTranSeq(Map<String,Object> params);
    int updateCmTranSeq(Map<String,Object> params);
}
