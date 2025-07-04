package com.FUTSAL.BOOKING.service;

import java.time.LocalDateTime;
import java.util.List;

import com.FUTSAL.BOOKING.model.Booking;
import com.FUTSAL.BOOKING.model.User;

public interface BookingService {
    List<Booking> getAll();
    Booking getById(Long id);
    Booking save(Booking booking);
    Booking update(Long id, Booking booking);
    void delete(Long id);
    List<Booking> getByUser(User user);

    
    

    // boolean isWaktuBookingBentrok(Long lapanganId, LocalDateTime mulai, LocalDateTime selesai);
    boolean isWaktuBookingBentrok(Long lapanganId, LocalDateTime mulai, LocalDateTime selesai);


    
}



