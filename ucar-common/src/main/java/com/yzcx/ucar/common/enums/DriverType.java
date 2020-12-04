package com.yzcx.ucar.common.enums;

public enum DriverType {
	EMPLOYEE_DRIVER(1, "自营司机"),
	SOCIAL_DRIVER(2, "社会司机");
	
	private int value;
	 
    private DriverType(int value, String desr) {
        this.value = value;
    }
    public int getValue() {
        return value;
    }
}
