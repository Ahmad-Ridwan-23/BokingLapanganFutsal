// package com.FUTSAL.BOOKING.controller;

// import java.time.format.DateTimeFormatter;

// import org.springframework.stereotype.Controller;
// import org.springframework.ui.Model;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.ModelAttribute;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestMapping;

// import com.FUTSAL.BOOKING.model.Booking;
// import com.FUTSAL.BOOKING.model.Lapangan;
// import com.FUTSAL.BOOKING.service.BookingService;
// import com.FUTSAL.BOOKING.service.LapanganService;



// @Controller
// @RequestMapping("/booking")
// public class BookingController {

//     private final BookingService bookingService;
//     private final LapanganService lapanganService;
    

//     public BookingController(BookingService bookingService, LapanganService lapanganService) {
//         this.bookingService = bookingService;
//         this.lapanganService = lapanganService;
//     }

// @GetMapping
// public String index(Model model) {
//     model.addAttribute("bookingList", bookingService.getAll());
//     model.addAttribute("formatter", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
//     return "booking/index";
// }



//     @GetMapping("/form")
// public String form(Model model) {
//     model.addAttribute("booking", new Booking());
//     model.addAttribute("lapanganList", lapanganService.getAll());
//     return "booking/form";
// }



    

// //    @PostMapping
// // public String save(@ModelAttribute Booking booking) {
// //     Long lapanganId = booking.getLapangan().getId(); // Ambil ID dari form
// //     Lapangan selectedLapangan = lapanganService.getById(lapanganId); // Ambil object-nya
// //     booking.setLapangan(selectedLapangan); // Set ke object booking
// //     bookingService.save(booking);
// //     return "redirect:/booking";
// // }

// @PostMapping
// public String save(@ModelAttribute Booking booking, Model model) {
//     Long lapanganId = booking.getLapangan().getId();
//     Lapangan selectedLapangan = lapanganService.getById(lapanganId);
//     booking.setLapangan(selectedLapangan);

//     if (bookingService.isWaktuBookingBentrok(lapanganId, booking.getTanggalMulai(), booking.getTanggalSelesai())) {
//         model.addAttribute("booking", booking);
//         model.addAttribute("lapanganList", lapanganService.getAll());
//         model.addAttribute("error", "Waktu booking tabrakan dengan jadwal lain.");
//         return "booking/form";
//     }

//     bookingService.save(booking);
//     return "redirect:/booking";
// }





//     @GetMapping("/edit/{id}")
// public String edit(@PathVariable Long id, Model model) {
//     model.addAttribute("booking", bookingService.getById(id));
//     model.addAttribute("lapanganList", lapanganService.getAll());
//     return "booking/form";
// }

    

//     @PostMapping("/update/{id}")
// public String update(@PathVariable Long id, @ModelAttribute Booking booking) {
//     Long lapanganId = booking.getLapangan().getId();
//     Lapangan selectedLapangan = lapanganService.getById(lapanganId);
//     booking.setLapangan(selectedLapangan);
//     bookingService.update(id, booking);
//     return "redirect:/booking";
// }

// // @PostMapping("/booking")
// // public String save(@ModelAttribute Booking booking) {
// //     Authentication auth = SecurityContextHolder.getContext().getAuthentication();
// //     String username = auth.getName();

// //     User user = userRepository.findByUsername(username).orElseThrow();
// //     booking.setUser(user);

// //     bookingService.save(booking);
// //     return "redirect:/booking";
// // }


//     @GetMapping("/delete/{id}")
//     public String delete(@PathVariable Long id) {
//         bookingService.delete(id);
//         return "redirect:/booking";
//     }
    
// }

// package com.FUTSAL.BOOKING.controller;

// import java.time.Duration;
// import java.time.format.DateTimeFormatter;

// import org.springframework.security.core.Authentication;
// import org.springframework.stereotype.Controller;
// import org.springframework.ui.Model;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.ModelAttribute;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestMapping;

// import com.FUTSAL.BOOKING.model.Booking;
// import com.FUTSAL.BOOKING.model.Lapangan;
// import com.FUTSAL.BOOKING.model.User;
// import com.FUTSAL.BOOKING.repository.UserRepository;
// import com.FUTSAL.BOOKING.service.BookingService;
// import com.FUTSAL.BOOKING.service.LapanganService;

// @Controller
// @RequestMapping("/booking")
// public class BookingController {

//     private final BookingService bookingService;
//     private final LapanganService lapanganService;
//     private final UserRepository userRepository;

//     public BookingController(
//             BookingService bookingService,
//             LapanganService lapanganService,
//             UserRepository userRepository) {
//         this.bookingService = bookingService;
//         this.lapanganService = lapanganService;
//         this.userRepository = userRepository;
//     }


//     @GetMapping
//     public String index(Model model, Authentication auth) {
//     User user = userRepository.findByUsername(auth.getName()).orElse(null);

//     if (user.getRole().equals("ROLE_ADMIN")) {
//         model.addAttribute("bookingList", bookingService.getAll());
//     } else {
//         model.addAttribute("bookingList", bookingService.getByUser(user));
//     }

//     model.addAttribute("formatter", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
//     return "booking/index";
// }


//     @GetMapping("/form")
//     public String form(Model model) {
//         model.addAttribute("booking", new Booking());
//         model.addAttribute("lapanganList", lapanganService.getAll());
//         return "booking/form";
//     }
   

// @GetMapping("/bayar/{id}")
// public String showBayarPage(@PathVariable Long id, Model model) {
//     Booking booking = bookingService.getById(id);
//     model.addAttribute("booking", booking);
//     return "booking/bayar";
// }

// @GetMapping("/lunas/{id}")
// public String tandaiLunas(@PathVariable Long id) {
//     Booking booking = bookingService.getById(id);
//     booking.setStatusPembayaran("Lunas");
//     bookingService.save(booking);
//     return "redirect:/booking";
// }



//     // @PostMapping
//     // public String save(@ModelAttribute Booking booking, Model model) {
//     //     Long lapanganId = booking.getLapangan().getId();
//     //     Lapangan selectedLapangan = lapanganService.getById(lapanganId);
//     //     booking.setLapangan(selectedLapangan);

//     //     if (bookingService.isWaktuBookingBentrok(lapanganId, booking.getTanggalMulai(), booking.getTanggalSelesai())) {
//     //         model.addAttribute("booking", booking);
//     //         model.addAttribute("lapanganList", lapanganService.getAll());
//     //         model.addAttribute("error", "Waktu booking tabrakan dengan jadwal lain.");
//     //         return "booking/form";
//     //     }

//     //     bookingService.save(booking);
//     //     return "redirect:/booking";
//     // }

// //     @PostMapping
// // public String save(@ModelAttribute Booking booking, Model model) {
// //     Long lapanganId = booking.getLapangan().getId();
// //     Lapangan selectedLapangan = lapanganService.getById(lapanganId);
// //     booking.setLapangan(selectedLapangan);

// //     // ✅ Ambil user yang sedang login
// //     Authentication auth = SecurityContextHolder.getContext().getAuthentication();
// //     String username = auth.getName();
// //     User user = userRepository.findByUsername(username).orElseThrow();
// //     booking.setUser(user); // 🟢 Simpan user ke dalam booking

// //     // 🛑 Cek bentrok waktu
// //     if (bookingService.isWaktuBookingBentrok(lapanganId, booking.getTanggalMulai(), booking.getTanggalSelesai())) {
// //         model.addAttribute("booking", booking);
// //         model.addAttribute("lapanganList", lapanganService.getAll());
// //         model.addAttribute("error", "Waktu booking tabrakan dengan jadwal lain.");
// //         return "booking/form";
// //     }

// //     bookingService.save(booking);
// //     return "redirect:/booking";
// // }

// @PostMapping
// public String save(@ModelAttribute Booking booking, Model model, Authentication auth) {
//     Long lapanganId = booking.getLapangan().getId();
//     Lapangan selectedLapangan = lapanganService.getById(lapanganId);
//     booking.setLapangan(selectedLapangan);

//     // Cek tabrakan jadwal
//     if (bookingService.isWaktuBookingBentrok(lapanganId, booking.getTanggalMulai(), booking.getTanggalSelesai())) {
//         model.addAttribute("booking", booking);
//         model.addAttribute("lapanganList", lapanganService.getAll());
//         model.addAttribute("error", "Waktu booking tabrakan dengan jadwal lain.");
//         return "booking/form";
//     }

//     if (!booking.getTanggalMulai().isBefore(booking.getTanggalSelesai())) {
//     model.addAttribute("booking", booking);
//     model.addAttribute("lapanganList", lapanganService.getAll());
//     model.addAttribute("error", "Waktu mulai harus sebelum waktu selesai.");
//     return "booking/form";
// }


//     // ✅ Set user (agar user bisa lihat booking miliknya)
//     User user = userRepository.findByUsername(auth.getName()).orElse(null);
//     booking.setUser(user);

//     // // ✅ Hitung total harga otomatis
//     // Duration durasi = Duration.between(booking.getTanggalMulai(), booking.getTanggalSelesai());
//     // long jam = durasi.toHours();
//     // double hargaPerJam = 50000;
//     // booking.setTotalHarga(jam * hargaPerJam);

//     Duration durasi = Duration.between(booking.getTanggalMulai(), booking.getTanggalSelesai());
//     long menit = durasi.toMinutes();
//     double hargaPerMenit = 50000.0 / 60; // Harga per menit dari Rp 50.000/jam
//     booking.setTotalHarga(menit * hargaPerMenit);


//     // ✅ Set default status pembayaran
//     booking.setStatusPembayaran("Belum Bayar");

//     // Simpan
//     bookingService.save(booking);
//     return "redirect:/booking";
// }


//     @GetMapping("/edit/{id}")
//     public String edit(@PathVariable Long id, Model model) {
//         model.addAttribute("booking", bookingService.getById(id));
//         model.addAttribute("lapanganList", lapanganService.getAll());
//         return "booking/form";
//     }

//     @PostMapping("/update/{id}")
//     public String update(@PathVariable Long id, @ModelAttribute Booking booking) {
//         Long lapanganId = booking.getLapangan().getId();
//         Lapangan selectedLapangan = lapanganService.getById(lapanganId);
//         booking.setLapangan(selectedLapangan);
//         bookingService.update(id, booking);
//         return "redirect:/booking";
//     }

//     // @PostMapping("/booking")
//     // public String saveBookingWithUser(@ModelAttribute Booking booking) {
//     //     Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//     //     String username = auth.getName();

//     //     Optional<User> optionalUser = userRepository.findByUsername(username);
//     //     if (optionalUser.isPresent()) {
//     //         booking.setUser(optionalUser.get());
//     //     } else {
//     //         // Kalau user tidak ditemukan (kemungkinan besar tidak terjadi), redirect ke login
//     //         return "redirect:/login?error=userNotFound";
//     //     }

//     //     bookingService.save(booking);
//     //     return "redirect:/booking";
//     // }

//     @GetMapping("/delete/{id}")
//     public String delete(@PathVariable Long id) {
//         bookingService.delete(id);
//         return "redirect:/booking";
//     }

    
// }
// 
// 
// 
// 
// 
// 
// 
// 
// 
// 
package com.FUTSAL.BOOKING.controller;

import java.time.Duration;
import java.time.format.DateTimeFormatter;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.FUTSAL.BOOKING.model.Booking;
import com.FUTSAL.BOOKING.model.Lapangan;
import com.FUTSAL.BOOKING.model.User;
import com.FUTSAL.BOOKING.repository.UserRepository;
import com.FUTSAL.BOOKING.service.BookingService;
import com.FUTSAL.BOOKING.service.LapanganService;

@Controller
@RequestMapping("/booking")
public class BookingController {

    private final BookingService bookingService;
    private final LapanganService lapanganService;
    private final UserRepository userRepository;

    public BookingController(
            BookingService bookingService,
            LapanganService lapanganService,
            UserRepository userRepository) {
        this.bookingService = bookingService;
        this.lapanganService = lapanganService;
        this.userRepository = userRepository;
    }

    // Tampilkan daftar booking berdasarkan role
    @GetMapping
    public String index(Model model, Authentication auth) {
        User user = userRepository.findByUsername(auth.getName()).orElse(null);

        if (user.getRole().equals("ROLE_ADMIN")) {
            model.addAttribute("bookingList", bookingService.getAll());
        } else {
            model.addAttribute("bookingList", bookingService.getByUser(user));
        }

        model.addAttribute("formatter", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        return "booking/index";
    }

    // Form tambah booking
    @GetMapping("/form")
    public String form(Model model) {
        model.addAttribute("booking", new Booking());
        model.addAttribute("lapanganList", lapanganService.getAll());
        return "booking/form";
    }

    // USER melihat QR bayar
    @GetMapping("/bayar/{id}")
    public String showBayarPage(@PathVariable Long id, Model model) {
        Booking booking = bookingService.getById(id);
        model.addAttribute("booking", booking);
        return "booking/bayar";
    }

    // ADMIN menandai sudah lunas
    @GetMapping("/lunas/{id}")
    public String tandaiLunas(@PathVariable Long id) {
        Booking booking = bookingService.getById(id);
        booking.setStatusPembayaran("Lunas");
        bookingService.save(booking);
        return "redirect:/booking";
    }

    // Simpan booking baru
    @PostMapping
    public String save(@ModelAttribute Booking booking, Model model, Authentication auth) {
        Long lapanganId = booking.getLapangan().getId();
        Lapangan selectedLapangan = lapanganService.getById(lapanganId);
        booking.setLapangan(selectedLapangan);

        // Cek tabrakan
        if (bookingService.isWaktuBookingBentrok(lapanganId, booking.getTanggalMulai(), booking.getTanggalSelesai())) {
            model.addAttribute("booking", booking);
            model.addAttribute("lapanganList", lapanganService.getAll());
            model.addAttribute("error", "Waktu booking tabrakan dengan jadwal lain.");
            return "booking/form";
        }

        // Validasi waktu mulai < selesai
        if (!booking.getTanggalMulai().isBefore(booking.getTanggalSelesai())) {
            model.addAttribute("booking", booking);
            model.addAttribute("lapanganList", lapanganService.getAll());
            model.addAttribute("error", "Waktu mulai harus sebelum waktu selesai.");
            return "booking/form";
        }

        // Set user yang sedang login
        User user = userRepository.findByUsername(auth.getName()).orElse(null);
        booking.setUser(user);

        // ✅ Hitung total harga berdasarkan harga lapangan
        double hargaPerJam = selectedLapangan.getHargaPerJam();
        Duration durasi = Duration.between(booking.getTanggalMulai(), booking.getTanggalSelesai());
        long menit = durasi.toMinutes();
        double hargaPerMenit = hargaPerJam / 60.0;
        booking.setTotalHarga(menit * hargaPerMenit);

        // Set status awal
        booking.setStatusPembayaran("Belum Bayar");

        // Simpan
        bookingService.save(booking);
        return "redirect:/booking";
    }

    // Edit booking
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("booking", bookingService.getById(id));
        model.addAttribute("lapanganList", lapanganService.getAll());
        return "booking/form";
    }

    // Update booking
    @PostMapping("/update/{id}")
    public String update(@PathVariable Long id, @ModelAttribute Booking booking) {
        Long lapanganId = booking.getLapangan().getId();
        Lapangan selectedLapangan = lapanganService.getById(lapanganId);
        booking.setLapangan(selectedLapangan);
        bookingService.update(id, booking);
        return "redirect:/booking";
    }

    // Hapus booking
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        bookingService.delete(id);
        return "redirect:/booking";
    }
}

