package com.tamnguyen.restaurant.enums;

/**
 * @author Tam Nguyen
 */
public enum OrderStatus {

    PENDING("Pending"),
    CONFIRMED("Confirmed"),
    IN_PROGRESS("In Progress"),
    READY("Ready"),
    DELIVERED("Delivered"),
    CANCELLED("Cancelled");


    private final String displayOrderStatus;

    OrderStatus(String display){
        this.displayOrderStatus = display;
    }

    public String getDisplayOrderStatus(){
        return displayOrderStatus;
    }
}
