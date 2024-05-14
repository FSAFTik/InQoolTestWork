package com.example.TennisReservation.Entities;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Entity representing a user.
 * It implements UserDetails interface from Spring Security to provide core user information.
 * It is annotated with @Data, @Entity, @Table, @NoArgsConstructor, @AllArgsConstructor, and @Builder.
 */
@Data
@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User implements UserDetails {

    /**
     * The username of the user. It is the primary key in the database.
     */
    @Id
    @Column(nullable = false)
    private String username;

    /**
     * The password of the user.
     * It is encrypted using BCryptPasswordEncoder when set.
     */
    @Column(nullable = false)
    private String password;

    /**
     * Indicates whether the user is enabled.
     * It is true by default, meaning the user is enabled.
     */
    private boolean enabled = true;

    /**
     * The roles of the user.
     * It is a collection of Role enum.
     */
    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Set<Role> roles;

    /**
     * Sets the password of the user.
     * The password is encrypted using BCryptPasswordEncoder.
     * @param password The plain text password.
     */
    public void setPassword(String password) {
        this.password = new BCryptPasswordEncoder().encode(password);
    }

    /**
     * Returns the authorities granted to the user.
     * It is a collection of SimpleGrantedAuthority, each representing a role of the user.
     * @return A collection of GrantedAuthority.
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.name()))
                .collect(Collectors.toList());
    }

    /**
     * Indicates whether the user's account has expired.
     * It always returns true, meaning the account is not expired.
     * @return A boolean indicating whether the user's account has expired.
     */
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * Indicates whether the user is locked.
     * It always returns true, meaning the user is not locked.
     * @return A boolean indicating whether the user is locked.
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * Indicates whether the user's credentials (password) has expired.
     * It always returns true, meaning the credentials are not expired.
     * @return A boolean indicating whether the user's credentials has expired.
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * Indicates whether the user is enabled.
     * @return A boolean indicating whether the user is enabled.
     */
    @Override
    public boolean isEnabled() {
        return this.enabled;
    }
}