package com.toyshop.StoreToys_API.service;

import com.toyshop.StoreToys_API.util.TokenType;
import org.springframework.security.core.userdetails.UserDetails;

public interface JWTService {

    String generateToken(UserDetails userDetails);

    String generateRefreshToken(UserDetails userDetails);

    String extractUsername(String token, TokenType type);

    boolean isValidToken(String token, TokenType type, UserDetails userDetails);

}
