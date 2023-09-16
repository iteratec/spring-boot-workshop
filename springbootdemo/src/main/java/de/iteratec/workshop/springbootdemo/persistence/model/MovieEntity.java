package de.iteratec.workshop.springbootdemo.persistence.model;

import jakarta.persistence.*;

@Entity
@Table(name = "movie")
public class MovieEntity {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(unique = true)
    private String title;

    private String genre;

    private Integer year;

    @ManyToOne
    @JoinColumn(name = "director_id")
    private PersonEntity director;

    @ManyToOne
    @JoinColumn(name = "main_actor_id")
    private PersonEntity mainActor;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public void setDirector(PersonEntity director) {
        this.director = director;
    }

    public void setMainActor(PersonEntity mainActor) {
        this.mainActor = mainActor;
    }

    public PersonEntity getDirector() {
        return director;
    }

    public PersonEntity getMainActor() {
        return mainActor;
    }
}
