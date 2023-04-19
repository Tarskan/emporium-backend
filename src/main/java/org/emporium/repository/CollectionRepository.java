package org.emporium.repository;

import org.emporium.model.Collection;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CollectionRepository extends CrudRepository<Collection, String> {
    List<Collection> findAll();
}
