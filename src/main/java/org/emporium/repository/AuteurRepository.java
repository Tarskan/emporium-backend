package org.emporium.repository;

import org.emporium.model.Auteur;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AuteurRepository extends CrudRepository<Auteur, String> {
    List<Auteur> findAll();

    @Query("from Auteur where name like concat(?1, '%')")
    List<Auteur> findAuteurAutocomplete(String name);
}
