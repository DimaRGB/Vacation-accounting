package com.github.dmytr0.vacation.service.auth;

import org.springframework.security.core.Authentication;

public interface IAuthenticationFacade {
    Authentication getAuthentication();
    String getCurrentUserName();
}
