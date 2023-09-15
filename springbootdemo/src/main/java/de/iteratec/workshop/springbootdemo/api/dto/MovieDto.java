package de.iteratec.workshop.springbootdemo.api.dto;

public class MovieDto {

    private Integer id;

    private String title;

    private String genre;

    private Integer year;

    private PersonDto director;

    private PersonDto mainActor;

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

    public PersonDto getDirector() {
        return director;
    }

    public void setDirector(PersonDto director) {
        this.director = director;
    }

    public PersonDto getMainActor() {
        return mainActor;
    }

    public void setMainActor(PersonDto mainActor) {
        this.mainActor = mainActor;
    }
}
