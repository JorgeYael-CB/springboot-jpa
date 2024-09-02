package com.yael.curso.springboot.jpa.springboot_jpa.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;



@Entity
@Table(name="persons")
public class Person {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String lastName;
    @Column(name="programming_language")
    private String programmingLanguage;


    public Person(){} // utilizado por - jpa

    public Person(Long id, String name, String lastName, String programmingLanguage) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.programmingLanguage = programmingLanguage;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public void setProgrammingLanguage(String programmingLanguage) {
        this.programmingLanguage = programmingLanguage;
    }
    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getLastName() {
        return lastName;
    }
    public String getProgrammingLanguage() {
        return programmingLanguage;
    }

    @Override
    public String toString(){
        return "Perdon id=" + this.id + ", name=" + this.name + ", lastName=" + this.lastName+", language=" + this.programmingLanguage;
    }
}
