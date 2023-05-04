package org.emporium.repository;

import org.emporium.model.Auteur;
import org.emporium.model.Oeuvres;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AuteurRepository extends CrudRepository<Auteur, String> {
    List<Auteur> findAll();

}
