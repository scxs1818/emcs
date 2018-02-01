package com.emcs.serviceImpl.common;

import com.emcs.mapper.plat.VaPlatAcctInfoMapper;
import com.emcs.mapper.plat.VaPlatInfoMapper;
import com.emcs.service.common.VaPlatAccInfoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Map;
@Transactional
@Service
public class VaPlatAccInfoServiceImpl implements VaPlatAccInfoService {
    @Resource
    VaPlatAcctInfoMapper vaPlatAccInfoService;
    @Override
    public int insertVaPlatAccInfo(Map<String, Object> map) {
        return vaPlatAccInfoService.insertVaPlatAccInfo(map);
    }
}
