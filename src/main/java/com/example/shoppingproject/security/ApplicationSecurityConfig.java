package com.example.shoppingproject.security;

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
                        .requestMatchers( "/auth/**").permitAll()//permit this url
                        .requestMatchers("/guest").permitAll()


                        .anyRequest().authenticated()

                )

                .httpBasic(); http.csrf().disable();//allow to send post request
        return http.build();
    }





    @Bean
    protected UserDetailsService userDetailsService (){
            UserDetails user = User.builder()
                    .username("anna")
                    .password(passwordEncoder.encode("password"))
                    .roles("student")
                    .build();

            return new InMemoryUserDetailsManager(user);
    }

}
