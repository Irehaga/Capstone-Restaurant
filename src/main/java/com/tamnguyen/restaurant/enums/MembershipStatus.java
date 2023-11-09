package com.tamnguyen.restaurant.enums;

/**
 * @author Tam Nguyen
 */
public enum MembershipStatus {

    ACTIVE("Active"),
    INACTIVE("Inactive");


    private final String displayStatus;

    MembershipStatus(String displayStatus){
        this.displayStatus = displayStatus;
    }

    public String getDisplayStatus(){
        return displayStatus;
    }
}
