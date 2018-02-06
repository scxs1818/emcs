package com.emcs.Super;

import com.emcs.exception.BusiException;
import com.emcs.mapper.ManyTableDMLMapper;
import com.emcs.mapper.ManyTableSelectMapper;
import com.emcs.mapper.OneTableDMLMapper;
import com.emcs.mapper.OneTableSelectMapper;
import com.emcs.pub.runtime.core.Logger;
import com.emcs.pub.runtime.core.LoggerFactory;
import com.emcs.tool.DataBaseOperate;
import com.emcs.tool.ServiceUtil;
import com.emcs.util.CommonResult;
import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Created by Administrator on 2018/2/3.
 */
@Transactional
public abstract class ServiceTransactionalY extends ServiceSupper{
    protected CommonResult before(Map<String, Object> param){
//        Map sysMap = (Map)param.get("SYS_HEAD");
//        param.put("TRAN_DATE",sysMap.get("TRAN_DATE"));
//        oneDML.insertVaCustAccInfo(param);//插入交易流水
        return null;
    }
    protected CommonResult after(Map<String, Object> param){
//        oneDML.UpdateVaCustAccInfo(param);//更新交易流水
        return null;
    }
}
