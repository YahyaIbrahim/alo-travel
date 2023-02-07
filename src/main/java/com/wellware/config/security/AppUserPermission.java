package com.wellware.config.security;

public enum AppUserPermission {

    TRIP_READ("trip:read"),
    TRIP_CREATE("trip:create"),
    TRIP_UPDATE("trip:update"),
    TRIP_DELETE("trip:delete"),


    RESERVATION_CREATE("reservation:create"),
    RESERVATION_READ("reservation:read"),
    RESERVATION_UPDATE("reservation:update"),
    RESERVATION_DELETE("reservation:delete"),
    RESERVATION_ACCEPT("reservation:accept"),
    RESERVATION_REJECT("reservation:reject"),

    REVIEW_CREATE("review:create"),
    REVIEW_READ("review:read"),
    REVIEW_UPDATE("review:update"),
    REVIEW_DELETE("review:delete");


    private final String permission;

    AppUserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
