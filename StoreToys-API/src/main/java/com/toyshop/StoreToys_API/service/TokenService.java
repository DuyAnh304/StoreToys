package com.toyshop.StoreToys_API.service;

import com.toyshop.StoreToys_API.model.Token;
import com.toyshop.StoreToys_API.repository.TokenRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public record TokenService(TokenRepository tRep) {

    public int saveToken(Token token) {
        Optional<Token> optional = tRep.findByUsername(token.getUsername());
        if(optional.isPresent()){ // neu ton tai token trong db
            Token existingToken = optional.get();
            existingToken.setAccessToken(token.getAccessToken());
            existingToken.setRefreshToken(token.getRefreshToken());
            tRep.saveAndFlush(existingToken);
            return existingToken.getTokenId();
        } else {
            tRep.save(token);
            return token.getTokenId();
        }
    }
    public void deleteToken(String username) {
        Token token = this.getTokenByUsername(username);
        tRep.delete(token);
    }
    private Token getTokenByUsername(String username) {
        return tRep.findByUsername(username).orElseThrow(); // can bat ngoai le o day
    }
}
