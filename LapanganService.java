package com.FUTSAL.BOOKING.service;


import java.util.List;

import com.FUTSAL.BOOKING.model.Lapangan;

public interface LapanganService {
    List<Lapangan> getAll();
    Lapangan getById(Long id);
    Lapangan save(Lapangan lapangan);
    Lapangan update(Long id, Lapangan lapangan);
    void delete(Long id);
}

