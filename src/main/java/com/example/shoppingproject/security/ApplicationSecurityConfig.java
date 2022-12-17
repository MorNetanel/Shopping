package com.example.shoppingproject.security;

import com.example.shoppingproject.enums.ClientType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig  {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public ApplicationSecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((authz) -> authz
                        .requestMatchers( "/auth/**").permitAll()//permit this url for everyone
                        .requestMatchers("/guest").permitAll()//permit this url for everyone
 //************************************************************************************
                        //permit this url by role:
                        .requestMatchers("/company/**").hasRole(String.valueOf(ClientType.COMPANY))
                        .requestMatchers("/customer/**").hasRole(String.valueOf(ClientType.CUSTOMER))
//*************************************************************************************

                        .anyRequest().authenticated()

                )

                .formLogin()
                .loginPage("/auth")
        ;
        http.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());//able to access token in j.s.

//        .disable();//allow to send post delete put request
        return http.build();
    }





    @Bean
    protected UserDetailsService userDetailsService (){
            UserDetails userAnna = User.builder()
                    .username("anna")
                    .password(passwordEncoder.encode("password"))
                    .roles(String.valueOf(ClientType.COMPANY))
                    .build();


        UserDetails userLinda = User.builder()
                .username("linda")
                .password(passwordEncoder.encode("password123"))
                .roles(String.valueOf(ClientType.CUSTOMER))
                .build();

            return new InMemoryUserDetailsManager(userAnna, userLinda);
    }

}
