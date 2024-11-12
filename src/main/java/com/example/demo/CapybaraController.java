package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/capybara")
public class CapybaraController {

    @Autowired
    private CapybaraService capybaraService;

    @PostMapping("/create")
    public Capybara createCapybara(@RequestParam String name) {
        return capybaraService.createCapybara(name);
    }

    @GetMapping("/all")
    public List<Capybara> getAllCapybaras() {
        return capybaraService.getAllCapybaras();
    }
}
