package ru.headhunter.api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "vacancyb")
public class HeadHunterVacancy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vacancyb_id")
    private Long id;

    @Column(name = "hh_vacancy_id", nullable = false)
    private Long hhId;

    @Column(name = "name", nullable = false)
    @NotBlank(message = "Название вакансии не должно быть пустым")
    @Size(max = 240)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @ManyToOne()
    @JoinColumn(name = "snapb_id")
    private Snap snap;

    public HeadHunterVacancy() {}

    public HeadHunterVacancy(Long hhId, String name, String description) {
        this.hhId = hhId;
        this.name = name;
        this.description = description;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getHhId() {
        return hhId;
    }

    public void setHhId(Long hhId) {
        this.hhId = hhId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Snap getSnap() {
        return snap;
    }

    public void setSnap(Snap snap) {
        this.snap = snap;
    }
}
