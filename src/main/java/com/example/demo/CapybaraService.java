package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class CapybaraService {

    @Autowired
    private CapybaraRepository capybaraRepository;

    private final Random random = new Random();

    // Метод создания новой капибары со случайными частями
    public Capybara createCapybara(String name) {
        Capybara capybara = new Capybara();
        capybara.setName(name);
        capybara.setHead(getRandomHead());
        capybara.setBody(getRandomBody());
        capybara.setLegs(getRandomLegs());
        capybara.setCollection("none");
        capybara.setTap(0);

        return capybaraRepository.save(capybara);
    }

    // Методы для случайного выбора частей 
    private String getRandomHead() {
        String[] heads = {"head_type_1", "head_type_2"}; // Добавьте больше вариантов
        return heads[random.nextInt(heads.length)];
    }

    private String getRandomBody() {
        String[] bodies = {"body_type_1", "body_type_2"}; // Добавьте больше вариантов
        return bodies[random.nextInt(bodies.length)];
    }

    private String getRandomLegs() {
        String[] legs = {"legs_type_1", "legs_type_2"}; // Добавьте больше вариантов
        return legs[random.nextInt(legs.length)];
    }

    public List<Capybara> getAllCapybaras() {
        return capybaraRepository.findAll();
    }
}
