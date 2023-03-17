package org.emporium.repository;

import org.emporium.model.Bibliotheque;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BibliothequeRepository extends CrudRepository<Bibliotheque, String> {
    List<Bibliotheque> findAll();
}
