package com.emcs.busniess.test;

import com.emcs.Constant.BusiConstant;
import com.emcs.Super.PubService;
import com.emcs.Super.ServiceTransactionalY;
import com.emcs.cache.CacheUtil;
import com.emcs.pub.runtime.core.Logger;
import com.emcs.pub.runtime.core.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/2/11.
 */
@Service
public class TestCache extends PubService{

    @Override
    public void process(Map<String, Object> param) {

    }
}
