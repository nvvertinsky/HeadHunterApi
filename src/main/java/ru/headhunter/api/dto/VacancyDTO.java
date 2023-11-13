package ru.headhunter.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record VacancyDTO(Long id, String name, String description) { }
