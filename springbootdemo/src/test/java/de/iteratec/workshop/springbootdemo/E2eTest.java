package de.iteratec.workshop.springbootdemo;

import de.iteratec.workshop.springbootdemo.api.dto.CreatePersonDto;
import de.iteratec.workshop.springbootdemo.api.dto.PersonDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class E2eTest {

    @Autowired
    TestRestTemplate restTemplate;

    @Test
    void postAndGetPerson() {
        // given
        CreatePersonDto person = new CreatePersonDto();
        person.setFirstname("Hans");
        person.setLastname("Dampf");

        // when
        ResponseEntity<PersonDto> responseForPost = restTemplate.postForEntity("/person", person, PersonDto.class);

        // then
        assertThat(responseForPost.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(200));

        // given
        Integer id = responseForPost.getBody().getId();

        // when
        ResponseEntity<PersonDto> responseForGet = restTemplate.getForEntity("/person/{id}", PersonDto.class, id);

        // then
        assertThat(responseForGet.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(200));
        assertThat(responseForGet.getBody().getFirstname()).isEqualTo("Hans");
        assertThat(responseForGet.getBody().getLastname()).isEqualTo("Dampf");
    }

}
