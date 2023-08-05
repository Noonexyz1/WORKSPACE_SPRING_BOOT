package com.empresa.jwt;

import com.empresa.user.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;


@Service
public class JwtService {

    /*Si prefieres utilizar una clave estática en lugar de generar una clave dinámicamente utilizando el método Keys.secretKeyFor(SignatureAlgorithm), debes asegurarte de que la clave estática cumpla con los requisitos de seguridad para el algoritmo HMAC-SHA que estás utilizando.
    La especificación JWT JWA (RFC 7518, Sección 3.2) establece que las claves utilizadas con algoritmos HMAC-SHA deben tener un tamaño >= 256 bits (el tamaño de la clave debe ser mayor o igual al tamaño de salida del hash). Por lo tanto, si estás utilizando el algoritmo HS256, debes utilizar una clave estática que tenga al menos 256 bits (32 bytes) de longitud.
    Aquí hay un ejemplo de una clave estática que cumple con este requisito:
    private static final String SECRET_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAzgZp8j";
    Esta clave tiene una longitud de 44 caracteres, lo que equivale a 32 bytes cuando se decodifica de Base64 a un arreglo de bytes.
    Puedes utilizar esta clave estática para firmar tus tokens JWT en lugar de generar una clave dinámicamente. Sin embargo, debes tener en cuenta que el uso de claves estáticas puede presentar riesgos de seguridad si la clave se ve comprometida. Por lo tanto, es importante proteger adecuadamente la clave y rotarla regularmente para minimizar el riesgo.
    * */
    private static final String SECRET_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAzgZp8j";

    public String getToken(UserDetails user) {
        return getToken(new HashMap<>(), user);
    }

    public String getToken(Map<String, Object> extraClaims, UserDetails user) {
        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(user.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000*60*24))
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Key getKey(){
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }


    public String getUsernameFromToken(String token) {
        return getClaim(token, Claims::getSubject);
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String userName = getUsernameFromToken(token);
        return (userName.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private Claims getAllClaims(String token){
        return Jwts.parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJwt(token)
                .getBody();
    }

    public <T> T getClaim(String token, Function<Claims, T> claimResolver){
        final Claims claims = getAllClaims(token);
        return claimResolver.apply(claims);
    }

    public Date getExpiration(String toke){
        return getClaim(toke, Claims::getExpiration);
    }

    public boolean isTokenExpired(String token){
        return getExpiration(token).before(new Date());
    }

}
