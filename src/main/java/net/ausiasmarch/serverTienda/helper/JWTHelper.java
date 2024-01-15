package net.ausiasmarch.serverTienda.helper;

import java.time.Duration;
import java.time.Instant;

import java.util.Date;
import java.util.UUID;

import javax.crypto.SecretKey;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class JWTHelper {
    
    // PARA CUANDO IMPLEMENTE EL TOKEN. NO BORRAR
    private static final String SECRET = "tienda_2024_@S3cret_P@ssw0rd";
    private static final String ISSUER = "TIENDA 2024 - Desde la Pluma a la Pantalla";

    private static SecretKey secretKey() {
        return Keys.hmacShaKeyFor((SECRET + ISSUER + SECRET).getBytes());
    }

    public static String generateJWT(String username) {

        Date currentTime = Date.from(Instant.now());
        Date expiryTime = Date.from(Instant.now().plus(Duration.ofSeconds(1500)));

        return Jwts.builder().setId(UUID.randomUUID().toString()).setIssuer(ISSUER).setIssuedAt(currentTime).setExpiration(expiryTime).claim("name", username).signWith(secretKey()).compact();
    }

    public static String validateJWT(String strJWT) {
        try {
            Jws<Claims> headerClaimsJWT = Jwts.parserBuilder().setSigningKey(secretKey()).build().parseClaimsJws(strJWT);

            Claims claims = headerClaimsJWT.getBody();

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
