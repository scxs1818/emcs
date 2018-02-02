package com.emcs.serviceImpl.busniess;


        import com.emcs.service.busniess.PlatRegisterService;
        import com.emcs.service.common.*;
        import com.emcs.util.CommonResult;
        import com.emcs.util.CommonUtils;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Service;
        import org.springframework.transaction.annotation.Transactional;

        import javax.annotation.Resource;
        import java.util.HashMap;
        import java.util.List;
        import java.util.Map;
@Transactional
@Service
public class PlatRegisterServiceImpl implements PlatRegisterService {
    private static int PLAT_ID_SEQ_NO_LENGTH = 4;
    @Autowired
    VaPlatInfoService VaPlatInfoService;
    @Autowired
    CommonService commonService;
    @Autowired
    VaPlatAccInfoService vaPlatAccInfoService;
    @Autowired
    VaPlatVirtualAcctService vaPlatVirtualAcctService;
    @Autowired
    VaPlatVirtualAcctBalService vaPlatVirtualAcctBalService;

    @Override
    public CommonResult platRegister(Map<String,Object> map) {
        CommonResult result =new CommonResult();
        try{
            if(map.isEmpty()){
                commonResult(result,"为传入参数");
                return result;
            }
            // 校验支付商户编码是否存在
            String payMerchId =(String)map.get("pay_merch_id");
            List<Map<String,Object>> list =VaPlatInfoService.selectByPayMerchId(payMerchId);
            if(list!=null&&list.size()>0){
                commonResult(result,"该平台已经注册");
                return result;
            }
            // 获取最新平台编码
            String currentPlatSeq = ""+commonService.getNextVal("PLAT_SEQ_NO");
            String platId ="3"+ CommonUtils.getSeq(PLAT_ID_SEQ_NO_LENGTH, currentPlatSeq);
            map.put("plat_id",platId);
            //保存商户平台信息
            int i=  VaPlatInfoService.insertVaPlatInfo(map);
            System.out.printf("i========"+i);
            //保存登记平台资金清算专户和平台结算账户
            vaPlatAccInfoService.insertVaPlatAccInfo(map);

            int m=  vaPlatVirtualAcctService.insertVaPlatVirtualAcct(map);

            vaPlatVirtualAcctBalService.insertVaPlatVirtualAcctBal(map);
            result.setDate(null);
            result.setMsg("注册商户信息成功");
            result.setStatus("S");
        }catch (Exception e){
            e.printStackTrace();
            commonResult(result, e.getMessage()) ;
        }

        return result;
    }
    public  void commonResult(CommonResult result, String msg) {
        result.setDate(null);
        result.setMsg(msg);
        result.setStatus("F");
    }
}
