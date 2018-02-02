package com.emcs.serviceImpl.common;

import com.emcs.mapper.OneTableDMLMapper;
import com.emcs.service.common.VaPlatVirtualAcctBalService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Map;
@Transactional
@Service
public class VaPlatVirtualAcctBalServiceImpl implements VaPlatVirtualAcctBalService{
    @Resource
    OneTableDMLMapper mapper;
    @Override
    public int insertVaPlatVirtualAcctBal(Map<String, Object> params) {
        return mapper.insertVaPlatVirtualAcctBal(params);
    }
}
