package org.emporium.repository;

import org.emporium.model.Auteur;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AuteurRepository extends CrudRepository<Auteur, String> {
    List<Auteur> findAll();

    @Query("from Auteur where lower(name) like concat(%, lower(?1), '%')")
    List<Auteur> findAuteurAutocomplete(String name);

    @Query("from Auteur where lower(name) = ?1")
    Auteur findByName(String name);
}
