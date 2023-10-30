package ru.headhunter.api.model;

import jakarta.persistence.*;

@Entity
@Table(name = "vacancyb")
public class Vacancy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vacancyb_id")
    private Long id;
    @Column(name = "hh_vacancy_id")
    private Long hhId;
    private String name;
    private String description;

    @ManyToOne()
    @JoinColumn(name = "snapb_id")
    private Snap snap;

    public Vacancy() {

    }

    public Vacancy(Long hhId, String name, String description, Snap snap) {
        this.hhId = hhId;
        this.name = name;
        this.description = description;
        this.snap = snap;
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
