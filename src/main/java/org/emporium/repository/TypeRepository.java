package org.emporium.repository;

import org.emporium.model.Type;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TypeRepository extends CrudRepository<Type, String> {
    List<Type> findAll();

}
