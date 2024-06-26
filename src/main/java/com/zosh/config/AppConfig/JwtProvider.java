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

// public class JwtProvider {

//     static SecretKey key = Keys.hmacShaKeyFor(JwtConstant.SECREATE_KEY.getBytes());


//     public static String generateToken(Authentication auth) {

//         String jwt = Jwts.builder().issuedAt(new Date())
//                 .expiration(new Date(new Date().getTime()+86400000))
//                 .claim("email", auth.getName())
//                 .signWith(key)
//                 .compact();

//         return jwt;
//     }

//     public static String getEmailFromToken(String jwt) {
//         //Bearer token

//         jwt=jwt.substring(7);

//             //Claims claims = Jwts.parser().setgningKey(key).build().parseClaimsJws(jwt).getBody();
//             @SuppressWarnings("deprecation")
//             Claims claims = Jwts.parser().build().parseSignedClaims(jwt).getBody();

//         return String.valueOf(claims.get("email"));

//     }

    
// }
public class JwtProvider {

    static SecretKey key = Keys.hmacShaKeyFor(JwtConstant.SECREATE_KEY.getBytes());

    public static String generateToken(Authentication auth) {
        return Jwts.builder()
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + 86400000))
                .claim("email", auth.getName())
                .signWith(key)
                .compact();
    }

    public static String getEmailFromToken(String jwt) {
        jwt = jwt.substring(7); // "Bearer " 제거
        Claims claims = Jwts.parser()
                            .setSigningKey(key)
                            .parseClaimsJws(jwt)
                            .getBody();
        return claims.get("email", String.class);
    }
}