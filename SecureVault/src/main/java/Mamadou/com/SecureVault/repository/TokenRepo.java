package Mamadou.com.SecureVault.repository;

import Mamadou.com.SecureVault.model.Token;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TokenRepo {
    Optional<Token> findByToken(String token);
}
