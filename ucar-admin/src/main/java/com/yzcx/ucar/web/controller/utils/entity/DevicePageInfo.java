package com.yzcx.ucar.web.controller.utils.entity;

import lombok.Data;

import java.util.List;

/**
 * author: yuzq
 * create: 2020-11-24 18:44
 **/
@Data
public class DevicePageInfo {

    private Integer totalPage;
    private Integer totalRecord;
    private Integer pageSize;
    private Integer pageNum;
    private List<PageDevice> data;
}
