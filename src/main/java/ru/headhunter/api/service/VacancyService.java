package ru.headhunter.api.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.headhunter.api.model.Snap;
import ru.headhunter.api.dto.VacanciesDTO;
import ru.headhunter.api.model.Vacancy;
import ru.headhunter.api.dto.VacancyDTO;

import java.util.Date;
import java.util.List;

@Service
public class VacancyService {

    @Autowired
    private VacancyDAO vacancyDAO;

    @Autowired
    private SnapDAO snapDAO;

    @Autowired
    private RestTemplate restTemplate;

    private String getVacanciesURL() {
        return new StringBuilder("https://api.hh.ru/vacancies")
                .append("?search_field=name")
                //.append("&search_field=description")
                .append("&area=1")
                .append("&text=Oracle")
                .append("&enable_snippets=false")
                .append("&page=1")
                .append("&per_page=2")
                .toString();
    }

    @Transactional
    public void saveVacancy() {
        final Snap snap = snapDAO.save(new Snap(new Date()));
        err();
        List<Vacancy> listVacancy = restTemplate.getForObject(getVacanciesURL(), VacanciesDTO.class)
                .items()
                .stream()
                .map(item -> restTemplate.getForObject(String.format("https://api.hh.ru/vacancies/%s", item.id()), VacancyDTO.class))
                .map(item -> new Vacancy(item.id(), item.name(), item.description(), snap))
                .toList();

        vacancyDAO.saveAll(listVacancy);
    }

    private void err() {
        throw new RuntimeException("Все плохо");
    }

}
