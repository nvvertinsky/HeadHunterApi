package ru.headhunter.api.controller;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import ru.headhunter.api.model.Vacancies;
import ru.headhunter.api.model.Vacancy;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class HeadHunterRestController {
    // 01. Хреновые результаты поиска. Поправить.
    // 02. Сохранять в базу данных
    private final RestTemplate restTemplate;

    HeadHunterRestController(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public String getVacanciesURL() {
        return new StringBuilder("https://api.hh.ru/vacancies")
                .append("?search_field=name")
                .append("&search_field=company_name")
                .append("&search_field=description")
                .append("&area=1")
                .append("&text=разработчик+Oracle")
                .append("&enable_snippets=false")
                .append("&page=1")
                .toString();
    }

    @GetMapping("/vacancy")
    public List loadVacancy(){
        return this.restTemplate.getForObject(getVacanciesURL(), Vacancies.class)
                .items()
                .stream()
                .map(item -> this.restTemplate.getForObject(String.format("https://api.hh.ru/vacancies/%s", item.id()), Vacancy.class))
                .collect(Collectors.toList());
    }
}
