package org.emporium.repository;

import org.emporium.model.Type;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TypeRepository extends CrudRepository<Type, String> {
    List<Type> findAll();

    @Query("from Type where lower(name) like concat('%', lower(?1), '%')")
    List<Type> findTypeAutocomplete(String name);

    @Query("from Type where lower(name) = ?1")
    Type findByName(String name);

    @Query("from Type where idOuevre = ?1")
    Type findByIdOeuvre(String idoeuvre);
}
