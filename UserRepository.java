// package com.FUTSAL.BOOKING.repository;

// import java.util.Optional;

// import org.springframework.data.jpa.repository.JpaRepository;

// import com.FUTSAL.BOOKING.model.User;



// public interface UserRepository extends JpaRepository<User, Long> {
//     Optional<User> findByUsername(String username);

    
// }

package com.FUTSAL.BOOKING.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.FUTSAL.BOOKING.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
    // ✅ Digunakan untuk proses login dan autentikasi
    Optional<User> findByUsername(String username);
    
}
