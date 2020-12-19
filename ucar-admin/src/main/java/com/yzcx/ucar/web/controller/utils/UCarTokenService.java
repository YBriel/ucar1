package com.yzcx.ucar.web.controller.utils;

import com.alibaba.fastjson.JSONObject;
import com.yzcx.ucar.common.utils.StringUtils;
import com.yzcx.ucar.web.controller.utils.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.security.MessageDigest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

/*    @RequestMapping("queryDayTravelStr")
    public List<DayTravelStr> queryDayTravelStr(@RequestParam("deviceId") String deviceId,@RequestParam("myTime")  String myTime) {
        if(StringUtils.isEmpty(deviceId)){
            return null;
        }
        String format1="";
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        // Date parse = format.parse(time);
        format1 = format.format(myTime);
        return TokenUtil.queryDayTravelStr(deviceId,format1);
    }*/

    @RequestMapping("queryDayTravelStr")
    public List<DayTravelStr> queryDayTravelStr(@RequestBody StrEntity strEntity) {
        if(StringUtils.isEmpty(strEntity.getDeviceId())){
            return null;
        }
        String format1="";
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date parse = format.parse(strEntity.getTime());
            format1 = format.format(parse);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return TokenUtil.queryDayTravelStr(strEntity.getDeviceId(),format1);
    }

    @RequestMapping("queryTravelHistory")
    public static  List<TravelHistory> queryTravelHistory(String id){
        return TokenUtil.queryTravelHistory(id);
    }
}
