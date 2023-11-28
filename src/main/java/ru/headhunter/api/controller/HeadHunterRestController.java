package ru.headhunter.api.controller;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import ru.headhunter.api.dto.VacanciesDTO;
import ru.headhunter.api.dto.VacancyDTO;
import ru.headhunter.api.model.ErrorResponse;
import ru.headhunter.api.model.HeadHunterVacancy;
import ru.headhunter.api.service.HeadHunterVacancyService;

import java.util.List;


@RestController
@RequestMapping("headhunter")
public class HeadHunterRestController {

    @Autowired
    private HeadHunterVacancyService headHunterVacancyService;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private RestTemplate restTemplate;
    @Value("${hh.vacancies.url}")
    private String vacanciesURL;
    @Value("${hh.vacancy.url}")
    private String vacancyURL;

    @GetMapping("/load")
    public ResponseEntity<HttpStatus> load(){
        VacanciesDTO vacanciesDTO = restTemplate.getForObject(vacanciesURL, VacanciesDTO.class);

        List<HeadHunterVacancy> listHeadHunterVacancy = vacanciesDTO.getItems()
                .stream()
                .map(item -> restTemplate.getForObject(String.format(vacancyURL + "/%s", item.getId()), VacancyDTO.class))
                .map(item -> convertToHeadHunterVacancy(item))
                .toList();


        headHunterVacancyService.saveVacancy(listHeadHunterVacancy);
        return new ResponseEntity<HttpStatus>(HttpStatus.OK);
    }

    @GetMapping("/vacancy/{id}")
    public VacancyDTO getVacancy(@PathVariable("id") Long id) {
        return convertToVacancyDTO(headHunterVacancyService.getVacancy(id));
    }

    private HeadHunterVacancy convertToHeadHunterVacancy(VacancyDTO vacancyDTO) {
        return modelMapper.map(vacancyDTO, HeadHunterVacancy.class);
    }

    private VacancyDTO convertToVacancyDTO(HeadHunterVacancy vacancy) {
        return modelMapper.map(vacancy, VacancyDTO.class);
    }

    @ExceptionHandler
    private ResponseEntity<ErrorResponse> hadleException(RuntimeException e) {
        return new ResponseEntity<>(new ErrorResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
    }
}
