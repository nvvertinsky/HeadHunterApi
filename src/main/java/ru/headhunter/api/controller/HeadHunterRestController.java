package ru.headhunter.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.headhunter.api.service.VacancyService;


@RestController
public class HeadHunterRestController {
    private final VacancyService vacancyService;

    public HeadHunterRestController(VacancyService vacancyService) {
        this.vacancyService = vacancyService;
    }

    @GetMapping("/vacancy")
    public String loadVacancy(){
        vacancyService.saveVacancy();
        return "Вакансии загружены в БД";
    }
}
