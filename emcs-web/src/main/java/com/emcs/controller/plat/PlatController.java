package com.emcs.controller.plat;

import com.emcs.service.common.VaPlatInfoService;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

@Controller
public class PlatController {
    @Resource
    VaPlatInfoService vaPlatInfoService;
}
