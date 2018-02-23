package com.emcs.eod;

import com.emcs.mapper.OneTableDMLMapper;
import com.emcs.mapper.OneTableSelectMapper;
import com.emcs.supers.SuperTask;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Map;

/**
 * 批量备份虚拟账户余额信息
 * Created by Administrator on 2018/2/21.
 */
@Service
public class BatchVirAccountBalance extends SuperTask{
    public static BatchVirAccountBalance bvab;
    @PostConstruct
    public void init() {
        bvab = this;
    }
    @Override
    public void process(Map<String, Object> data) {
        bvab.manyDML.batchVaCustVirtualAcctBal(data);
        bvab.manyDML.batchVaMerchVirtualAcctBal(data);
    }
}
