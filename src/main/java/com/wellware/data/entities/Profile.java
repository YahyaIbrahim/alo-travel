package com.wellware.data.entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.authority.SimpleGrantedAuthority;


import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@Entity
@Table(name = "profile")
public class Profile extends AuditModel {

    @NotNull
    private boolean enabled;

    @NotNull
    @NotEmpty
    @Column(name = "display_name")
    @Pattern(regexp = "[\\w\\s]+")
    private String displayName;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "USER_ROLES",
            joinColumns = {
                    @JoinColumn(name = "USER_ID")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "ROLE_ID") })
    private Set<Authorities> roles;

    @NotNull
    @NotEmpty
    @Column(name = "email_address")
    @Email(message = "(Error: Please enter a valid email address)")
    private String email;

    @NotNull
    @NotEmpty
    private String password;

    private String phone;

    @ManyToOne
    private Country country;

    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(
            name = "user_trips",
            joinColumns = @JoinColumn(name = "profile_id"),
            inverseJoinColumns = @JoinColumn(name = "trip_id")
    )
    private Set<Trip> trips;

    public Profile() {
        this.enabled = false;
    }

    public Profile(Profile profile){
        this.email = profile.email;
        this.password = profile.password;
        this.enabled = false;
    }

    @Override
    public String toString() {
        return "Profile{" +
                "enabled=" + enabled +
                ", displayName='" + displayName + '\'' +
                ", roles=" + roles +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", country=" + country +
                ", trips=" + trips +
                '}';
    }

}