package com.emcs.serviceImpl.busniess.recharge;

import com.emcs.Super.ServiceTransactionalY;
import com.emcs.exception.BusiException;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by Administrator on 2018/2/4.
 */
@Service
public class MerchRecharge extends ServiceTransactionalY{
    @Override
    protected void process(Map<String, Object> param) {
        if(oneSelect.selectIsExistVaMerchInfo(param)==0)throw new BusiException("商户信息不存在或者处于异常状态","600007");




    }
}
