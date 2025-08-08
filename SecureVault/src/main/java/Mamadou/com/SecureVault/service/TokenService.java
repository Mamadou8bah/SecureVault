package Mamadou.com.SecureVault.service;


import Mamadou.com.SecureVault.model.Token;
import Mamadou.com.SecureVault.model.User;
import Mamadou.com.SecureVault.repository.TokenRepo;
import Mamadou.com.SecureVault.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TokenService {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private TokenRepo tokenRepo;

    public Token findByToken(String token) {
        return tokenRepo.findByToken(token);
    }

    public Token save(User user) {
        Token token = createToken(user);
        return tokenRepo.save(token);
    }

    private Token createToken(User user) {
        Token token = new Token();
        token.setUser(user);
        token.setToken(jwtUtil.generateToken(user.getEmail()));
        token.setExpired(false);
        token.setRevoked(false);
        return token;
    }

    private boolean isTokenValid(String token) {
        Token token1=tokenRepo.findByToken(token);
        if(token1==null) {
            return false;
        }
        return !token1.isExpired()||!token1.isRevoked();
    }

}
