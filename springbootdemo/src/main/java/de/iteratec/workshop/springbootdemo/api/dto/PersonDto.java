package de.iteratec.workshop.springbootdemo.api.dto;

import java.util.List;

public class PersonDto {

    private Integer id;

    private String firstname;

    private String lastname;

    private List<MovieDto> moviesAsDirector;

    private List<MovieDto> moviesAsMainActor;

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

    public List<MovieDto> getMoviesAsDirector() {
        return moviesAsDirector;
    }

    public void setMoviesAsDirector(List<MovieDto> moviesAsDirector) {
        this.moviesAsDirector = moviesAsDirector;
    }

    public List<MovieDto> getMoviesAsMainActor() {
        return moviesAsMainActor;
    }

    public void setMoviesAsMainActor(List<MovieDto> moviesAsMainActor) {
        this.moviesAsMainActor = moviesAsMainActor;
    }
}
