package org.emporium.repository;

import org.emporium.model.Bibliotheque;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BibliothequeRepository extends CrudRepository<Bibliotheque, String> {
    List<Bibliotheque> findAll();
}
