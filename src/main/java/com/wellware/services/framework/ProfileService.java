package com.wellware.services.framework;

import com.wellware.DTO.ProfileDTO;
import com.wellware.data.entities.Profile;
import com.wellware.data.entities.VerificationToken;
import com.wellware.exceptions.EmailExistsException;

public interface ProfileService {

    Profile registerNewAccount(ProfileDTO profileDTO)throws EmailExistsException;

    void saveRegisteredUser(Profile user);

    void createVerificationToken(Profile user, String token);

    VerificationToken getVerificationToken(String VerificationToken);

    Profile getProfileByEmail(String email);

    Profile verifyProfile(String verificationToken);
}
