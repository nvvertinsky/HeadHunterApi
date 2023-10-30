package ru.headhunter.api.service;

import org.springframework.stereotype.Service;
import ru.headhunter.api.model.Snap;
import ru.headhunter.api.repository.SnapRepository;

@Service
public class SnapDAO {
    private final SnapRepository snapRepository;

    public SnapDAO(SnapRepository snapRepository) {
        this.snapRepository = snapRepository;
    }

    public Snap save(Snap snap) {
        return snapRepository.save(snap);
    }
}
