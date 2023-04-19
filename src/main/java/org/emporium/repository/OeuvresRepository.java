package org.emporium.repository;

import org.emporium.model.Collection;
import org.emporium.model.Oeuvres;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OeuvresRepository extends CrudRepository<Oeuvres, String> {
    List<Oeuvres> findAll();

}
