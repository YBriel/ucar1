package com.yzcx.ucar.common.enums;

public enum OrderType {
    INSTANCE_CAR(1, "即时专送"),
    BOOKING_CAR(2, "预约专车"),
    AIRPORT_DELIVERY(3, "机场专送"),
    INTERCITY_CAR(4, "城际专车"),
    INTERCITY_CONTRACT_ORDER(5, "城际包车"),
    PICKUP_ORDER(6, "接机专车"),
    PICKUP_CARPOOL_ORDER(7, "接机拼车"),
    DROPOFF_ORDER(8, "送机"),
    DROPOFF_CARPOOL_ORDER(9, "送机拼车"),
    BUSSINESS_ORDER(10, "VIP商务");

    private int type;
    private String desc;

    OrderType(int type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    public int getType() {
        return type;
    }

    public String getDesc() {
        return desc;
    }

    public static String getOrderTypeDesc(int type) {
        OrderType[] values = values();
        for (OrderType value : values) {
            if (value.getType() == type) {
                return value.getDesc();
            }
        }
        return null;
    }

}
