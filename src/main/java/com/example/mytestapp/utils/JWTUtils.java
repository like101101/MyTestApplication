package com.example.mytestapp.utils;

import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JWTUtils {

    private static final String jwtToken = "jdniuahsidwi@!@!sad";

    public static String createToken(String email){
        Map<String,Object> claims = new HashMap<>();
        claims.put("email", email);
        JwtBuilder jwtBuilder = Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, jwtToken)
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000*60*60*24*365));
        String token = jwtBuilder.compact();
        return token;
    }

    public static Map<String,Object> checkToken(String token){
        try {
            Jwt parse = Jwts.parser().setSigningKey(jwtToken).parse(token);
            return (Map<String,Object>)parse.getBody();
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
