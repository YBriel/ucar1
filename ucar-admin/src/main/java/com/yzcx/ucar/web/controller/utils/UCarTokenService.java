package com.yzcx.ucar.web.controller.utils;

import com.yzcx.ucar.common.utils.StringUtils;
import com.yzcx.ucar.web.controller.utils.entity.*;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.List;

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
    public List<DayTravelStr> queryDayTravelStr(@RequestBody StrEntity strEntity) throws ParseException {
        if(StringUtils.isEmpty(strEntity.getDeviceId()) || StringUtils.isEmpty(strEntity.getTime())){
            return null;
        }
        return TokenUtil.queryDayTravelStr(strEntity.getDeviceId(),strEntity.getTime());
    }

    @RequestMapping("queryTravelHistory")
    public static  List<TravelHistory> queryTravelHistory(String id){
        return TokenUtil.queryTravelHistory(id);
    }
}
