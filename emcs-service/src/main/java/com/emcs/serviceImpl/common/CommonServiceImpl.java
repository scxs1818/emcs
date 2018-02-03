package com.emcs.serviceImpl.common;

import com.emcs.mapper.common.CommonMapper;
import com.emcs.service.common.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CommonServiceImpl implements CommonService{
    @Autowired
    CommonMapper oneSelect;
    @Override
    public int getNextVal(String sqeName) {
        return oneSelect.getNextVal(sqeName);
    }
}
