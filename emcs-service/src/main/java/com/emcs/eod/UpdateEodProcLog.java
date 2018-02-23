package com.emcs.eod;

import com.emcs.supers.PubServiceN;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * Created by Administrator on 2018/2/23.
 */
@Service
public class UpdateEodProcLog extends PubServiceN{
    @Transactional(propagation= Propagation.NOT_SUPPORTED)
    @Override
    public void process(Map<String, Object> data) {
        oneDML.updateEodProcLog(data);
    }
}
