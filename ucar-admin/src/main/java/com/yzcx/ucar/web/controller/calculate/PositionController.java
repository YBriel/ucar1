package com.yzcx.ucar.web.controller.calculate;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * author: yuzq
 * create: 2020-11-23 11:33
 **/
@Controller
@RequestMapping("/calculate/position")
public class PositionController {

    private String prefix = "calcute/position";

    @GetMapping
    @RequiresPermissions("calcute:position:view")
    public String position(){
        return prefix + "/position";
    }

    @GetMapping("aa")
    @ResponseBody
    public String positiona(){
        return prefix + "/position";
    }
}
