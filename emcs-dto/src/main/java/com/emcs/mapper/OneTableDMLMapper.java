package com.emcs.mapper;

import java.util.Map;

/**
 * Created by Administrator on 2018/2/2.
 */
public interface OneTableDMLMapper {
    int insertVaPlatAccInfo(Map<String, Object> map);
    int insertVaPlatInfo(Map<String,Object> map);
    int insertVaPlatVirtualAcctBal(Map<String, Object> params);
    int insertVaPlatVirtualAcct(Map<String,Object> params);
}
