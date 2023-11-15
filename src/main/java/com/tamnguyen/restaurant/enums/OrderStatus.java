package com.tamnguyen.restaurant.enums;

import lombok.Getter;

/**
 * @author Tam Nguyen
 */
@Getter
public enum OrderStatus {

    PENDING("Pending"),
    CONFIRMED("Confirmed"),
    IN_PROGRESS("In Progress"),
    READY("Ready"),
    DELIVERED("Delivered"),
    NEW("New"),
    CANCELLED("Cancelled");


    private final String displayOrderStatus;

    OrderStatus(String display){
        this.displayOrderStatus = display;
    }


}
