package de.iteratec.workshop.springbootdemo.api;

import de.iteratec.workshop.springbootdemo.api.dto.CreatePersonDto;
import de.iteratec.workshop.springbootdemo.api.dto.PersonDto;
import de.iteratec.workshop.springbootdemo.persistence.PersonRepository;
import de.iteratec.workshop.springbootdemo.persistence.model.PersonEntity;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.ErrorResponseException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    private final PersonRepository personRepository;

    public PersonController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @PostMapping
    public PersonDto savePerson(@Validated @RequestBody CreatePersonDto dto) {
        PersonEntity person = new PersonEntity();
        person.setFirstname(dto.getFirstname());
        person.setLastname(dto.getLastname());

        personRepository.save(person);

        return mapPerson(person);
    }

    @GetMapping
    public List<PersonDto> getPersons(@RequestParam(required = false) String firstname,
                                      @RequestParam(required = false) String lastname) {
        List<PersonEntity> personList;
        if (firstname != null && lastname != null) {
            personList = new ArrayList<>();
            personRepository.findByFirstnameAndLastname(firstname, lastname).ifPresent(personList::add);
        } else if (firstname != null) {
            personList = personRepository.findByFirstnameStartsWithIgnoringCase(firstname);
        } else if (lastname != null) {
            personList = personRepository.findByLastnameStartsWithIgnoringCase(lastname);
        } else {
            personList = personRepository.findAll();
        }
        return personList.stream().map(this::mapPerson).toList();
    }

    @GetMapping("/{id}")
    public PersonDto getPerson(@PathVariable Integer id) {
        return personRepository.findById(id)
                .map(this::mapPerson)
                .orElseThrow(() -> createErrorResponseException(404, "No Person found for id=" + id));
    }

    private PersonDto mapPerson(PersonEntity person) {
        PersonDto dto = new PersonDto();
        dto.setId(person.getId());
        dto.setFirstname(person.getFirstname());
        dto.setLastname(person.getLastname());

        return dto;
    }

    private ErrorResponseException createErrorResponseException(int code, String detail) {
        HttpStatusCode statusCode = HttpStatusCode.valueOf(code);
        return new ErrorResponseException(statusCode, ProblemDetail.forStatusAndDetail(statusCode, detail), null);
    }
}
