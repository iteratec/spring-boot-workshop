package de.iteratec.workshop.springbootdemo.persistence;

import de.iteratec.workshop.springbootdemo.persistence.model.MovieEntity;
import de.iteratec.workshop.springbootdemo.persistence.model.PersonEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class MovieRepositoryTest {

    @Autowired
    PersonRepository personRepository;

    @Autowired
    MovieRepository movieRepository;

    @Test
    void test_saveAndGet() {
        // given
        PersonEntity cameron = new PersonEntity();
        cameron.setFirstname("James");
        cameron.setLastname("Cameron");
        personRepository.save(cameron);

        PersonEntity arni = new PersonEntity();
        arni.setFirstname("Arnold");
        arni.setLastname("Schwarzenegger");
        personRepository.save(arni);

        MovieEntity movie = new MovieEntity();
        movie.setTitle("Terminator");
        movie.setGenre("Action");
        movie.setYear(1984);
        movie.setDirector(cameron);
        movie.setMainActor(arni);

        // when
        movieRepository.saveAndFlush(movie);

        // then
        assertThat(movie.getId()).isNotNull();

        // when
        Optional<MovieEntity> reloaded = movieRepository.findById(movie.getId());

        // then
        assertThat(reloaded).isNotEmpty();
        assertThat(reloaded.get().getMainActor().getFirstname()).isEqualTo("Arnold");
    }

}