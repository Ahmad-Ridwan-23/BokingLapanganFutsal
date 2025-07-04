package com.FUTSAL.BOOKING.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.FUTSAL.BOOKING.model.Booking;
import com.FUTSAL.BOOKING.model.User;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByLapanganId(Long lapanganId);
    List<Booking> findByUserUsername(String username);
List<Booking> findByUser(User user);


}

