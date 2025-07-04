package com.FUTSAL.BOOKING.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.FUTSAL.BOOKING.model.Lapangan;
import com.FUTSAL.BOOKING.repository.LapanganRepository;

@Service
public class LapanganServiceImpl implements LapanganService {

    private final LapanganRepository repo;

    public LapanganServiceImpl(LapanganRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<Lapangan> getAll() {
        return repo.findAll();
    }

    @Override
    public Lapangan getById(Long id) {
        return repo.findById(id).orElseThrow();
    }

    @Override
    public Lapangan save(Lapangan lapangan) {
        return repo.save(lapangan);
    }

    @Override
    public Lapangan update(Long id, Lapangan lapangan) {
        Lapangan existing = getById(id);
        existing.setNama(lapangan.getNama());
        existing.setTipe(lapangan.getTipe());
        existing.setHargaPerJam(lapangan.getHargaPerJam());
        return repo.save(existing);
    }



    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }
}
