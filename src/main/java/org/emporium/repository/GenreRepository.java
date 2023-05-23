package org.emporium.repository;

import org.emporium.model.Auteur;
import org.emporium.model.Genre;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface GenreRepository extends CrudRepository<Genre, String> {
    List<Genre> findAll();

    @Query("from Genre where name like concat(?1, '%')")
    List<Genre> findGenreAutocomplete(String name);

    @Query("from Genre where name = ?1")
    Genre findByName(String name);
}
