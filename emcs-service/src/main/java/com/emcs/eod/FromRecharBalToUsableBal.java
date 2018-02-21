package com.emcs.eod;

import com.emcs.mapper.OneTableDMLMapper;
import com.emcs.mapper.OneTableSelectMapper;
import com.emcs.supers.PubService;
import com.emcs.supers.ServiceTransactionalY;
import com.emcs.supers.SuperTask;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * Created by Administrator on 2018/2/18.
 */
@Service
public class FromRecharBalToUsableBal extends SuperTask {

    @Transactional
    @Override
    public void process(Map<String, Object> data, OneTableSelectMapper oneSelect, OneTableDMLMapper oneDML) {
        oneSelect.selectVaCustVirtualAcctBalLock(null);
        oneDML.dayEndTransferAmtForCust(null);
        oneSelect.selectVaMerchVirtualAcctBalLock(null);
        oneDML.dayEndTransferAmtForMerch(null);
    }
}
