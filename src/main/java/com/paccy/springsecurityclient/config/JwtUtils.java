package com.paccy.springsecurityclient.config;

import io.jsonwebtoken.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

@Component
public class JwtUtils {
    private String signingKey="ejkdsnxbfdcxjwehjasgzxiuerdojakznewdshu6zxh67hdsb9hf";

    public String extractUsername(String token){
        return extractClaim(token, Claims::getSubject);
    }

    private <T> T extractClaim(String token, Function<Claims,T> claimsTResolver) {
        final Claims claims =extractAllClaims(token);
        return claimsTResolver.apply(claims);
    }

    private Date extractExpirationDate(String token){
        return extractClaim(token,Claims::getExpiration);
    }
    public boolean hasClaim(String token,String claimeName){
        final Claims claims=extractAllClaims(token);
        return claims.get(claimeName) !=null;
    }

    private Claims extractAllClaims(String token) {
        return (Claims) Jwts.parser().setSigningKey(signingKey).build();
    }
    public String generateToken(UserDetails userDetails, Map<String,Object> claims){
        return createToken(claims,userDetails);
    }
    public String generateToken(UserDetails userDetails){
        Map<String,Object> claims= new HashMap<>();
        return createToken(claims,userDetails);
    }

    private String createToken(Map<String, Object> claims, UserDetails userDetails) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .claim("authorities",userDetails.getAuthorities())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+ TimeUnit.HOURS.toMillis(24)))
                .signWith(SignatureAlgorithm.HS256,signingKey).compact();
    }
    public Boolean isTokenValid(String token,UserDetails userDetails){
        final String username=extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return extractExpirationDate(token).before(new Date());
    }


}
