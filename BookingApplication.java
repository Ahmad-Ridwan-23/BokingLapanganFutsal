package com.FUTSAL.BOOKING;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.FUTSAL.BOOKING.model.User;
import com.FUTSAL.BOOKING.repository.UserRepository;

@SpringBootApplication
public class BookingApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookingApplication.class, args);
    }

    // Tambahkan kode ini DI DALAM class BookingApplication
    @Bean
    CommandLineRunner run(UserRepository repo, BCryptPasswordEncoder encoder) {
        return args -> {
            if (repo.findByUsername("admin").isEmpty()) {
                User admin = new User(null, "admin", encoder.encode("admin123"), "ROLE_ADMIN");
                User user = new User(null, "user", encoder.encode("user123"), "ROLE_USER");
                repo.save(admin);
                repo.save(user);
            }
        };
    }
}
