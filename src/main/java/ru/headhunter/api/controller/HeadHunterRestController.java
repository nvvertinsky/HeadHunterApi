package ru.headhunter.api.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import ru.headhunter.api.dto.VacanciesDTO;
import ru.headhunter.api.dto.VacancyDTO;
import ru.headhunter.api.model.ErrorResponse;
import ru.headhunter.api.model.HeadHunterVacancy;
import ru.headhunter.api.service.HeadHunterVacancyService;

import java.util.List;


@RestController
public class HeadHunterRestController {

    @Autowired
    private HeadHunterVacancyService headHunterVacancyService;
    @Autowired
    private RestTemplate restTemplate;
    @Value("${hh.vacancies.url}")
    private String vacanciesURL;
    @Value("${hh.vacancy.url}")
    private String vacancyURL;

    @GetMapping("/load")
    public ResponseEntity<HttpStatus> load(){
        VacanciesDTO vacanciesDTO = restTemplate.getForObject(vacanciesURL, VacanciesDTO.class);

        List<HeadHunterVacancy> listHeadHunterVacancy = vacanciesDTO.items()
                .stream()
                .map(item -> restTemplate.getForObject(String.format(vacancyURL + "/%s", item.id()), VacancyDTO.class))
                .map(item -> new HeadHunterVacancy(item.id(), item.name(), item.description()))
                .toList();


        headHunterVacancyService.saveVacancy(listHeadHunterVacancy);
        return new ResponseEntity<HttpStatus>(HttpStatus.OK);
    }

    @ExceptionHandler
    private ResponseEntity<ErrorResponse> hadleException(RuntimeException e) {
        return new ResponseEntity<>(new ErrorResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
    }
}
