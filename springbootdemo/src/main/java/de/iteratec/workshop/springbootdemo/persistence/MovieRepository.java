package de.iteratec.workshop.springbootdemo.persistence;

import de.iteratec.workshop.springbootdemo.persistence.model.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieRepository extends JpaRepository<MovieEntity, Integer> {

    List<MovieEntity> findByTitleContaining(String partialTitle);

    List<MovieEntity> findByDirectorId(Integer id);
}
