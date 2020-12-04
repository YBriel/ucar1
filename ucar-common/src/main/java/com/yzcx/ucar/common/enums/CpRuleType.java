package com.yzcx.ucar.common.enums;

public enum CpRuleType {
	SYSTEM(1,"系统"),
	COMMON(2, "通用配置"),
	 DEFINE(3, "自定义");
	
	 private int value;
	    
	 private CpRuleType(int value, String desr) {
		        this.value = value;
	 }
	    
	 public int getValue() {
	        return value;
	 }
}
