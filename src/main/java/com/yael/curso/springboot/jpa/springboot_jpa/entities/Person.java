package com.yael.curso.springboot.jpa.springboot_jpa.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
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
    private String lastname;

    @Column(name="programming_language")
    private String programmingLanguage;


    @Embedded // es como aplicar herencia, puedes tener otra clase separada y te dara sus metodos
    private Audit audit = new Audit();


    public Person(){} // utilizado por - jpa

    public Person(Long id, String name, String lastName, String programmingLanguage) {
        this.id = id;
        this.name = name;
        this.lastname = lastName;
        this.programmingLanguage = programmingLanguage;
    }

    public Person(String name, String lastName) {
        this.name = name;
        this.lastname = lastName;
    }




    public void setId(Long id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setLastName(String lastName) {
        this.lastname = lastName;
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
    public String getLastname() {
        return lastname;
    }
    public String getProgrammingLanguage() {
        return programmingLanguage;
    }

    @Override
    public String toString(){
        return "Person id=" + this.id + ", name=" + this.name + ", lastName=" + this.lastname+", language=" + this.programmingLanguage;
    }
}
