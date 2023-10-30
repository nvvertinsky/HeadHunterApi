package ru.headhunter.api.repository;

import org.springframework.data.repository.CrudRepository;
import ru.headhunter.api.model.Vacancy;


public interface VacancyRepository extends CrudRepository<Vacancy, Long> {

}
