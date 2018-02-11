package com.emcs.mapper;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/2/2.
 */
public interface ManyTableSelectMapper{
    List<Map<String,Object>> manyVaCustVirtualAcctBalLock(Object param);
    List<Map<String,Object>> manyVaMerchVirtualAcctBalLock(Object param);
}
