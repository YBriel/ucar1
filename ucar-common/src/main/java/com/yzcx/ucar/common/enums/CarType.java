package com.yzcx.ucar.common.enums;

/**
 * 车型定义
 * @author maozh
 *
 */
public enum CarType {
	 ECONOMIC(1, "经济、快车"),
	 COMFORTABLE(2, "舒适"),
	 BUSINESS(3,"商务"),
	 LUXURY(4, "豪华"),
	 TAXI(5, "出租车");
	
	 private int value;
	    
	 private CarType(int value, String desr) {
		        this.value = value;
	 }
	    
	 public int getValue() {
	        return value;
	 }
}
