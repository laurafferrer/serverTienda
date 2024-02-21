/*
   JWTHelper class for handling JSON Web Tokens (JWT).
*/
package net.ausiasmarch.serverTienda.helper;

import java.time.Instant;

import java.util.Date;
import java.util.UUID;

import javax.crypto.SecretKey;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class JWTHelper {
    
    // Secret and issuer for JWT generation and validation
    private static final String SECRET = "tienda_2024_@S3cret_P@ssw0rd";
    private static final String ISSUER = "TIENDA 2024 - Desde la Pluma a la Pantalla";

    private static SecretKey secretKey() {
        return Keys.hmacShaKeyFor((SECRET + ISSUER + SECRET).getBytes());
    }

    public static String generateJWT(String username) {

        Date currentTime = Date.from(Instant.now());
        Date expirationTime = Date.from(Instant.now().plusSeconds(3600));

        return Jwts.builder()
                .setId(UUID.randomUUID().toString())
                .setIssuer(ISSUER)
                .setIssuedAt(currentTime)
                .setExpiration(expirationTime)
                .claim("name", username)
                .signWith(secretKey())
                .compact();
    }

    public static String validateJWT(String strJWT) {
        try {
            Jws<Claims> headerClaimsJwt = Jwts.parserBuilder()
                    .setSigningKey(secretKey())
                    .build()
                    .parseClaimsJws(strJWT);
            
            Claims claims = headerClaimsJwt.getBody();

            if (claims.getExpiration().before(new Date())) {
                return null;
            }

            if (!claims.getIssuer().equals(ISSUER)) {
                return null;
            }

            return claims.get("name", String.class);
        } catch (Exception e) {
            return null;
        }
    }
    
}
