package org.emporium.repository;

import org.emporium.model.Editeur;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EditeurRepository extends CrudRepository<Editeur, String> {
    List<Editeur> findAll();

}
