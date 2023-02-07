package com.wellware.services.implementation;

import com.wellware.DTO.ProfileDTO;
import com.wellware.data.entities.Authorities;
import com.wellware.data.entities.Country;
import com.wellware.data.entities.Profile;
import com.wellware.data.entities.VerificationToken;
import com.wellware.data.repositories.AuthoritiesRepository;
import com.wellware.data.repositories.CountryRepository;
import com.wellware.data.repositories.ProfileRepository;
import com.wellware.data.repositories.VerificationTokenRepository;
import com.wellware.exceptions.EmailExistsException;
import com.wellware.services.framework.ProfileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;


@Service
@Slf4j
public class ProfileServiceImpl implements ProfileService {

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private VerificationTokenRepository tokenRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private AuthoritiesRepository authoritiesRepository;

    @Override
    public Profile registerNewAccount(ProfileDTO profileDTO) throws EmailExistsException {
        if (emailExist(profileDTO.getEmail())) {
            throw new EmailExistsException(
                    "There is an account with that email adress: "
                            + profileDTO.getEmail());
        }

        Optional<Country> country = countryRepository.findById(profileDTO.getCountryId());
        Profile user = new Profile();
        user.setDisplayName(profileDTO.getDisplayName());
        user.setPassword(passwordEncoder.encode(profileDTO.getPassword()));
        user.setEmail(profileDTO.getEmail());
        user.setPhone(profileDTO.getPhone());
        country.ifPresent(user::setCountry);

        Profile profile =  profileRepository.save(user);
        saveAuthority(profile);

        return profile;
    }

    @Override
    public void saveAuthority(Profile profile){
        Authorities authority = new Authorities();
        authority.setAuthority("ROLE_USER");
        authority.setProfile(profile);
        authoritiesRepository.save(authority);
    }

    @Override
    public void saveRegisteredUser(Profile user) {
        profileRepository.save(user);
    }

    @Override
    public void createVerificationToken(Profile user, String token) {
        VerificationToken myToken = new VerificationToken(token, user);
        tokenRepository.save(myToken);
    }

    @Override
    public VerificationToken getVerificationToken(String VerificationToken) {
        return tokenRepository.findByToken(VerificationToken);
    }

    @Override
    public Profile verifyProfile(String verificationToken) {
        return tokenRepository.findByToken(verificationToken).getUser();
    }

    private boolean emailExist(String email) {
        Profile user = profileRepository.findByEmail(email);
        return user != null;
    }
}
