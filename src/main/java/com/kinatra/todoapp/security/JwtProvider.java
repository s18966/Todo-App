package com.kinatra.todoapp.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Component
public class JwtProvider {

    @Value("my-secret")
    private String jwtSecret;

    //jwt token generate
    public String genToken(String login){
        Date date = Date.from(LocalDate.now().plusDays(10).atStartOfDay(ZoneId.systemDefault()).toInstant());
        return Jwts.builder()
                .setSubject(login)
                .setExpiration(date)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }
    //check if token valid
    public boolean isTokenValid(String token){
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        }catch (Exception e){
            System.out.println("Invalid token");
        }
        return false;
    }
    public String extractLoginFromToken(String token){
        //getting login from token
        Claims claims = Jwts.parser().
                setSigningKey(jwtSecret).
                parseClaimsJws(token).
                getBody();
        return claims.getSubject();
    }
}
