package com.toyshop.StoreToys_API.service.impl;

import com.toyshop.StoreToys_API.DTOs.request.LoginRequest;
import com.toyshop.StoreToys_API.DTOs.respone.TokenRespone;
import com.toyshop.StoreToys_API.model.Account;
import com.toyshop.StoreToys_API.model.Token;
import com.toyshop.StoreToys_API.repository.AccountRepository;
import com.toyshop.StoreToys_API.service.AuthenticationService;
import com.toyshop.StoreToys_API.service.JWTService;
import com.toyshop.StoreToys_API.service.TokenService;
import com.toyshop.StoreToys_API.util.TokenType;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final AccountRepository aRep;
    private final AuthenticationManager aMan;
    private final JWTService jSer;
    private final TokenService tSer;

    @Override
    public TokenRespone authenticate(LoginRequest loginRequest) {
        aMan.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        var account = aRep.findByUsername(loginRequest.getUsername()).orElseThrow();
        String accessToken = jSer.generateToken(account);
        String refreshToken = jSer.generateRefreshToken(account);

        //save token to db
        Token token = Token.builder().accessToken(accessToken)
                .username(account.getUsername())
                .refreshToken(refreshToken)
                .build();
        tSer.saveToken(token);
        return TokenRespone.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .userId(account.getAccountId())
                .role(account.getRole().getRoleName())
                .build();
    }

    @Override
    public TokenRespone refresh(HttpServletRequest request) {
        String refreshToken = request.getHeader("r-token");
        if (!StringUtils.hasText(refreshToken)) {
            return null; // can bat ngoai le
        }
        // extract user from token
        final String username = jSer.extractUsername(refreshToken, TokenType.REFRESH_TOKEN);
        //check it in db
        Optional<Account> account = aRep.findByUsername(username);
        if (!jSer.isValidToken(refreshToken, TokenType.REFRESH_TOKEN,account.get())) {
            return null;
        }
        String accessToken = jSer.generateToken(account.get());

        //save token to db
        Token token = Token.builder().accessToken(accessToken)
                .username(account.get().getUsername())
                .refreshToken(refreshToken)
                .build();
        tSer.saveToken(token);
        return TokenRespone.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .role(account.get().getRole().getRoleName())
                .userId(account.get().getAccountId())
                .build();
    }

    @Override
    public void logout(HttpServletRequest request) {
        String accessToken = request.getHeader("a-token");
        if (!StringUtils.hasText(accessToken)) {
            return; // can bat ngoai le
        }
        final String username = jSer.extractUsername(accessToken, TokenType.ACCESS_TOKEN);
        Optional<Account> account = aRep.findByUsername(username);
        tSer.deleteToken(account.get().getUsername());
    }

}
