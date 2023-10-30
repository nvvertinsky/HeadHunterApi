package ru.headhunter.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record VacancyDTO(Long id, String name, String description) { }
