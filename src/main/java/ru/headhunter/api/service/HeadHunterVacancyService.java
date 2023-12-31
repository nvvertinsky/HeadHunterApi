package ru.headhunter.api.service;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.headhunter.api.exception.VacancyNoFoundException;
import ru.headhunter.api.model.HeadHunterVacancy;
import ru.headhunter.api.model.Snap;
import ru.headhunter.api.repository.SnapRepository;
import ru.headhunter.api.repository.VacancyRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class HeadHunterVacancyService {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private SnapRepository snapRepository;
    @Autowired
    private VacancyRepository vacancyRepository;

    @Transactional
    public void saveVacancy(@Valid List<HeadHunterVacancy> vacancies) {
        Snap snap = new Snap(new Date());

        snapRepository.save(snap);

        for (int i = 0; i < vacancies.size(); i++) {
            vacancies.get(i).setSnap(snap);
        }

        vacancyRepository.saveAll(vacancies);
    }

    @Transactional
    public HeadHunterVacancy getVacancy(Long id) {
        Optional<HeadHunterVacancy> headHunterVacancy = vacancyRepository.findById(id);

        if (headHunterVacancy.isEmpty()) {
            throw new VacancyNoFoundException("Вакансия с таким Head Hunter ID не найдена.");
        }

        return headHunterVacancy.get();
    }

}
