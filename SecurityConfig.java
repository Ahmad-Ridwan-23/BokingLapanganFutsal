package com.FUTSAL.BOOKING.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
            .requestMatchers("/register", "/login", "/css/**", "/js/**").permitAll() // ⬅️ IZINKAN
            .requestMatchers("/lapangan/**").hasAuthority("ROLE_ADMIN")
            .requestMatchers("/booking/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_USER")
            .anyRequest().authenticated() // ⬅️ ini WAJIB agar defaultSuccessUrl aktif
            )
            .formLogin(form -> form
                .loginPage("/login")
                .defaultSuccessUrl("/booking", true) // ⬅️ diarahkan ke sini setelah login
                .permitAll()
            )
          
            .logout(logout -> logout
                .logoutSuccessUrl("/login?logout").permitAll()
            );

        return http.build();
    }

    

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
        
    }
}