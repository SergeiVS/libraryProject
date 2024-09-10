package org.libraryaccountingproject.services.securityServices;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.libraryaccountingproject.exeptions.InvalidJwtException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import java.util.Date;

@Service
public class JwtProvider {

    @Value("JIUdiuiJIUSIUDHEFIUHIJOIU8347657657DPOQWKoi09a858")
    private String jwtSecret;

    @Value("60000")
    private Long jwtExpiration;

    public SecretKeySpec getJwtSecret() {
        return new SecretKeySpec(jwtSecret.getBytes(), SignatureAlgorithm.HS256.getJcaName());
    }

    public String getJwtToken(String login) {
        Date timeStampNow = new Date();
        Date expirationDate = new Date(timeStampNow.getTime() + jwtExpiration);

        return Jwts.builder()
                .setSubject(login)
                .setIssuedAt(timeStampNow)
                .setExpiration(expirationDate)
                .signWith(getJwtSecret(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Claims getJwtClaims(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(getJwtSecret())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            throw new InvalidJwtException("Invalid JWT token");
        }
    }

    public boolean validateJwtToken(String token) {

        getJwtClaims(token);
        return true;
    }

    public String getLoginFromJwtToken(String token) {

        return getJwtClaims(token).getSubject();
    }
}
