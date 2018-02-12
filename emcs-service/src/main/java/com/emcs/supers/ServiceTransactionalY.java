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
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Created by Administrator on 2018/2/3.
 */
@Transactional
public abstract class ServiceTransactionalY {
    @Autowired
    InsertCmTranSeq icts;
    @Autowired
    UpdateCmTranSeq ucts;
    protected CommonResult before(Map<String, Object> param) {
        icts.process(param);//插入交易流水
        return null;
    }

    protected CommonResult after(Map<String, Object> param) {
        ucts.process(param);//更新交易流水
        return null;
    }

    protected Logger log = LoggerFactory.getLogger(ServiceTransactionalY.class);
    @Resource
    protected OneTableSelectMapper oneSelect;
    @Resource
    protected OneTableDMLMapper oneDML;
    @Resource
    protected ManyTableSelectMapper manySelect;
    @Resource
    protected ManyTableDMLMapper manyDML;
    protected CommonResult result = new CommonResult();

    public CommonResult doService(Map<String, Object> param) {
        try {
            before(param);
            process(param);
            result.setMsg("交易成功");
            result.setStatus("S");
            param.put("tran_status","S");
        } catch (Exception e) {
            log.error("交易失败", e);
            result.setMsg(e.getMessage());
            result.setStatus("F");
            param.put("tran_status","F");
            DoException.doThrowException(e);
        }finally {
            after(param);
        }
        return result;
    }

    protected abstract void process(Map<String, Object> param);
}