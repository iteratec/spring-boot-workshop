package de.iteratec.workshop.springbootdemo.persistence;

import de.iteratec.workshop.springbootdemo.persistence.model.PersonEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class PersonRepositoryTest {

    @Autowired
    PersonRepository personRepository;

    @Test
    void test_saveAndGet() {
        // given
        PersonEntity person = new PersonEntity();
        person.setFirstname("Arnold");
        person.setLastname("Schwarzenegger");

        // when
        personRepository.saveAndFlush(person);

        // then
        assertThat(person.getId()).isNotNull();

        // when
        Optional<PersonEntity> reloaded = personRepository.findById(person.getId());

        // then
        assertThat(reloaded).isNotEmpty();
        assertThat(reloaded.get().getFirstname()).isEqualTo(person.getFirstname());
        assertThat(reloaded.get().getLastname()).isEqualTo(person.getLastname());
    }

}