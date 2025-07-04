package com.FUTSAL.BOOKING.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String namaPemesan;
    private String noHp;

    private LocalDateTime tanggalMulai;
    private LocalDateTime tanggalSelesai;

    @ManyToOne
    @JoinColumn(name = "lapangan_id")
    private Lapangan lapangan;

    @Column(name = "status_pembayaran")
    private String statusPembayaran = "Belum Bayar"; // default

    @Column(name = "total_harga")
    private Double totalHarga;

}
