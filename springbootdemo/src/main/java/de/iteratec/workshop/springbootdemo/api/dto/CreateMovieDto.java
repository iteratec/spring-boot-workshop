package de.iteratec.workshop.springbootdemo.api.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CreateMovieDto {

    @NotBlank
    private String title;

    private String genre;

    @Min(1900)
    @Max(2099)
    private Integer year;

    @NotNull
    private Integer directorId;

    @NotNull
    private Integer mainActorId;

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

    public Integer getDirectorId() {
        return directorId;
    }

    public void setDirectorId(Integer directorId) {
        this.directorId = directorId;
    }

    public Integer getMainActorId() {
        return mainActorId;
    }

    public void setMainActorId(Integer mainActorId) {
        this.mainActorId = mainActorId;
    }
}
