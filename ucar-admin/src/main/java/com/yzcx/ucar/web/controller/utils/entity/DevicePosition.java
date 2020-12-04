package com.yzcx.ucar.web.controller.utils.entity;

import lombok.Data;

/**
 * author: yuzq
 * create: 2020-11-24 18:33
 **/
@Data
public class DevicePosition {
    private String deviceId; //设备号
    private String time;  //数据上传时间
    private Double lon;//经度
    private Double lat;//纬度
    private Integer speed;//速度
    private Integer azimuth;//方位角
    private String acc;//acc状态
    private Integer online;//在线状态 0-熄火 1-启动
    private Double lbsLat;//基站纬度 0-离线 1-在线
    private Double lbsLon;//基站经度
}
