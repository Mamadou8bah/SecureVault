package Mamadou.com.SecureVault.service;


import Mamadou.com.SecureVault.model.Token;
import Mamadou.com.SecureVault.repository.TokenRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TokenService {

    @Autowired
    private TokenRepo tokenRepo;

    public Optional<Token> findByToken(String token) {
        return tokenRepo.findByToken(token);
    }
}
