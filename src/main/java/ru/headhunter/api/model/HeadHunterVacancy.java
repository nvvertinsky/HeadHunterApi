package ru.headhunter.api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "vacancyb")
public class HeadHunterVacancy {
    @Id
    @Column(name = "vacancyb_id")
    private Long id;

    @Column(name = "name", nullable = false)
    @Size(max = 240, min = 1, message = "Имя должно быть от 1 до 240 символов")
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @ManyToOne()
    @JoinColumn(name = "snapb_id")
    private Snap snap;

    public HeadHunterVacancy() {}

    public HeadHunterVacancy(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
