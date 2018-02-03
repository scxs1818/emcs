package com.emcs.service.busniess;

import com.emcs.util.CommonResult;

import java.util.List;
import java.util.Map;

public interface PlatRegisterService {
  CommonResult platRegister(Map<String, Object> map);
  int insertVaPlatAccInfo(Map<String, Object> map);
  int insertVaPlatInfo(Map<String,Object> map);
  List<Map<String,Object>> selectByPayMerchId(String payMerchId);
  int insertVaPlatVirtualAcctBal(Map<String, Object> params);
  int insertVaPlatVirtualAcct(Map<String,Object> params);
}
