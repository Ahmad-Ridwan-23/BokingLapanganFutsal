package com.FUTSAL.BOOKING.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.FUTSAL.BOOKING.model.Lapangan;
import com.FUTSAL.BOOKING.service.LapanganService;

@Controller
@RequestMapping("/lapangan")
public class LapanganController {

    private final LapanganService service;

    public LapanganController(LapanganService service) {
        this.service = service;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("list", service.getAll());
        return "lapangan/index";
    }

    @GetMapping("/form")
    public String form(Model model) {
        model.addAttribute("lapangan", new Lapangan());
        return "lapangan/form";
    }

    @PostMapping
    public String save(@ModelAttribute Lapangan lapangan) {
        service.save(lapangan);
        return "redirect:/lapangan";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("lapangan", service.getById(id));
        return "lapangan/form";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable Long id, @ModelAttribute Lapangan lapangan) {
        service.update(id, lapangan);
        return "redirect:/lapangan";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "redirect:/lapangan";
    }
}

