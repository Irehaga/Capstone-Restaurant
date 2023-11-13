package com.tamnguyen.restaurant.enums;

import lombok.Getter;

/**
 * @author Tam Nguyen
 */

@Getter
public enum MembershipStatus {

    ACTIVE("Active"),
    INACTIVE("Inactive");


    private final String displayStatus;

    MembershipStatus(String displayStatus){
        this.displayStatus = displayStatus;
    }


}
