package org.emporium.repository;

import org.emporium.model.Genre;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface GenreRepository extends CrudRepository<Genre, String> {
    List<Genre> findAll();

}
