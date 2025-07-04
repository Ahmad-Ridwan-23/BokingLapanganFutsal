package com.FUTSAL.BOOKING.util;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordGenerator implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = "user123";
        String encoded = encoder.encode(rawPassword);
        System.out.println("BCrypt untuk '" + rawPassword + "' adalah:\n" + encoded);
    }
}
