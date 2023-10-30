package ru.headhunter.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record VacanciesDTO(List<VacancyDTO> items) { }
