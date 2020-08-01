package com.github.dmytr0.vacation.config;


import com.github.dmytr0.vacation.domain.User;
import com.github.dmytr0.vacation.repository.UsersRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.boot.autoconfigure.security.oauth2.resource.AuthoritiesExtractor;
import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.AuthorityUtils;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Log4j2
@Configuration
@EnableOAuth2Sso
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String[] ALLOWED_PATH = {
            "/",
            "/policy",
            "/google**",
            "/login**",
            "/logout**",
            "/manifest**",
            "/favicon.ico",
            "/built/**",
            "/img/**",
    };
    @Value("#{'${allowed.domains}'.trim().isEmpty() ? T(java.util.Collections).emptyList() : '${allowed.domains}'.trim().split('\\s*,\\s*')}")
    private List<String> allowedDomains;

    @Value("#{'${global.admin.email}'.trim()}")
    private String globalAdminEmail;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //@formatter:off
        http
                .authorizeRequests()
        .antMatchers(ALLOWED_PATH)
                .permitAll()
        .anyRequest()
                .hasAuthority("USER")
        .and()
                .logout().logoutSuccessUrl("/?q=logout").permitAll()
        .and()
                .exceptionHandling().accessDeniedPage("/unauthorized")
        .and()
                .csrf().disable();
        //@formatter:on
    }


    @Bean
    public PrincipalExtractor clientExtractor(UsersRepository repository) {
        return map -> {
            User googleUser = User.fromMap(map);
            User user = repository.findById(googleUser.get_id()).orElseGet(() -> {
                googleUser.setAuthorities(getSpecialAuthorities(googleUser.getEmail(), Collections.emptySet()));
                return googleUser;
            });
            user.setLastVisit(LocalDateTime.now());
            return repository.save(user);
        };
    }

    @Bean
    public AuthoritiesExtractor authoritiesExtractor(UsersRepository repository) {
        return map -> {
            User user = User.fromMap(map);
            User usr = repository.findById(user.get_id()).orElseThrow(() -> {
                log.info("Unknown user " + user);
                return new BadCredentialsException("Unknown user");
            });

            String[] authorities = usr.getAuthorities().toArray(new String[0]);
            return AuthorityUtils.createAuthorityList(authorities);
        };
    }


    private Set<String> getSpecialAuthorities(String email, Set<String> storedAuthorities) {
        Set<String> authorities = new HashSet<>();
        if (globalAdminEmail.equals(email)) {
            authorities.add("SUPER_ADMIN");
        }

        if (allowedDomains.isEmpty() || allowedDomains.contains(email.split("@")[1])) {
            authorities.add("USER");
            authorities.addAll(storedAuthorities);
        }

        if (authorities.isEmpty()) {
            String msg = "User " + email + " has no authorities";
            log.info(msg);
            throw new BadCredentialsException(msg);
        }
        return authorities;
    }
}
