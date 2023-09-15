package de.iteratec.workshop.springbootdemo.api;

import de.iteratec.workshop.springbootdemo.api.dto.CreateMovieDto;
import de.iteratec.workshop.springbootdemo.api.dto.MovieDto;
import de.iteratec.workshop.springbootdemo.api.dto.PersonDto;
import de.iteratec.workshop.springbootdemo.persistence.MovieRepository;
import de.iteratec.workshop.springbootdemo.persistence.PersonRepository;
import de.iteratec.workshop.springbootdemo.persistence.model.MovieEntity;
import de.iteratec.workshop.springbootdemo.persistence.model.PersonEntity;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.ErrorResponseException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("movie")
public class MovieController {

    private final MovieRepository movieRepository;
    private final PersonRepository personRepository;

    public MovieController(MovieRepository movieRepository, PersonRepository personRepository) {
        this.movieRepository = movieRepository;
        this.personRepository = personRepository;
    }

    @GetMapping
    public List<MovieDto> getMovies(@RequestParam(required = false) String partialTitle,
                                    @RequestParam(required = false) Integer directorId) {
        if (partialTitle != null && directorId != null) {
            throw createErrorResponseException(404, "partialTitle and directorId may not be set both");
        }
        List<MovieEntity> results;
        if (partialTitle != null) {
            results = movieRepository.findByTitleContaining(partialTitle);
        } else if (directorId != null) {
            results = movieRepository.findByDirectorId(directorId);
        } else {
            results = movieRepository.findAll();
        }
        return results.stream()
                .map(this::mapMovie)
                .toList();
    }

    @GetMapping("/{id}")
    public MovieDto getMovie(@PathVariable Integer id) {
        return movieRepository.findById(id)
                .map(this::mapMovie)
                .orElseThrow(() -> createErrorResponseException(404, "No Movie found for id=" + id));
    }

    @PostMapping
    public MovieDto saveMovie(@Validated @RequestBody CreateMovieDto dto) {
        MovieEntity movie = new MovieEntity();
        movie.setTitle(dto.getTitle());
        movie.setGenre(dto.getGenre());
        movie.setYear(dto.getYear());

        personRepository.findById(dto.getDirectorId()).ifPresent(movie::setDirector);
        personRepository.findById(dto.getMainActorId()).ifPresent(movie::setMainActor);

        movieRepository.save(movie);

        return mapMovie(movie);
    }

    private MovieDto mapMovie(MovieEntity movie) {
        MovieDto dto = new MovieDto();
        dto.setId(movie.getId());
        dto.setTitle(movie.getTitle());
        dto.setGenre(movie.getGenre());
        dto.setYear(movie.getYear());
        dto.setDirector(mapPerson(movie.getDirector()));
        dto.setMainActor(mapPerson(movie.getMainActor()));

        return dto;
    }

    private PersonDto mapPerson(PersonEntity person) {
        if (person == null) {
            return null;
        }
        PersonDto dto = new PersonDto();
        dto.setId(person.getId());
        dto.setFirstname(person.getFirstname());
        dto.setLastname(person.getLastname());

        return dto;
    }

    private ErrorResponseException createNotFoundException(Integer id) {
        HttpStatusCode statusCode = HttpStatusCode.valueOf(404);
        return new ErrorResponseException(statusCode,
                ProblemDetail.forStatusAndDetail(statusCode, "No Movie found for id=" + id),
                null);
    }

    private ErrorResponseException createErrorResponseException(int code, String detail) {
        HttpStatusCode statusCode = HttpStatusCode.valueOf(code);
        return new ErrorResponseException(statusCode, ProblemDetail.forStatusAndDetail(statusCode, detail), null);
    }
}
