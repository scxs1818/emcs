package com.emcs.supers;

import com.emcs.busniess.common.InsertCmAcctTranSeq;
import com.emcs.busniess.common.InsertCmTranSeq;
import com.emcs.busniess.common.UpdateCmAcctTranSeq;
import com.emcs.busniess.common.UpdateCmTranSeq;
import com.emcs.exception.DoException;
import com.emcs.mapper.ManyTableDMLMapper;
import com.emcs.mapper.ManyTableSelectMapper;
import com.emcs.mapper.OneTableDMLMapper;
import com.emcs.mapper.OneTableSelectMapper;
import com.emcs.pub.runtime.core.Logger;
import com.emcs.pub.runtime.core.LoggerFactory;
import com.emcs.util.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Created by Administrator on 2018/2/3.
 */
public abstract class ServiceTransactionalY {
    protected Logger log = LoggerFactory.getLogger(ServiceTransactionalY.class);
    @Resource
    protected OneTableSelectMapper oneSelect;
    @Resource
    protected OneTableDMLMapper oneDML;
    @Resource
    protected ManyTableSelectMapper manySelect;
    @Resource
    protected ManyTableDMLMapper manyDML;

    @Autowired
    InsertCmTranSeq icts;
    @Autowired
    UpdateCmTranSeq ucts;

    protected CommonResult before(Map<String, Object> data) {
        icts.process(data);//插入交易流水
        return result;
    }
    protected CommonResult after(Map<String, Object> data) {
        ucts.process(data);//更新交易流水
        return result;
    }

    @Transactional
    public CommonResult doService(Map<String, Object> data) {
        log.info("请求的数据:"+data);
        try {
            before(data);
            process(data);
            result.setMsg("交易成功");
            result.setStatus("S");
            data.put("tran_status","01");
        } catch (Exception e) {
            log.error("交易失败", e);
            String errormsg = e.getMessage();
            if(errormsg!=null&&errormsg.length()>500)
                errormsg = errormsg.substring(0,500);
            result.setMsg(errormsg);
            result.setStatus("F");
            data.put("tran_status","02");
            data.put("fail_reason",errormsg);
            DoException.doThrowException(e);
        }finally {
            after(data);
        }
        return result;
    }

    protected CommonResult result = new CommonResult();
    protected abstract void process(Map<String, Object> data);
}