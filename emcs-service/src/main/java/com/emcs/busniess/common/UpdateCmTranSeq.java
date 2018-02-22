package com.emcs.busniess.common;

import com.emcs.supers.PubServiceY;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
@Service
public class UpdateCmTranSeq extends PubServiceY {
    @Transactional(propagation= Propagation.NOT_SUPPORTED)
    @Override
    public void process(Map<String, Object> data) {
        oneDML.updateCmTranSeq(data);
    }
}
