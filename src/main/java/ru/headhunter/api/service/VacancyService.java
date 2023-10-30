package ru.headhunter.api.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.headhunter.api.model.Snap;
import ru.headhunter.api.model.VacanciesDTO;
import ru.headhunter.api.model.Vacancy;
import ru.headhunter.api.model.VacancyDTO;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VacancyService {

    private final VacancyDAO vacancyDAO;
    private final SnapDAO snapDAO;
    private final RestTemplate restTemplate;

    public VacancyService(VacancyDAO vacancyDAO, SnapDAO snapDAO, RestTemplate restTemplate) {
        this.vacancyDAO = vacancyDAO;
        this.snapDAO = snapDAO;
        this.restTemplate = restTemplate;
    }

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

        List<VacancyDTO> listVacancyDTO = this.restTemplate.getForObject(getVacanciesURL(), VacanciesDTO.class)
                .items()
                .stream()
                .map(item -> this.restTemplate.getForObject(String.format("https://api.hh.ru/vacancies/%s", item.id()), VacancyDTO.class))
                .collect(Collectors.toList());

        List<Vacancy> listVacancy = listVacancyDTO.stream().map(item -> new Vacancy(item.id(), item.name(), item.description(), snap)).toList();

        vacancyDAO.saveAll(listVacancy);
    }

}
