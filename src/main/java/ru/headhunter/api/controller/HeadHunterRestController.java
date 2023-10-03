package ru.headhunter.api.controller;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import ru.headhunter.api.model.Vacancies;

@RestController
public class HeadHunterRestController {

    private final RestTemplate restTemplate;

    HeadHunterRestController(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }


    @GetMapping("/vacancy")
    public String loadVacancy(){
        Vacancies vacancies = this.restTemplate.getForObject("https://api.hh.ru/vacancies?text=Разработчик Oracle&page=0&per_page=1", Vacancies.class);

        return vacancies.toString();
    }
}
