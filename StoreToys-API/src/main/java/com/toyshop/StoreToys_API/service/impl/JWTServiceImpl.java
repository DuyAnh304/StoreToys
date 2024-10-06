package com.toyshop.StoreToys_API.service.impl;

import com.toyshop.StoreToys_API.service.JWTService;
import com.toyshop.StoreToys_API.util.TokenType;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import static com.toyshop.StoreToys_API.util.TokenType.ACCESS_TOKEN;
import static com.toyshop.StoreToys_API.util.TokenType.REFRESH_TOKEN;

@Service
public class JWTServiceImpl implements JWTService {

    @Value("${jwt.lifespan.aToken}")
    private String lifespanAToken;

    @Value("${jwt.lifespan.rToken}")
    private String lifespanRToken;

    @Value("${jwt.secretKeyEncoded}")
    private String secretKeyEncoded;

    @Value("${jwt.refreshKeyEncoded}")
    private String refreshKeyEncoded;

    @Override
    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    @Override
    public String generateRefreshToken(UserDetails userDetails) {
        return generateRefreshToken(new HashMap<>(), userDetails);
    }

    @Override
    public String extractUsername(String token, TokenType type) {
        return this.extractClaims(token, type, Claims::getSubject);
    }

    @Override
    public boolean isValidToken(String token, TokenType type, UserDetails userDetails) {
        final String username = this.extractUsername(token, type);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token, type));
    }

    private String generateToken(Map<String, Object> claims, UserDetails userDetails) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setHeaderParam("typ", "JWT")
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(calculateExpiration(lifespanAToken))
                .signWith(decodedSecretKey(ACCESS_TOKEN), SignatureAlgorithm.HS256)
                .compact();
    }

    private String generateRefreshToken(Map<String, Object> claims, UserDetails userDetails) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setHeaderParam("typ", "JWT")
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(calculateExpiration(lifespanRToken))
                .signWith(decodedSecretKey(REFRESH_TOKEN), SignatureAlgorithm.HS256)
                .compact();
    }

    private Date calculateExpiration(String time) {
        String cleanTime = time.replace(" ", "");
        long result = Arrays.stream(cleanTime.split("\\*"))
                .mapToLong(Long::parseLong)
                .reduce(1, (a, b) -> a * b);
        return new Date(System.currentTimeMillis() + result);
    }

    private SecretKey decodedSecretKey(TokenType type) {
        byte[] keyBytes;
        if(ACCESS_TOKEN.equals(type)) {
            keyBytes = Decoders.BASE64.decode(secretKeyEncoded);
        } else {
            keyBytes = Decoders.BASE64.decode(refreshKeyEncoded);
        }
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private <T> T extractClaims(String token, TokenType type, Function<Claims, T> claimResolver) {
        final Claims claims = this.extractAllClaims(token, type);
        return claimResolver.apply(claims);
    }

    private Claims extractAllClaims(String token, TokenType type) {
        try {
            return Jwts.parser().verifyWith(decodedSecretKey(type))
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
        } catch (SignatureException e) {
            // Xử lý ngoại lệ SignatureException ở đây (can bat ngoai le)
            System.out.println("JWT signature không khớp: " + e.getMessage());
            throw new IllegalArgumentException("Token không hợp lệ", e);
        }
    }

    private boolean isTokenExpired(String token, TokenType type) {
        return extractExpiration(token, type).before(new Date());
    }

    private Date extractExpiration(String token, TokenType type) {
        return extractClaims(token, type, Claims::getExpiration);
    }
}
