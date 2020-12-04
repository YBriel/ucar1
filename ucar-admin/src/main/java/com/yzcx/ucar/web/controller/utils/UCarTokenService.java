package com.yzcx.ucar.web.controller.utils;

import com.alibaba.fastjson.JSONObject;
import com.yzcx.ucar.web.controller.utils.entity.DevicePosition;
import com.yzcx.ucar.web.controller.utils.entity.PageDevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.security.MessageDigest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * author: yuzq
 * create: 2020-11-24 14:30
 **/
@RestController
@RequestMapping("test")
public class UCarTokenService {

    @RequestMapping("queryAllData")
    public List<DevicePosition> listAllDevices() {
        List<PageDevice> pageDevices = TokenUtil.listAll(1, 10);
        return TokenUtil.listCurrentPos(pageDevices);
    }


}
