package com.heanoria.reminders.securedapi.security.internal;

import com.heanoria.reminders.securedapi.core.data.exceptions.ExpiredTokenException;
import com.heanoria.reminders.securedapi.security.data.UserClaims;
import com.heanoria.reminders.securedapi.security.data.UserProxy;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.security.KeyPair;
import java.util.*;
import java.util.stream.Collectors;

public class TokenHandler {
    private static final String ROLES_CLAIMS_KEY = "roles";
    private static final String EMAIL_CLAIMS_KEY = "email";
    private static final String USER_ID_CLAIMS_KEY = "uid";
    private static final String USERNAME_CLAIMS_KEY = "username";

    private static final int TOKEN_EXPIRATION_DURATION = 500;
    private static final Logger logger = LoggerFactory.getLogger(TokenHandler.class);

    private final KeyPair keyPair;
    public TokenHandler(KeyPair keyPair) {
        this.keyPair = keyPair;
    }

    public String createTokenForUser(UserClaims user) {
        return Jwts.builder()
                .setClaims(buildClaimsMap(user))
                .setSubject(user.getEmail())
                .setExpiration(calculateExpirationDate())
                .signWith(SignatureAlgorithm.RS512, keyPair.getPrivate())
                .compact();
    }

    public String getEmailFromToken(String token) {
        return getClaimsFromToken(token).get(EMAIL_CLAIMS_KEY, String.class);
    }

    public UUID getIdFromToken(String token) {
        return UUID.fromString(getClaimsFromToken(token).get(USER_ID_CLAIMS_KEY, String.class));
    }

    public UserClaims mapAuthenticationToUserClaims(Authentication auth) {
        if (auth instanceof UserAuthentication) {
            UserProxy details = ((UserAuthentication) auth).getDetails();
            return UserClaims.builder().id(details.getId()).email(details.getEmail()).username(details.getUsername()).roles(details.getAuthorities() != null ? details.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()): Collections.emptyList()).build();
        }
        return UserClaims.builder().build();
    }

    private Claims getClaimsFromToken(String token) {
        try {
            return Jwts.parser().setSigningKey(keyPair.getPublic())
                    .parseClaimsJws(token).getBody();
        } catch (ExpiredJwtException exception) {
            throw new ExpiredTokenException(exception.getMessage());
        }
    }

    private Date calculateExpirationDate() {
        return DateUtils.addSeconds(new Date(), TOKEN_EXPIRATION_DURATION);
    }

    private Map<String, Object> buildClaimsMap(UserClaims userClaims) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(ROLES_CLAIMS_KEY, userClaims.getRoles());
        claims.put(USER_ID_CLAIMS_KEY, userClaims.getId());
        claims.put(EMAIL_CLAIMS_KEY, userClaims.getEmail());
        claims.put(USERNAME_CLAIMS_KEY, userClaims .getUsername());
        return claims;
    }

}
