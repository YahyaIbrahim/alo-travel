package com.wellware.config.security;

import com.wellware.data.entities.Profile;
import com.wellware.data.repositories.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private ProfileRepository profileRepository;


    @Override
    public UserDetails loadUserByUsername (String username) throws UsernameNotFoundException {
        Profile profile = profileRepository.findByEmail(username);

        if(profile == null || !profile.isEnabled())
            throw  new UsernameNotFoundException("Username " + username + " Not found");
        else
            return new User(profile.getEmail(), profile.getPassword(), getAuthority(profile));

    }


    private Set<SimpleGrantedAuthority> getAuthority(Profile profile) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        profile.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
        });
        return authorities;
    }

}
