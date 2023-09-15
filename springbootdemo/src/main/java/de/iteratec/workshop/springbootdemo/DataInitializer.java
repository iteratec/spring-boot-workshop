package de.iteratec.workshop.springbootdemo;

import de.iteratec.workshop.springbootdemo.persistence.PersonRepository;
import de.iteratec.workshop.springbootdemo.persistence.model.PersonEntity;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements ApplicationRunner {

    private final PersonRepository personRepository;

    public DataInitializer(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public void run(ApplicationArguments args) {
        savePerson("James", "Cameron");
        savePerson("Chuck", "Russell");
        savePerson("Arnold", "Schwarzenegger");
        savePerson("Sam", "Worthington");
        savePerson("Kate", "Winslet");
        savePerson("Clint", "Eastwood");
    }

    private void savePerson(String firstname, String lastname) {
        PersonEntity person = new PersonEntity();
        person.setFirstname(firstname);
        person.setLastname(lastname);
        personRepository.save(person);
    }

}
