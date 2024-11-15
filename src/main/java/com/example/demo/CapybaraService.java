package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Service
public class CapybaraService {

    @Autowired
    private CapybaraRepository capybaraRepository;
    @Autowired
    private UserDataRepository userDataRepository;

    private final Random random = new Random();

    public Capybara createCapybara(String name) {
        Capybara capybara = new Capybara();
        capybara.setName(name);
        capybara.setHead(getRandomHeadId());
        capybara.setBody(getRandomBodyId());
        capybara.setLegs(getRandomLegsId());
        capybara.setCollection("none");
        capybara.setTap(1);

        return capybaraRepository.save(capybara);
    }
    public UserData addCapybaraToUser(Long userId, Long capybaraId) {
    UserData user = userDataRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("Пользователь не найден"));

    // Добавляем ID капибары в массив capybaras
    Integer[] currentCapybaras = user.getCapybaras();
    Integer[] newCapybaras = Arrays.copyOf(currentCapybaras, currentCapybaras.length + 1);
    newCapybaras[newCapybaras.length - 1] = capybaraId.intValue();
    user.setCapybaras(newCapybaras);

    return userDataRepository.save(user);
}
   

    // Метод для случайного выбора ID головы из доступных ID
    private Integer getRandomHeadId() {
        Integer[] headIds = {1, 2}; // Замените на актуальные ID из таблицы head
        return headIds[random.nextInt(headIds.length)];
    }

    // Метод для случайного выбора ID тела из доступных ID
    private Integer getRandomBodyId() {
        Integer[] bodyIds = {1, 2}; // Замените на актуальные ID из таблицы body
        return bodyIds[random.nextInt(bodyIds.length)];
    }

    // Метод для случайного выбора ID ног из доступных ID
    private Integer getRandomLegsId() {
        Integer[] legsIds = {1, 2}; // Замените на актуальные ID из таблицы legs
        return legsIds[random.nextInt(legsIds.length)];
    }

    public List<Capybara> getAllCapybaras() {
        return capybaraRepository.findAll();
    }
    public void deleteCapybaraById(Long capyId) {
        capybaraRepository.deleteById(capyId);
    }
}