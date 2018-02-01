package com.emcs.mapper.plat;

import java.util.List;
import java.util.Map;

public interface VaPlatInfoMapper {
    int insertVaPlatInfo(Map<String,Object> params);
    List<Map<String,Object>> selectByPayMerchId(String payMerchId);
}
