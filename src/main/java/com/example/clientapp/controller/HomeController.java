package com.example.clientapp.controller;

import java.security.Principal;

import com.example.clientapp.model.Boss;
import com.example.clientapp.repository.BossRepository;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final BossRepository bossRepository;

    public HomeController(BossRepository bossRepository) {
        this.bossRepository = bossRepository;
    }

    @GetMapping("/client/home")
    public String home(Model model, Principal principal) {

        String username = principal.getName();

        Boss boss = bossRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Boss not found"));

        model.addAttribute("username", boss.getUsername());
        model.addAttribute("fullName", boss.getFullName());

        return "home";
    }
}
