package de.iteratec.workshop.springbootdemo.persistence.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "person", uniqueConstraints = @UniqueConstraint(columnNames = {"firstname", "lastname"}))
public class PersonEntity {
    @Id
    @GeneratedValue
    private Integer id;

    private String firstname;

    private String lastname;

    @OneToMany(mappedBy = "director")
    private List<MovieEntity> moviesAsDirector;

    @OneToMany(mappedBy = "mainActor")
    private List<MovieEntity> moviesAsMainActor;

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

    public List<MovieEntity> getMoviesAsDirector() {
        return moviesAsDirector;
    }

    public void setMoviesAsDirector(List<MovieEntity> moviesAsDirector) {
        this.moviesAsDirector = moviesAsDirector;
    }

    public List<MovieEntity> getMoviesAsMainActor() {
        return moviesAsMainActor;
    }

    public void setMoviesAsMainActor(List<MovieEntity> moviesAsMainActor) {
        this.moviesAsMainActor = moviesAsMainActor;
    }
}
