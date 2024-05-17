package com.zosh.config.AppConfig;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.List;

public class JwtTokenValidator extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull FilterChain filterChain) throws ServletException, IOException {
        String jwt=request.getHeader(JwtConstant.JWT_HEADER);


        //Bearer jwt

        if(jwt!=null && jwt.startsWith("Bearer ")){
            jwt=jwt.substring(7);
            try {
                SecretKey key = Keys.hmacShaKeyFor(JwtConstant.SECREATE_KEY.getBytes(StandardCharsets.UTF_8));


                //SecretKey key = Keys.hmacShaKeyFor(JwtConstant.SECREATE_KEY.getBytes());
              // Claims claims = Jwts.parser().build().parseSignedClaims(jwt).getPayload();
               
               Claims claims = Jwts.parserBuilder()
                                    .setSigningKey(key)
                                    .build()
                                    .parseClaimsJws(jwt)
                                    .getBody();
                // Add expiration check
                if (claims.getExpiration().before(new Date())) {
                    throw new BadCredentialsException("Token expired");
                }

            //     String email=String.valueOf(claims.get("email"));
            //     String authorities = String.valueOf(claims.get("Authorities"));
            //    //String authorities=String.valueOf(claims.get("Authorities"));
            //    List<GrantedAuthority> auths= AuthorityUtils.commaSeparatedStringToAuthorityList(authorities);

            //    Authentication authentication=new UsernamePasswordAuthenticationToken(email,null,auths);
            //    SecurityContextHolder.getContext().setAuthentication(authentication);

               String email = claims.get("email", String.class);
                String authorities = claims.get("Authorities", String.class);
                List<GrantedAuthority> auths = AuthorityUtils.commaSeparatedStringToAuthorityList(authorities);

                Authentication authentication = new UsernamePasswordAuthenticationToken(email, null, auths);
                SecurityContextHolder.getContext().setAuthentication(authentication);


            }
            catch(Exception e) {
                throw new BadCredentialsException("Invalid token...");
            }
        }

        filterChain.doFilter(request, response);

    }

}


