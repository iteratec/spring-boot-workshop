package de.iteratec.workshop.springbootdemo.persistence;

import de.iteratec.workshop.springbootdemo.persistence.model.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PersonRepository extends JpaRepository<PersonEntity, Integer> {

    List<PersonEntity> findByFirstnameStartsWithIgnoringCase(String firstname);

    List<PersonEntity> findByLastnameStartsWithIgnoringCase(String lastname);

    Optional<PersonEntity> findByFirstnameAndLastname(String firstname, String lastname);
}
