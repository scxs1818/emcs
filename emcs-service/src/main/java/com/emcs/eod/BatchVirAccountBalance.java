package com.emcs.eod;

import com.emcs.mapper.OneTableDMLMapper;
import com.emcs.mapper.OneTableSelectMapper;
import com.emcs.supers.SuperTask;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 批量备份虚拟账户余额信息
 * Created by Administrator on 2018/2/21.
 */
@Service
public class BatchVirAccountBalance extends SuperTask{
    @Override
    public void process(Map<String, Object> data) {
        manyDML.batchVaCustVirtualAcctBal(data);
        manyDML.batchVaMerchVirtualAcctBal(data);
    }
}
