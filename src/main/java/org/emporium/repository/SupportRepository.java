package org.emporium.repository;

import org.emporium.model.Support;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SupportRepository extends CrudRepository<Support, String> {
    List<Support> findAll();

    @Query("from Support where name like concat(?1, '%')")
    List<Support> findSupportTypeAutocomplete(String name);
}
