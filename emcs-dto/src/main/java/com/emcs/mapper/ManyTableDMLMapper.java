package com.emcs.mapper;

import java.util.Map;

/**
 * Created by Administrator on 2018/2/2.
 */
public interface ManyTableDMLMapper{
    int batchVaCustVirtualAcctBal(Map<String, Object> data);
    int batchVaMerchVirtualAcctBal(Map<String, Object> data);
    int insertEodProcPrdLogs(Map<String, Object> data);

}