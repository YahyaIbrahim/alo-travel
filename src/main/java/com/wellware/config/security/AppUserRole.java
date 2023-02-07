package com.wellware.config.security;

import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import com.google.common.collect.Sets;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static com.wellware.config.security.AppUserPermission.*;


@Getter
public enum AppUserRole {


    USER(Sets.newHashSet(TRIP_READ,
            RESERVATION_CREATE,
            RESERVATION_READ,
            RESERVATION_UPDATE,
            RESERVATION_DELETE,
            REVIEW_CREATE,
            REVIEW_READ,
            REVIEW_UPDATE,
            REVIEW_DELETE)),

    SUPERADMIN(Sets.newHashSet(TRIP_READ,
            TRIP_CREATE,
            TRIP_UPDATE,
            TRIP_DELETE,
            RESERVATION_CREATE,
            RESERVATION_READ,
            RESERVATION_UPDATE,
            RESERVATION_DELETE,
            RESERVATION_ACCEPT,
            RESERVATION_REJECT,
            REVIEW_CREATE,
            REVIEW_READ,
            REVIEW_UPDATE,
            REVIEW_DELETE));

    private final Set<AppUserPermission> permissions;

    AppUserRole(Set<AppUserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthorities() {
        java.util.Set<org.springframework.security.core.authority.SimpleGrantedAuthority> permissions = getPermissions().stream()
                .map(permission -> new org.springframework.security.core.authority.SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
        permissions.add(new org.springframework.security.core.authority.SimpleGrantedAuthority("ROLE_" + this.name()));
        return permissions;
    }
}
