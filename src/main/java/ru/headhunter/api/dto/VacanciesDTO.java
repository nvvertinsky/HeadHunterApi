package ru.headhunter.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class VacanciesDTO {
    private List<VacancyDTO> items;

    public VacanciesDTO() {
    }

    public VacanciesDTO(List<VacancyDTO> items) {
        this.items = items;
    }

    public List<VacancyDTO> getItems() {
        return items;
    }

    public void setItems(List<VacancyDTO> items) {
        this.items = items;
    }
}
