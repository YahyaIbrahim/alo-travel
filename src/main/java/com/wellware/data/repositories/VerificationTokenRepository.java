package com.wellware.data.repositories;


import com.wellware.data.entities.Profile;
import com.wellware.data.entities.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VerificationTokenRepository
        extends JpaRepository<VerificationToken, Long> {

    VerificationToken findByToken(String token);

    VerificationToken findByUser(Profile user);
}