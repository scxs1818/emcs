package com.emcs.serviceImpl.common;

import com.emcs.mapper.plat.VaPlatInfoMapper;
import com.emcs.service.common.VaPlatInfoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


@Service
public class VaPlatInfoServiceImpl implements VaPlatInfoService {
    @Resource
    VaPlatInfoMapper vaPlatInfoMapper;
    @Override
    public int insertVaPlatInfo(Map<String,Object> map) {
       return vaPlatInfoMapper.insertVaPlatInfo(map);
    }

    @Override
    public List<Map<String, Object>> selectByPayMerchId(String payMerchId) {
        return vaPlatInfoMapper.selectByPayMerchId(payMerchId);
    }
}
