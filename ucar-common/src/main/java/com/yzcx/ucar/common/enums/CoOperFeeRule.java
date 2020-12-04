package com.yzcx.ucar.common.enums;

public enum CoOperFeeRule {
	PERCENT(1, "按百分比");
	
	
	private int value;
	private CoOperFeeRule(int value, String desr){
		this.value=value;
	}

	public int getValue() {
	    return value;
	}
}
