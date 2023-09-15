package de.iteratec.workshop.springbootdemo;

import de.iteratec.workshop.springbootdemo.persistence.MovieRepository;
import de.iteratec.workshop.springbootdemo.persistence.PersonRepository;
import de.iteratec.workshop.springbootdemo.persistence.model.MovieEntity;
import de.iteratec.workshop.springbootdemo.persistence.model.PersonEntity;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements ApplicationRunner {

    private final PersonRepository personRepository;
    private final MovieRepository movieRepository;

    public DataInitializer(PersonRepository personRepository, MovieRepository movieRepository) {
        this.personRepository = personRepository;
        this.movieRepository = movieRepository;
    }

    @Override
    public void run(ApplicationArguments args) {
        int cameron = savePerson("James", "Cameron");
        int russell = savePerson("Chuck", "Russell");
        int arni = savePerson("Arnold", "Schwarzenegger");
        int worthington = savePerson("Sam", "Worthington");
        int winslet = savePerson("Kate", "Winslet");
        int eastwood = savePerson("Clint", "Eastwood");
        saveMovie("Terminator", "Action", 1984, cameron, arni);
        saveMovie("Eraser", "Action", 1996, russell, arni);
        saveMovie("Avatar: Aufbruch nach Pandora", "Science Fiction", 2009, cameron, worthington);
        saveMovie("Titanic", "Drama", 1997, cameron, winslet);
        saveMovie("Erbarmungslos", "Western", 1992, eastwood, eastwood);
    }

    private int savePerson(String firstname, String lastname) {
        PersonEntity person = new PersonEntity();
        person.setFirstname(firstname);
        person.setLastname(lastname);
        personRepository.save(person);

        return person.getId();
    }

    private void saveMovie(String title, String genre, int year, Integer directorId, Integer mainActorId) {
        MovieEntity movie = new MovieEntity();
        movie.setTitle(title);
        movie.setGenre(genre);
        movie.setYear(year);
        personRepository.findById(directorId).ifPresent(movie::setDirector);
        personRepository.findById(mainActorId).ifPresent(movie::setMainActor);
        movieRepository.save(movie);
    }
}
