package com.wellware.config.security;


import com.wellware.data.entities.Authorities;
import com.wellware.data.entities.Profile;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Set;

public class CustomUserDetails extends Profile implements UserDetails {


    public CustomUserDetails(Profile profile){
        super(profile);
    }

    @Override
    public Set<Authorities> getAuthorities() {
        return super.getAuthorities();
    }

    @Override
    public String getPassword() {
        return super.getPassword();
    }

    @Override
    public String getUsername() {
        return super.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
