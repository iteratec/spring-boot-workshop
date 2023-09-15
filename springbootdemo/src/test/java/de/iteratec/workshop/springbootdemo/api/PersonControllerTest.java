package de.iteratec.workshop.springbootdemo.api;

import de.iteratec.workshop.springbootdemo.api.dto.CreatePersonDto;
import de.iteratec.workshop.springbootdemo.api.dto.PersonDto;
import de.iteratec.workshop.springbootdemo.persistence.PersonRepository;
import de.iteratec.workshop.springbootdemo.persistence.model.PersonEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringJUnitConfig(PersonController.class)
class PersonControllerTest {

    @Autowired
    PersonController personController;

    @MockBean
    PersonRepository personRepositoryMock;

    @Test
    void savePerson() {
        // given
        CreatePersonDto requestDto = new CreatePersonDto();
        requestDto.setFirstname("A");
        requestDto.setLastname("B");

        when(personRepositoryMock.save(any())).thenAnswer(i -> {
            PersonEntity personToSave = i.getArgument(0);
            personToSave.setId(666);
            return personToSave;
        });

        // when
        PersonDto result = personController.savePerson(requestDto);

        // then
        verify(personRepositoryMock).save(argThat(p -> p.getFirstname().equals("A") && p.getLastname().equals("B")));
        assertThat(result.getId()).isEqualTo(666);
    }
}
