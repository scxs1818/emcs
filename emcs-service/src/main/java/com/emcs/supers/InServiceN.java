package com.emcs.supers;

import com.emcs.busniess.common.InsertCmTranSeq;
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

import javax.annotation.Resource;
import java.util.Map;

/**
 * 通信接入的Service,非事务执行
 * Created by Administrator on 2018/2/3.
 */
public abstract class InServiceN {
    protected Logger log = LoggerFactory.getLogger(InServiceN.class);
    @Resource
    protected OneTableSelectMapper oneSelect;
    @Resource
    protected OneTableDMLMapper oneDML;
    @Resource
    protected ManyTableSelectMapper manySelect;
    @Resource
    protected ManyTableDMLMapper manyDML;
    protected CommonResult result = new CommonResult();
    public CommonResult doService(Map<String, Object> data){
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
    protected abstract void process(Map<String, Object> data);
    @Autowired
    InsertCmTranSeq icts;

    protected CommonResult before(Map<String, Object> data) {
        icts.process(data);//插入交易流水
        return result;
    }
    @Autowired
    UpdateCmTranSeq ucts;
    protected CommonResult after(Map<String, Object> data) {
        ucts.process(data);//更新交易流水
        return result;
    }
}
