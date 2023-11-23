package ru.headhunter.api.service;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.headhunter.api.model.Snap;
import ru.headhunter.api.repository.SnapRepository;

@Service
public class SnapService {

    @Autowired
    private SnapRepository snapRepository;

    @Transactional
    public void saveSnap(@Valid Snap snap) {
        snapRepository.save(snap);
    }
}
