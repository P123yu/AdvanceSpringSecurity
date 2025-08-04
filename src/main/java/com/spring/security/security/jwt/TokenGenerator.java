package com.spring.security.security.jwt;

import com.spring.security.security.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
//import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class TokenGenerator {


//    .issuedAt(new Date())
//    .expiration(new Date(System.currentTimeMillis() + 1000*60*10))



    @Value("${jwt.secretKey}")
    private String jwtSecretKey;


    public SecretKey getSecretKey(){
        byte[] secretArray = Decoders.BASE64.decode(jwtSecretKey);
        return Keys.hmacShaKeyFor(secretArray);
    }


//    private SecretKey getSecretKey() {
//        return Keys.hmacShaKeyFor(jwtSecretKey.getBytes(StandardCharsets.UTF_8));
//    }


    // generate token

    public String generateToken(User user){
        return Jwts.builder()
                .subject(user.getUsername())
                .claim("userId", user.getId().toString())
                .issuedAt(Date.from(Instant.now()))
                .expiration(Date.from(Instant.now().plus(10,ChronoUnit.MINUTES)))
                .signWith(getSecretKey())
                .compact();
    }

    // verify token and fetch the username from a JWT Token

    public String getUsernameFromToken(String token) {
        Claims claims =  Jwts.parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
        return claims.getSubject();
    }






}
