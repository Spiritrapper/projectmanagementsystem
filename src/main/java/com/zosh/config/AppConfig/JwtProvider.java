package com.zosh.config.AppConfig;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Collection;
import java.util.Date;

public class JwtProvider {

    static SecretKey key = Keys.hmacShaKeyFor(JwtConstant.SECREATE_KEY.getBytes());


    public static String generateToken(Authentication auth) {

        String jwt = Jwts.builder().issuedAt(new Date())
                .expiration(new Date(new Date().getTime()+86400000))
                .claim("email", auth.getName())
                .signWith(key)
                .compact();

        return jwt;
    }

    public static String getEmailFromToken(String jwt) {
            Claims claims = Jwts.parser().build().parseSignedClaims(jwt).getBody();

        return String.valueOf(claims.get("email"));

    }
}
