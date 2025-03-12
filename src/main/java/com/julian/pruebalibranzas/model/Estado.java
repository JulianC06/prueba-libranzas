package com.julian.pruebalibranzas.model;

import jakarta.persistence.*;

@Entity
@Table(name = "t00_estado")
public class Estado {

    @Id
    @Column(name = "id_estado")
    private Integer id;

    private String descripcion;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}