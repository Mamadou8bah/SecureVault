package Mamadou.com.SecureVault.repository;

import Mamadou.com.SecureVault.model.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TokenRepo extends JpaRepository<Token, Long> {
    Token findByToken(String token);
}
