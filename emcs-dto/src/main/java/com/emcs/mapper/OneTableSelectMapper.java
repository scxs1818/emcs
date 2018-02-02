package com.emcs.mapper;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/2/2.
 */
public interface OneTableSelectMapper {
    public List<Map<String,Object>> selectVirAcctType();
    List<Map<String,Object>> selectByPayMerchId(String payMerchId);
}
