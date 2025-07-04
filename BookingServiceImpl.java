// package com.FUTSAL.BOOKING.service;

// import java.time.LocalDateTime;
// import java.util.List;

// import org.springframework.stereotype.Service;

// import com.FUTSAL.BOOKING.model.Booking;
// import com.FUTSAL.BOOKING.repository.BookingRepository;

// @Service
// public class BookingServiceImpl implements BookingService {

//     private final BookingRepository repo;

//     public BookingServiceImpl(BookingRepository repo) {
//         this.repo = repo;
//     }

//     @Override
//     public List<Booking> getAll() {
//         return repo.findAll();
//     }

//     @Override
//     public Booking getById(Long id) {
//         return repo.findById(id).orElseThrow();
//     }

//     @Override
//     public Booking save(Booking booking) {
//         return repo.save(booking);
//     }

//     @Override
//     public Booking update(Long id, Booking booking) {
//         Booking existing = getById(id);
//         existing.setNamaPemesan(booking.getNamaPemesan());
//         existing.setNoHp(booking.getNoHp());
//         existing.setTanggalMulai(booking.getTanggalMulai());
//         existing.setTanggalSelesai(booking.getTanggalSelesai());
//         existing.setLapangan(booking.getLapangan());
//         return repo.save(existing);
//     }

//     @Override
//     public void delete(Long id) {
//         repo.deleteById(id);
//     }

//     //  Method validasi jadwal booking agar tidak tabrakan
//     @Override
//     public boolean isWaktuBookingBentrok(Long lapanganId, LocalDateTime mulai, LocalDateTime selesai) {
//         List<Booking> existing = repo.findByLapanganId(lapanganId);

//         for (Booking b : existing) {
//             // Validasi bentrok: jika ada irisan waktu
//             if (mulai.isBefore(b.getTanggalSelesai()) && selesai.isAfter(b.getTanggalMulai())) {
//                 return true; // Bentrok
//             }
//         }
//         return false; // Tidak bentrok
//     }
// }

package com.FUTSAL.BOOKING.service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.FUTSAL.BOOKING.model.Booking;
import com.FUTSAL.BOOKING.model.User;
import com.FUTSAL.BOOKING.repository.BookingRepository;

@Service
public class BookingServiceImpl implements BookingService {

    private final BookingRepository repo;

    public BookingServiceImpl(BookingRepository repo) {
        this.repo = repo;
    }

    // ✅ FILTER BERDASARKAN ROLE ADMIN ATAU USER
    @Override
    public List<Booking> getAll() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        // Jika ADMIN → tampilkan semua data
        if (auth.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
            return repo.findAll();
        }

        // Jika USER → tampilkan hanya booking milik dia
        String username = auth.getName();
        return repo.findByUserUsername(username); // method ini harus ada di BookingRepository
    }

    

    @Override
    public Booking getById(Long id) {
        return repo.findById(id).orElseThrow();
    }

    @Override
    public Booking save(Booking booking) {
        // Duration durasi = Duration.between(booking.getTanggalMulai(), booking.getTanggalSelesai());
        // long jam = durasi.toHours();

        // double hargaPerJam = 50000; // misal
        // booking.setTotalHarga(jam * hargaPerJam);

        Duration durasi = Duration.between(booking.getTanggalMulai(), booking.getTanggalSelesai());
        long jam = durasi.toHours();

        double hargaPerJam = booking.getLapangan().getHargaPerJam();
        booking.setTotalHarga(jam * hargaPerJam);


        if (booking.getStatusPembayaran() == null || booking.getStatusPembayaran().isBlank()) {
        booking.setStatusPembayaran("Belum Bayar");
        }
        if (booking.getTotalHarga() == null) {
        booking.setTotalHarga(0.0);
        }

        


        return repo.save(booking);
        

        
    }

    @Override
    public Booking update(Long id, Booking booking) {
        Booking existing = getById(id);
        existing.setNamaPemesan(booking.getNamaPemesan());
        existing.setNoHp(booking.getNoHp());
        existing.setTanggalMulai(booking.getTanggalMulai());
        existing.setTanggalSelesai(booking.getTanggalSelesai());
        existing.setLapangan(booking.getLapangan());
        return repo.save(existing);
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }

    

    @Override
    public boolean isWaktuBookingBentrok(Long lapanganId, LocalDateTime mulai, LocalDateTime selesai) {
        List<Booking> existing = repo.findByLapanganId(lapanganId);

        for (Booking b : existing) {
            // Cek tabrakan waktu
            if (mulai.isBefore(b.getTanggalSelesai()) && selesai.isAfter(b.getTanggalMulai())) {
                return true;
            }
        }
        return false;
    }
    @Override
public List<Booking> getByUser(User user) {
    return repo.findByUser(user);
}


    
}

