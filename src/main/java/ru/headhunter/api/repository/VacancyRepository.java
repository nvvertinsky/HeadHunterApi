package ru.headhunter.api.repository;

import org.springframework.data.repository.CrudRepository;
import ru.headhunter.api.model.HeadHunterVacancy;


public interface VacancyRepository extends CrudRepository<HeadHunterVacancy, Long> {

}
