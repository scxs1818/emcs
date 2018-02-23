package com.emcs.eod;

import com.emcs.mapper.OneTableDMLMapper;
import com.emcs.mapper.OneTableSelectMapper;
import com.emcs.supers.SuperTask;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Map;

/**
 * Created by Administrator on 2018/2/18.
 */
@Service
public class FromRecharBalToUsableBal extends SuperTask{
    public static FromRecharBalToUsableBal frbtu;
    @PostConstruct
    public void init() {
        frbtu = this;
    }


    public void process(Map<String, Object> data, OneTableSelectMapper oneSelect, OneTableDMLMapper oneDML) {
        frbtu.
        frbtu.oneS.selectVaCustVirtualAcctBalLock(null);
        frbtu.oneDML.dayEndTransferAmtForCust(null);
        frbtu.oneS.selectVaMerchVirtualAcctBalLock(null);
        frbtu.oneDML.dayEndTransferAmtForMerch(null);
    }
}