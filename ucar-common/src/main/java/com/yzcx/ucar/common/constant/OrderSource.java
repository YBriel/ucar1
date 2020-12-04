package com.yzcx.ucar.common.constant;

public enum OrderSource {
	
	ORDER_SOURCE_YIZHI(1,"易至"),
	ORDER_SOURCE_BAIDU(2, "百度"),
	ORDER_SOURCE_CTRIP (3, "携程"),
	ORDER_SOURCE_JXHK (4, "江西航空"),
	ORDER_SOURCE_MEITUAN(5, "美团"),
	ORDER_SOURCE_GAODE(6, "高德"),
	ORDER_SOURCE_HOTEL(9, "酒店"),
	ORDER_SOURCE_CARCODE(11, "一车一码");
	private int value;
	 
    private OrderSource(int value, String desr) {
        this.value = value;
    }
    public int getValue() {
        return value;
    }
}
