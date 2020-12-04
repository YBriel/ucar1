package com.yzcx.ucar.common.enums;

/**
 * 司机分成结余归谁
 * 
 * @author maozhr
 */
public enum DriverMoney2WhoRule{
	TO_CP(1, "挂靠CP"),
	TO_PLATFORM(2, "归CP");
	
	private int value;
	 
    private DriverMoney2WhoRule(int value, String desr) {
        this.value = value;
    }
    public int getValue() {
        return value;
    }
}
