package ru.headhunter.api.repository;

import org.springframework.data.repository.CrudRepository;
import ru.headhunter.api.model.HeadHunterVacancy;

import java.util.Optional;


public interface VacancyRepository extends CrudRepository<HeadHunterVacancy, Long> {

}
