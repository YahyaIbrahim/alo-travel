package com.wellware.data.repositories;

import com.wellware.data.entities.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Long> {

    Profile findByEmail(String email);
    Profile findFirstById(Long profileId);
}
