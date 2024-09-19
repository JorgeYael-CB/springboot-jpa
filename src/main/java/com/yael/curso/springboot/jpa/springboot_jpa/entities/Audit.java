package com.yael.curso.springboot.jpa.springboot_jpa.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;




@Embeddable
public class Audit {

    @Column(name="create_at")
    private LocalDateTime createdAt;

    @Column(name="updated_at")
    private LocalDateTime updatedAt;


    @PrePersist // antes de insertar en la base de datos
    public void prePersist(){
        System.out.println("Evento del ciclo de vida del entity pre persist");
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate // antes de insertar en la base de datos
    public void preUpdate(){
        System.out.println("Evento del ciclo de vida del entity pre update");
        this.updatedAt = LocalDateTime.now();
    }


    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
