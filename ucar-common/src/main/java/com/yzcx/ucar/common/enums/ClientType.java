package com.yzcx.ucar.common.enums;

/**
 * 车型定义
 * @author maozh
 *
 */
public enum ClientType {
	 C_APP(1, "乘客端APP"),
	 D_APP(2, "司机端APP"),
	 W_BROADCAST(3,"公众号"),
	 W_PROGRAM(4, "小程序");
	
	 private int value;
	 private ClientType(int value, String desr) {
		        this.value = value;
	 }
	    
	 public int getValue() {
	        return value;
	 }
}
