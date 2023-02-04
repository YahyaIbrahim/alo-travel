package com.wellware.data.entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;


import java.util.HashSet;
import java.util.Set;


@Data
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

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "profile")
    private Set<Authorities> authorities = new HashSet<>();

    @NotNull
    @NotEmpty
    @Column(name = "email_address")
    @Email(message = "(Error: Please enter a valid email address)")
    private String email;

    @NotNull
    @NotEmpty
    private String password;

    private String phone;

    private String country;

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

}