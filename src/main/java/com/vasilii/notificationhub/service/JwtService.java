package com.vasilii.notificationhub.service;

import com.vasilii.notificationhub.config.JwtProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class JwtService {

    private final JwtProperties props;
    private final SecretKey key;


    public JwtService(JwtProperties props) {
        this.props = props;
        this.key = Keys.hmacShaKeyFor(props.secret().getBytes());
    }

    private String buildToken(String username, long ttl, List<String> roles) {
        Instant now = Instant.now();
        Instant expiry = now.plusSeconds(ttl);
        return Jwts.builder()
                .subject(username)
                .issuer(props.issuer())
                .issuedAt(Date.from(now))
                .expiration(Date.from(expiry))
                .claim("roles", roles)
                .signWith(key, Jwts.SIG.HS256)
                .compact();
    }

    public String generateAccessToken(String username, List<String> roles) {
        return buildToken(username, props.accessTtlSeconds(), roles);
    }

    public String generateRefreshToken(String username) {
        return buildToken(username, props.refreshTtlSeconds(), new ArrayList<>());
    }

    public Jws<Claims> parse(String token) {
        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token);
    }
}
