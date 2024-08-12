package com.sergio.ecom.utils;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {


    public static final String ACCESS_TOKEN_SECRET = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IlNlcmdpbyBQZXJkb21vIiwiaWF0IjoxNTE2MjM5MDIyfQMNhL6bff8dq1WNHiqTmyHFbTRGhR3hzspfmlWs9Bw";

    // Método para agregar el token.
    public String generateToken(String userName){
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userName);
    }

    private String createToken(Map<String, Object> claims, String userName){
        return Jwts.builder()
                    .setClaims(claims)
                    .setSubject(userName)
                    .setIssuedAt(new Date(System.currentTimeMillis()))
                    .setExpiration(new Date(System.currentTimeMillis() + 10000 * 60 * 30)) // Fecha de caducidad. -  30 minutos apartir de ahora.
                    // Firmando el tocken
                    .signWith(getSignKey(), SignatureAlgorithm.HS256).compact(); // getSignKey es un método para obtener la clave de inicio de sesión.
    }

    // Método para obtener la llave de inicio de sesión:
    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(ACCESS_TOKEN_SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    // Método para extraer el nombre del usuario de nuestro TOKEN.
    public String extractUsername(String token){
        return extractCalim(token, Claims::getSubject);
    }

    public <T> T extractCalim(String token, Function<Claims, T> claimsResolver){
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token){
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // Método para comprobar la caducidad de nuestro JWT.

    private Boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }

    //Extrayendo el vencimiento del token.

    public Date extractExpiration(String token){
        return extractCalim(token, Claims::getExpiration);
    }

    // Validando nuestro token
    public Boolean validateToken (String token, UserDetails userDetails){
        final String username =  extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

}
