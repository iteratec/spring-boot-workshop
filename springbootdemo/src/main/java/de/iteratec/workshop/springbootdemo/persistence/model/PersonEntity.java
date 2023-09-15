package de.iteratec.workshop.springbootdemo.persistence.model;

import jakarta.persistence.*;

@Entity
@Table(name = "person", uniqueConstraints = @UniqueConstraint(columnNames = {"firstname", "lastname"}))
public class PersonEntity {
    @Id
    @GeneratedValue
    private Integer id;

    private String firstname;

    private String lastname;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

}
