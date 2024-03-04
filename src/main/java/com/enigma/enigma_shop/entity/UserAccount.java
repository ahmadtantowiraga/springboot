package com.enigma.enigma_shop.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name ="m_user_account")
public class UserAccount implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name="username", nullable = false, unique = true)
    private String userName;

    @Column(name="password", nullable = false)
    private String password;

    @Column(name="is_enable")
    private Boolean isEnable;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Role> role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role.stream().map(role1 -> new SimpleGrantedAuthority(role1.getRole().name())).toList();
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return userName;
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
        return isEnable;
    }
}
