package com.yzcx.ucar.common.enums;

public enum CpType {
	PROVINCE_CP(10, "省运营商"),
	CITY_CP(20, "市运营商"),
	CITY_DMP(30, "市级DMP"),
	COUNTY_DMP(40, "县DMP");
	
	private int value;
	private CpType(int value, String desr){
		this.value=value;
	}

	public int getValue() {
	    return value;
	}
}
