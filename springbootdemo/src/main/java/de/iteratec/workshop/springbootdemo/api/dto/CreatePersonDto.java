package de.iteratec.workshop.springbootdemo.api.dto;

import jakarta.validation.constraints.NotBlank;

public class CreatePersonDto {

    @NotBlank
    private String firstname;

    @NotBlank
    private String lastname;

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
