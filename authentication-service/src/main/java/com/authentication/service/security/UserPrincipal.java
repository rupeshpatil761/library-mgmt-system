package com.authentication.service.security;

import com.authentication.service.model.LoginUser;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class UserPrincipal implements UserDetails {
    private String userName;

    @JsonIgnore
    private String userRole;

    @JsonIgnore
    private String password;

    private Collection<? extends GrantedAuthority> authorities;

    public UserPrincipal(String userName, String userRole, String password, Collection<? extends GrantedAuthority> authorities) {
        this.userName = userName;
        this.userRole = userRole;
        this.password = password;
        this.authorities = authorities;
    }

    public static UserPrincipal create(LoginUser user) {
        List<GrantedAuthority> authorities = getAuthorityFromRole(user.getRole());

        return new UserPrincipal(
                user.getUsername(),
                user.getRole(),
                user.getPassword(),
                authorities
        );
    }

    private static List<GrantedAuthority> getAuthorityFromRole(String userRole) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        switch (userRole) {
            case "ADMIN":
                authorities.add(new SimpleGrantedAuthority("ADMIN"));
                break;
            default:
                authorities.add(new SimpleGrantedAuthority("NO_ROLE"));
        }
        return authorities;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserPrincipal that = (UserPrincipal) o;
        return Objects.equals(userName, that.userName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName);
    }
}
