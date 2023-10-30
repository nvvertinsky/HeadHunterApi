package ru.headhunter.api.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "snapb")
public class Snap {

    @Column(name = "snapb_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    @Column(nullable = false)
    private Date dt;

    public Snap() {
    }

    public Snap(Date dt) {
        this.dt = dt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDt() {
        return dt;
    }

    public void setDt(Date dt) {
        this.dt = dt;
    }
}
