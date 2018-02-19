package com.emcs.busniess.register;
import com.emcs.Constant.BusiConstant;
import com.emcs.Constant.ErrorCodeConstant.*;
import com.emcs.supers.ServiceTransactionalY;
import com.emcs.Constant.BusiConstant.*;
import com.emcs.exception.BusiException;
import com.emcs.supers.SupperService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
@Service
public class PlatformRegister extends ServiceTransactionalY {

    public void process(Map<String, Object> data){

        // 1.校验支付商户编码是否存在
        if(oneSelect.selectIsExistVaPlatInfo(data)>0) throw new BusiException(PlatErrorCode.VAP004.code(),PlatErrorCode.VAP004.val());

        // 2.生成平台编码
        data.put("plat_id",oneSelect.getNextVal(Quence.PLAT.val()));
        data.put("status","N");//首次注册为正常
        data.put("payment_type",0);//手动结算
        data.put("currency","RMB");
        data.put("create_date",data.get("tran_date")+""+data.get("tran_time"));

        //3.注册平台信息
        oneDML.insertVaPlatInfo(data);

        //4.保存登记平台资金清算专户和平台结算账户
        //4.1绑定平台结算账户
        Object acct_no=data.get("settle_acct");
        if(acct_no!=null&&!"".equals(acct_no.toString().trim())){
            data.put("acct_type", BusiConstant.ACCT_TYPE_PLAT_SETTLE);
            data.put("acct_no",acct_no);
            data.put("acacct_noct_category",data.get("settle_acct_category"));
            oneDML.insertVaPlatAccInfo(data);
            data.put("mermber_id",data.get("plat_id"));
            data.put("bind_seq_no",oneSelect.getNextVal(Quence.BIND_SEQ_NO.val()));
            oneDML.insertVaBindSeq(data);
        }

        //4.2绑定平台资金清算专户
        data.put("acct_type", BusiConstant.ACCT_TYPE_PLAT_DEPOSIT);
        data.put("acct_no",data.get("deposit_acct"));
        data.put("acct_name","资金清算专户");
        data.put("is_this_bank","Y");
        data.put("acct_category",data.get("deposit_acct_category"));
        data.put("acct_br_no","支付机构代码");
        data.put("acct_br_name","支付机构名称");
        oneDML.insertVaPlatAccInfo(data);
        data.put("bind_seq_no",oneSelect.getNextVal(Quence.BIND_SEQ_NO.val()));
        oneDML.insertVaBindSeq(data);

        //5.查询虚拟账户类型
        data.put("vir_acct_type","101");
        List<Map<String,Object>> virAcctTypeList = oneSelect.selectVaVirtualAcctType(data);
        data.putAll(virAcctTypeList.get(0));

        //5.注册虚拟账户信息
        data.put("plat_virid", oneSelect.getNextVal(Quence.PLAT_VIRT.val()));
        oneDML.insertVaPlatVirtualAcct(data);

        //6.注册虚拟账户余额信息
        data.put("actural_bal",0);
        data.put("usable_bal",0);
        data.put("freeze_bal",0);
        data.put("recharge_bal",0);
        oneDML.insertVaPlatVirtualAcctBal(data);
    }
}