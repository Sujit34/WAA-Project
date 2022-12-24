package edu.miu.WAA_Project.service.impl;

import edu.miu.WAA_Project.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class ApplicationUserDetail implements UserDetails {
    private final String username;
    private final String password;
    private final String role;
    private final int id;

    public ApplicationUserDetail(User user) {
        username = user.getEmail();
        password = user.getPassword();
        role = user.getUserType();
        id = user.getId();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(() -> role);

        return authorities;
    }

    public int getId() {
        return id;
    }

    public boolean isStudent() {
        return role.equals("STUDENT");
    }

    public boolean isAdmin() {
        return role.equals("ADMIN");
    }

    public boolean isFaculty() {
        return role.equals("FACULTY");
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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
}
