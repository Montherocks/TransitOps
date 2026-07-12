package com.TransitOps.backend.security;

import com.TransitOps.backend.entity.Permissions;
import com.TransitOps.backend.entity.Role;
import com.TransitOps.backend.entity.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Getter
@RequiredArgsConstructor
public class CustomUserDetails implements UserDetails {

    private final User user;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        Set<GrantedAuthority> authorities = new HashSet<>();

        Role role = user.getRole();

        if (role != null) {

            // Add Role
            authorities.add(
                    new SimpleGrantedAuthority("ROLE_" + role.getName())
            );

            // Add Permissions
            if (role.getPermissions() != null) {
                for (Permissions permission : role.getPermissions()) {

                    authorities.add(
                            new SimpleGrantedAuthority(permission.getName())
                    );
                }
            }
        }

        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    /**
     * We use email as the username.
     */
    @Override
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return user.getEnabled();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {

        return Boolean.TRUE.equals(user.getEnabled())
                && Boolean.TRUE.equals(user.getEmailVerified());
    }

    /**
     * Convenience methods
     */

    public Long getUserId() {
        return user.getId();
    }

    public String getFullName() {
        return user.getFullName();
    }

    public Role getRole() {
        return user.getRole();
    }
}