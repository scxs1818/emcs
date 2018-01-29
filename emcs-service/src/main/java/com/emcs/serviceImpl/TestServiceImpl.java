package com.emcs.serviceImpl;

import com.emcs.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import com.emcs.mapper.TestMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
@Service
public class TestServiceImpl implements TestService{
    @Autowired
    TestMapper mapper;
    @Override
    public List<Map<String, Object>> selectAll() {
       // mapper.selectAll();
        return mapper.selectAll();
    }
}
