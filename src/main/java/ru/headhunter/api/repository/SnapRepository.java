package ru.headhunter.api.repository;

import org.springframework.data.repository.CrudRepository;
import ru.headhunter.api.model.Snap;

public interface SnapRepository extends CrudRepository<Snap, Long> {

}
