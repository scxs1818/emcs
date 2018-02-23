package com.emcs.busniess.common;

import com.emcs.Constant.BusiConstant;
import com.emcs.pub.runtime.core.Logger;
import com.emcs.pub.runtime.core.LoggerFactory;
import com.emcs.supers.PubServiceY;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * Created by Administrator on 2018/2/22.
 */
@Service
public class ValidateIsDaySwitch{
    protected Logger log = LoggerFactory.getLogger(ValidateIsDaySwitch.class);

    public boolean process(Map<String, Object> data) {
        log.info("ValidateIsDaySwitch="+data);
        String server_date = new SimpleDateFormat(BusiConstant.FOR_STAN_08).format(new Date());
        Object run_date = data.get("run_date");
        log.info("server_date="+server_date+",run_date="+run_date);
        if(!server_date.equals(run_date)){
            log.warn("尚未进行日切,不能进行日终");
            return false;
        }
        return true;
    }
}