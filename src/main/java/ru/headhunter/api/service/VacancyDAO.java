package ru.headhunter.api.service;

import org.springframework.stereotype.Service;
import ru.headhunter.api.model.Vacancy;
import ru.headhunter.api.repository.VacancyRepository;

import java.util.List;

@Service
public class VacancyDAO {
    private final VacancyRepository vacancyRepository;

    public VacancyDAO(VacancyRepository vacancyRepository) {
        this.vacancyRepository = vacancyRepository;
    }

    public Iterable<Vacancy> saveAll(List<Vacancy> listVacancy) {
        return vacancyRepository.saveAll(listVacancy);
    }
}
