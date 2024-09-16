package org.libraryaccountingproject.services.securityServices;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.libraryaccountingproject.exeptions.InvalidJwtException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Date;

@Service
public class JwtProvider {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.lifetime}")
    private Long expiry;

    public Key getSigningKey() {
        return new SecretKeySpec(secret.getBytes(), SignatureAlgorithm.HS256.getJcaName());
    }

    public String getJwtToken(String login) {
        Date timeStampNow = new Date();
        Date expiryDate = new Date(timeStampNow.getTime() + expiry);

        return Jwts.builder()
                .setSubject(login)
                .setIssuedAt(timeStampNow)
                .setExpiration(expiryDate)
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean validateJwtToken(String token) {

        getJwtClaims(token);
        return true;
    }

    private Claims getJwtClaims(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (JwtException e) {
            throw new InvalidJwtException("Invalid JWT token");
        }
    }

    public String getLoginFromJwtToken(String token) {
        return getJwtClaims(token).getSubject();
    }
}
