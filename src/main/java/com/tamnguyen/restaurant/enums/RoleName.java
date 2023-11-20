package com.tamnguyen.restaurant.enums;

/**
 * @author Tam Nguyen
 */
public enum RoleName {

//    ROLE_CUSTOMER("ROLE_CUSTOMER"),
    ROLE_ADMIN("ROLE_ADMIN");

    private final String displayName;

    RoleName(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

}
