package com.emcs.busniess.common;

import com.emcs.Constant.BusiConstant;
import com.emcs.supers.PubService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;
@Service
public class UpdateCmTranSeq extends PubService {
    @Override
    public void process(Map<String, Object> param) {
        oneDML.updateCmTranSeq(param);
    }
}
