package com.wellware.DTO;


import com.wellware.data.entities.Country;
import com.wellware.data.entities.Profile;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class ProfileDTO  {

    @NotNull
    @NotEmpty
    @Pattern(regexp = "[\\w\\s]+")
    private String displayName;

    @NotNull
    @NotEmpty
//    @Pattern(regexp = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?")
    @Email(message = "Please enter a valid email address")
    private String email;

    @NotNull
    @NotEmpty
    @Size(min = 8, max = 30, message = "Password should be from 8 to 30 characters")
    private String password;

    private String phone;

    private Long countryId;

    public ProfileDTO(){}

    public ProfileDTO(Profile profile) {
        this.displayName = profile.getDisplayName();
        this.email = profile.getEmail();
        this.password = profile.getPassword();
        this.phone = profile.getPhone();
        this.countryId = profile.getCountry().getId();
    }
}