package com.example.reddit.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class TokenUtils {
    @Value("myXAuthSecret")
    private String secret;

    @Value("18000")
    private Long expiration;

    public String getUsernameFromToken(String token) {
        String username;
        try {
            Claims claims = this.getClaimsFromToken(token);
            username = claims.getSubject();
        } catch (Exception e) {
            username = null;
        }
        return username;
    }

    private Claims getClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }

    public Date getExpirationDateFromToken(String token) {
        Date expiration;
        try {
            final Claims claims = this.getClaimsFromToken(token);
            expiration = claims.getExpiration();
        } catch (Exception e) {
            expiration = null;
        }
        return expiration;
    }

    private boolean daLiJeTokenIstekao(String token) {
        final Date expiration = this.getExpirationDateFromToken(token);
        return expiration.before(new Date(System.currentTimeMillis()));
    }

    public boolean validirajToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return username.equals(userDetails.getUsername()) && !daLiJeTokenIstekao(token);
    }

    public String generisiToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<String, Object>();
        claims.put("sub", userDetails.getUsername());
        claims.put("created", new Date(System.currentTimeMillis()));
        return Jwts.builder().setClaims(claims).setExpiration(new Date(System.currentTimeMillis() * 2))
                .signWith(SignatureAlgorithm.HS512, secret).compact();
    }
}
