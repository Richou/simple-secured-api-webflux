package com.heanoria.reminders.securedapi.security.internal;

import com.heanoria.reminders.securedapi.core.data.contexts.UserContext;
import com.heanoria.reminders.securedapi.core.data.exceptions.ExpiredTokenException;
import com.heanoria.reminders.securedapi.security.proxies.UserServiceProxy;
import com.heanoria.reminders.securedapi.security.data.UserClaims;
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
    private static final String TOKEN_REPLACEMENT_PATTERN = "%TOKEN%";

    private static final int TOKEN_EXPIRATION_DURATION = 120;
    private static final Logger logger = LoggerFactory.getLogger(TokenHandler.class);

    private final KeyPair keyPair;
    private final UserServiceProxy userServiceProxy;

    public TokenHandler(KeyPair keyPair, UserServiceProxy userServiceProxy) {
        this.keyPair = keyPair;
        this.userServiceProxy = userServiceProxy;
    }

    public String createTokenForUser(UserClaims user) {
        return Jwts.builder()
                .setClaims(buildClaimsMap(user))
                .setExpiration(calculateExpirationDate())
                .signWith(SignatureAlgorithm.RS512, keyPair.getPrivate())
                .compact();
    }

    public UserContext parseMailFromToken(String token) {
        return userServiceProxy.getUserByMail(getMailFromToken(token));
    }

    public Authentication getAuthentication(String token) {
        Claims claims = getClaimsFromToken(token);

        Collection<? extends GrantedAuthority> authorities =
                Arrays.stream(claims.get(ROLES_CLAIMS_KEY).toString().split(","))
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());

        User principal = new User(claims.getSubject(), "", authorities);

        return new UsernamePasswordAuthenticationToken(principal, token, authorities);
    }

    public UserClaims mapAuthenticationToUserClaims(Authentication auth) {
        return UserClaims.builder().build();
    }

    private String getMailFromToken(String token) {
        return (String) getClaimsFromToken(token).get(EMAIL_CLAIMS_KEY);
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
