package com.espro.cognito.configs;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;

import java.util.Collection;
import java.util.List;

public class CustomAuthoritiesMapper {

    private final JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter;

    public CustomAuthoritiesMapper() {
        this.jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
    }

    public Collection<GrantedAuthority> convert(Jwt jwt) {
        Collection<GrantedAuthority> authorities = jwtGrantedAuthoritiesConverter.convert(jwt);

        var groups = (List<Object>)jwt.getClaims().get("cognito:groups");
        if (groups != null) {
            authorities.addAll(groups.stream()
                    .map(group -> new SimpleGrantedAuthority("CGROUP_" + group))
                    .toList());
        }
        return authorities;
    }
}