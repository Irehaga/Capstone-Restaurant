package com.tamnguyen.restaurant.enums;

import lombok.Getter;

/**
 * @author Tam Nguyen
 */

@Getter
public enum MembershipType {

    SILVER("Silver Membership"),
    GOLD("Gold Membership");

    private final String displayName;

    MembershipType(String displayName){
        this.displayName = displayName;
    }


}
