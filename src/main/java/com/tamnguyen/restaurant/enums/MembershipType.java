package com.tamnguyen.restaurant.enums;

/**
 * @author Tam Nguyen
 */
public enum MembershipType {

    SILVER("Silver Membership"),
    GOLD("Gold Membership");

    private final String displayName;

    MembershipType(String displayName){
        this.displayName = displayName;
    }

    public String getDisplayName(){
        return displayName;
    }
}
