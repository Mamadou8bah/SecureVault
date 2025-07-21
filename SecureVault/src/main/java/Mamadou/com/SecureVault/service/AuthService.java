package Mamadou.com.SecureVault.service;


import Mamadou.com.SecureVault.dto.LoginRequest;
import Mamadou.com.SecureVault.dto.RegisterRequest;
import Mamadou.com.SecureVault.dto.UserDto;
import Mamadou.com.SecureVault.model.User;
import Mamadou.com.SecureVault.repository.UserRepo;
import Mamadou.com.SecureVault.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private UserRepo repo;
    @Autowired
    BCryptPasswordEncoder encoder;
    @Autowired
    JwtUtil util;

    public ResponseEntity<?> register(RegisterRequest request){
        User userExist=repo.findByEmail(request.getEmail());
        if (userExist!=null){
            throw new RuntimeException("User Already Exist");
        }
        User user=new User();
        user.setPassword(encoder.encode(request.getPassword()));
        user.setEmail(request.getEmail());
        user.setFullName(request.getFullName());
        repo.save(user);
        return ResponseEntity.ok("Registration Successful");
    }

    public ResponseEntity<?>login(LoginRequest request){
        User user=repo.findByEmail(request.getEmail());
        boolean validPassword=encoder.matches(request.getPassword(), user.getPassword());

        if (user==null ||!validPassword){
            throw new RuntimeException("Invalid Credentials");
        }
        String token=util.generateToken(user.getEmail());
        return ResponseEntity.ok(token);
    }

    public ResponseEntity<?> userProfile(){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();

        if(authentication==null||!authentication.isAuthenticated()){
            throw new RuntimeException("Not Authenticated");
        }
        String email=authentication.getName();

        User user=repo.findByEmail(email);
        return ResponseEntity.ok(new UserDto(user.getFullName(), user.getEmail()));

    }


}
