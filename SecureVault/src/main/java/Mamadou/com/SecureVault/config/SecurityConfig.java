package Mamadou.com.SecureVault.config;

import Mamadou.com.SecureVault.service.MyUserDetailsService;
import Mamadou.com.SecureVault.util.JwtAuthFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig  {
    @Autowired
    JwtAuthFilter filter;

    @Autowired
    MyUserDetailsService userDetailsService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity)throws Exception{
        return httpSecurity
                .csrf(csrf->csrf.disable())
                .authorizeHttpRequests(
                        auth->auth.requestMatchers("/api/auth/register","/api/auth/login").permitAll()
                                .requestMatchers(HttpMethod.GET, "/api/auth/me").authenticated()
                                .anyRequest().authenticated()
                )
                .sessionManagement(ses->ses.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class)
                .userDetailsService(userDetailsService)
                .build();
    }
}
