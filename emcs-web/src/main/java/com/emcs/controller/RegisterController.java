package com.emcs.controller;

        import com.emcs.serviceImpl.busniess.CustRegister;
        import com.emcs.serviceImpl.busniess.MerchRegister;
        import com.emcs.serviceImpl.busniess.PlatformRegister;
        import com.emcs.util.CommonResult;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.web.bind.annotation.RequestMapping;
        import org.springframework.web.bind.annotation.RequestParam;
        import org.springframework.web.bind.annotation.RestController;

        import java.util.Map;
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
