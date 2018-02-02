package com.emcs.serviceImpl.common;

import com.emcs.mapper.OneTableDMLMapper;
import com.emcs.service.common.VaPlatVirtualAcctService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Map;
@Transactional
@Service
public class VaPlatVirtualAcctServiceImpl implements VaPlatVirtualAcctService {
    @Resource
    OneTableDMLMapper mapper;
    @Override
    public int insertVaPlatVirtualAcct(Map<String, Object> params) {
        return mapper.insertVaPlatVirtualAcct(params);
    }
}
