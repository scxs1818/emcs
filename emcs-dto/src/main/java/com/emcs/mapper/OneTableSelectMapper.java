package com.emcs.mapper;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/2/2.
 */
public interface OneTableSelectMapper{
    List<Map<String,Object>> selectVaVirtualAcctType(Object param);
    int selectIsExistVaMerchInfo(Object param);
    int selectIsExistVaPlatInfo(Object param);
    int selectIsExistVaCustInfo(Object param);
    List<Map<String,Object>> selectPlatInfo(Object param);
    List<Map<String,Object>> selectMerchInfo(Object param);
    List<Map<String,Object>> selectVaCustVirtualAcctInfo(Object param);
    List<Map<String,Object>> selectVaCustRechargeDetail(Object param);
    List<Map<String,Object>> selectVaCustRechargeAmtDay(Object param);
    List<Map<String,Object>> selectCmBusinessParaForCache(Object param);
    List<Map<String,Object>> selectCmSystemForCache(Object param);

    int getNextVal(String sqeName);
}
