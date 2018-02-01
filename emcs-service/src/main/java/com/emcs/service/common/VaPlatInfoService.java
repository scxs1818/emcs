package com.emcs.service.common;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface VaPlatInfoService {
    int insertVaPlatInfo(Map<String,Object> map);
    List<Map<String,Object>> selectByPayMerchId(String payMerchId);
}
