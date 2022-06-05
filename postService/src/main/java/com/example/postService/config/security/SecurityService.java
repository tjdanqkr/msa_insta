package com.example.postService.config.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;

@Service
public class SecurityService {
    @Value("${jwt.SECRET_KEY}")
    String SECRET_KEY;
    @Value("${jwt.TOKEN_VALIDATE_TIME}")
    Long TOKEN_VALIDATE_TIME;

    public String createToken(String subject){
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        byte[] secretKeyBytes = DatatypeConverter.parseBase64Binary(SECRET_KEY);
        Key signingKey = new SecretKeySpec(secretKeyBytes,signatureAlgorithm.getJcaName());
        return Jwts.builder()
                .setSubject(subject)
                .signWith(signingKey,signatureAlgorithm)
                .setExpiration(new Date(System.currentTimeMillis()+TOKEN_VALIDATE_TIME))
                .compact();
    }
    public String getSubject(String token){
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(DatatypeConverter.parseBase64Binary(SECRET_KEY))
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }
    public Claims getClaims(String token){
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(DatatypeConverter.parseBase64Binary(SECRET_KEY))
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims;
    }
    public String getToken(){
        ServletRequestAttributes requestAttributes =(ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();

        String token = request.getHeader("Authorization");
        return token.substring("Bearer ".length());
    }
}
