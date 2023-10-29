package com.example.samuraitravel.controller;

import com.example.samuraitravel.entity.House;
import com.example.samuraitravel.repository.HouseRepository;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {
    private final HouseRepository houseRepository;

    public HomeController(HouseRepository houseRepository) {
        this.houseRepository = houseRepository;
    }
    @GetMapping("/")
    public  String index(Model model) {
        List<House> newHouses = houseRepository.findTop10ByOrderByCreatedAtDesc();
        model.addAttribute("newHouses", newHouses);

        return "index";
    }
}
