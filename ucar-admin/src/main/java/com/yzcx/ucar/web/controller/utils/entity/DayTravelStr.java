package com.yzcx.ucar.web.controller.utils.entity;

import lombok.Data;
import org.springframework.cache.annotation.Cacheable;

/**
 * author: yuzq
 * create: 2020-12-19 10:02
 **/
@Data
public class DayTravelStr implements Comparable<DayTravelStr> {

    private String id;
    private String accTime;

    @Override
    public int compareTo(DayTravelStr o) {
        String substring = o.getAccTime().substring(0, 2);
        int i = Integer.parseInt(substring);
        return Integer.parseInt(this.getAccTime().substring(0,2))-i;
    }
}
