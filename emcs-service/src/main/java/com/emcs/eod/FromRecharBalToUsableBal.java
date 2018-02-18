package com.emcs.eod;

import com.emcs.supers.ServiceTransactionalY;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by Administrator on 2018/2/18.
 */
@Service
public class FromRecharBalToUsableBal extends ServiceTransactionalY {

    @Override
    protected void process(Map<String, Object> data) {
        oneSelect.selectVaCustVirtualAcctBalLock(null);
        oneDML.dayEndTransferAmtForCust(null);
        oneSelect.selectVaMerchVirtualAcctBalLock(null);
        oneDML.dayEndTransferAmtForMerch(null);
    }
}
