package com.wellware.data.entities;

import jakarta.persistence.*;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;



@Data
@Entity
@Table(name = "authorities")
public class Authorities implements GrantedAuthority {

    @Id
    @GeneratedValue(
        strategy= GenerationType.AUTO,
        generator="native"
    )
    @GenericGenerator(
        name = "native",
        strategy = "native"
    )
    private Long id;
    private String authority;

    @ManyToOne
    private Profile profile;

    @Override
    public String getAuthority() {
        return authority;
    }
}