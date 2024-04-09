package com.jshi.laughtale.security.jwt;

import com.jshi.laughtale.security.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.time.Duration;
import java.util.Date;

@Slf4j
@Component
public class JwtProcessor implements BeanPostProcessor {

    private final String ROLE_CLAIM = "role";
    @Value("${jwt.secret}")
    private String secret;

    public String getEmail(String token) {
        SecretKey secretKey = Keys.hmacShaKeyFor(secret.getBytes());
        return Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token).getBody().getSubject();
    }

    public Role getRole(String token) {
        SecretKey secretKey = Keys.hmacShaKeyFor(secret.getBytes());
        return Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token).getBody()
                .get(ROLE_CLAIM, Role.class);
    }

    public boolean isValid(String token) {
        SecretKey secretKey = Keys.hmacShaKeyFor(secret.getBytes());
        Date expiration = Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token).getBody()
                .getExpiration();
        Date now = new Date();

        return now.before(expiration);
    }

    public String createJwtToken(String email, String role) {
        SecretKey secretKey = Keys.hmacShaKeyFor(secret.getBytes());
        Date now = new Date();
        Claims claims = Jwts.claims()
                .setSubject(email)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + Duration.ofDays(4).toMillis()));
//                .setExpiration(new Date(now.getTime() + Duration.ofHours(1).toMillis()));
        return Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .setClaims(claims)
                .claim(ROLE_CLAIM, role)
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();
    }

}
