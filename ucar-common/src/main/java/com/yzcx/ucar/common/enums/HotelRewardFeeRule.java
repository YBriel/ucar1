package com.yzcx.ucar.common.enums;

public enum HotelRewardFeeRule {
	PERCENT_REWARD(1, "按比例奖励"),
	FIX_REWARD(2, "固定金额奖励"),
	ZERO_REWARD(3, "不提供奖励"),
	DEFINE_REWARD(4, "自定义奖励");
	
	private int value;
	private HotelRewardFeeRule(int value, String desr){
		this.value=value;
	}

	public int getValue() {
	    return value;
	}
}
