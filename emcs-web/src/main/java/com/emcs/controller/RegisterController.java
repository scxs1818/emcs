package com.emcs.controller;

        import com.emcs.busniess.register.CustRegister;
        import com.emcs.busniess.register.MerchRegister;
        import com.emcs.busniess.register.PlatformRegister;
        import com.emcs.util.CommonResult;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.web.bind.annotation.RequestMapping;
        import org.springframework.web.bind.annotation.RequestParam;
        import org.springframework.web.bind.annotation.RestController;

        import java.util.Map;

/**
 * 注册
 * Created by Administrator on 2018/2/5.
 */
@RequestMapping("/register")
@RestController
public class RegisterController {
    @Autowired
    PlatformRegister plat;
    @Autowired
    MerchRegister merch;
    @Autowired
    CustRegister cust;

    @RequestMapping("/plat")
    public CommonResult platRegister(@RequestParam Map<String,Object> param) {
        return  plat.doService(param);
    }

    @RequestMapping("/merch")
    public CommonResult merchRegister(@RequestParam Map<String,Object> param) {
        return  merch.doService(param);
    }

    @RequestMapping("/cust")
    public CommonResult custRegister(@RequestParam Map<String,Object> param) {
        return  cust.doService(param);
    }
}
