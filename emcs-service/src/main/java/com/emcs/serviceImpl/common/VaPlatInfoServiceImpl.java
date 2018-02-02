package com.emcs.serviceImpl.common;

import com.emcs.mapper.OneTableDMLMapper;
import com.emcs.mapper.OneTableSelectMapper;
import com.emcs.service.common.VaPlatInfoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Transactional
@Service
public class VaPlatInfoServiceImpl implements VaPlatInfoService {
    @Resource
    OneTableDMLMapper vaPlatInfoMapper;
    @Override
    public int insertVaPlatInfo(Map<String,Object> map) {
       return vaPlatInfoMapper.insertVaPlatInfo(map);
    }
    @Resource
    OneTableSelectMapper vaPlatInfoMapper1;
    @Override
    public List<Map<String, Object>> selectByPayMerchId(String payMerchId) {
        return vaPlatInfoMapper1.selectByPayMerchId(payMerchId);
    }
}
