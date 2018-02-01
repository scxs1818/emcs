package com.emcs.service.common;

import org.apache.ibatis.annotations.Param;

import java.util.Map;

public interface VaPlatVirtualAcctService {
    int insertVaPlatVirtualAcct(Map<String,Object> params);
}
