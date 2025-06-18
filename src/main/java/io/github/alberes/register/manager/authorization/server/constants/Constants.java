package io.github.alberes.register.manager.authorization.server.constants;

import java.util.Set;

public interface Constants {

    public static final String DATE_TIME_FORMATTER_PATTERN = "dd/MM/yyyy HH:mm:ss";
    public static final String ADMIN = "ADMIN";
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String EMAIL = "email";
    public static final String PROFILES = "profiles";
    public static final String REGISTRATION_DATE = "registrationDate";
    public static final String OBJECT_NOT_FOUND = "Object not found!";
    public static final String SLASH_LOGIN = "/login";
    public static final String RSA = "RSA";
    public static final Set<String> SWAGGERS = Set.of("/v2/api-docs/**", "/v3/api-docs/**", "/swagger-resources/**",
            "/swagger-ui.html", "/swagger-ui/**", "/webjars/**", "/actuator/**");
}
