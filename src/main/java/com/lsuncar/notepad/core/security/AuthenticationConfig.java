package com.lsuncar.notepad.core.security;

import com.lsuncar.notepad.db.entity.User;
import com.lsuncar.notepad.db.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static java.util.Objects.isNull;

@Configuration
@RequiredArgsConstructor
public class AuthenticationConfig {

    @Autowired
    private UserRepository userService;

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            try {
                User user = userService.findUserByUsername(username);
                if (isNull(user))
                    throw new UsernameNotFoundException("Username not found");
                return user; //TODO bu iyi değil gibi geldi. Converterla uğraşmamak için böyle yaptım şimdilik
            } catch (Exception e) {
                throw e;
            }
        };
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }


}
