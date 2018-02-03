package com.emcs.controller.plat;

        import com.emcs.serviceImpl.busniess.PlatformRegister;
        import com.emcs.util.CommonResult;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.web.bind.annotation.RequestMapping;
        import org.springframework.web.bind.annotation.RequestParam;
        import org.springframework.web.bind.annotation.RestController;

        import java.util.Map;

@RestController
public class PlatController {
    @Autowired
    PlatformRegister platRegister;


    @RequestMapping("/plat")
    public CommonResult platRegister(@RequestParam Map<String,Object> param) {
        return  platRegister.doService(param);
    }
}
