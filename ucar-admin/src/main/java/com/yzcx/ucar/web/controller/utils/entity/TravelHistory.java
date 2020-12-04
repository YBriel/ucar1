package com.yzcx.ucar.web.controller.utils.entity;

import lombok.Data;

/**
 * author: yuzq
 * create: 2020-12-04 13:34
 **/
@Data
public class TravelHistory {

    private String time;
    private Double lon;
    private Double lat;
    private Double speed;
    private Integer azimuth;
    private Integer accelerate;
    private Integer decelerate;
    private Boolean alarmPoint;
    private Integer corner;
}
