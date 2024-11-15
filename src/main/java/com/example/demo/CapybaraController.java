package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.DTObuyRequest;
import com.example.demo.dto.DTOsellRequest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/capybara")
public class CapybaraController {

    @Autowired
    private CapybaraService capybaraService;
    @Autowired
    private UserDataService userDataService;

    @PostMapping("/create")
    public Capybara createCapybara(@RequestParam String name) {
        return capybaraService.createCapybara(name);
    }

    @GetMapping("/all")
    public List<Capybara> getAllCapybaras() {
        return capybaraService.getAllCapybaras();
    }
    
    @PostMapping("/buy")
    public ResponseEntity<Map<String, Object>> buyCapybara(@RequestBody DTObuyRequest entity) {
        Map<String, Object> response = new HashMap<>();
        Integer balance = userDataService.loadUserBalance(entity.getId());
        Capybara capybara;
        if(entity.getProposal().getCost() <= balance){
            response.put("result", "success");
            capybara = capybaraService.createCapybara("CapiCapi");
            response.put("capybara", capybara);
            userDataService.addCapybaraToUser(entity.getId(), capybara.getId());
            userDataService.updateUserBalance(entity.getId(), balance-entity.getProposal().getCost());
        }else{
            response.put("result", "error");
            response.put("msg", "Недостаточно средств на балансе:(");
        }
        return ResponseEntity.ok(response);
    }

    @PostMapping("/sell")
    public ResponseEntity<Map<String, Object>> sellCapybara(@RequestBody DTOsellRequest entity) {
        Map<String, Object> response = new HashMap<>();
        Integer balance = userDataService.loadUserBalance(entity.getId());
        try {
            userDataService.deleteCapybaraToUser(entity.getVkId(), entity.getId());
            capybaraService.deleteCapybaraById(entity.getId());
            response.put("result", "success");
            
        } catch (Exception e) {
            response.put("result", "error");
            response.put("msg", "Недостаточно средств на балансе:(");
        }
        
        return ResponseEntity.ok(response);
    }
}