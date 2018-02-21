package com.emcs.controller;

import com.emcs.busniess.card.BindCard;
import com.emcs.busniess.card.UnbundCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 绑卡
 * Created by Administrator on 2018/2/4.
 */

@RequestMapping("/card")
@RestController
public class CardController {

    @Autowired
    BindCard bindCard;
    @Autowired
    UnbundCard unbindCard;

    @RequestMapping("/bind")
    public void tBindCard(@RequestParam Map<String, Object> data) {
        bindCard.doService(data);
    }


    @RequestMapping("/unbind")
    public void tUnbindCard(@RequestParam Map<String, Object> data) {
        unbindCard.doService(data);
    }
}