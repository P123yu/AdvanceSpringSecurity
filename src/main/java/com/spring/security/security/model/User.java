package com.spring.security.security.model;


import com.spring.security.security.utility.RolePermissionMapping;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Table(name="users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;

    private String password;



    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    Set<Role> roles = new HashSet<>();


    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private Set<RolePermission.Permission> permissions = new HashSet<>();


//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//
////                return roles.stream()
////                .map(role -> new SimpleGrantedAuthority("ROLE_"+role.name()))
////                .collect(Collectors.toSet());
//
//
//        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
//        roles.forEach(
//                role -> {
//                    Set<SimpleGrantedAuthority> permissions = RolePermissionMapping.getAuthoritiesForRole(role);
//                    authorities.addAll(permissions);
//                    authorities.add(new SimpleGrantedAuthority("ROLE_"+role.name()));
//                }
//        );
//        return authorities;
//    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();

        // Role-based permissions
        roles.forEach(role -> {
            authorities.addAll(RolePermissionMapping.getAuthoritiesForRole(role));
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.name()));
        });

        // Custom permissions
        permissions.forEach(p ->
                authorities.add(new SimpleGrantedAuthority(p.getPermission()))
        );

        return authorities;
    }


}
