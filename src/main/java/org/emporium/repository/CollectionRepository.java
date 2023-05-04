package org.emporium.repository;

import org.emporium.model.Collection;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CollectionRepository extends CrudRepository<Collection, String> {
    List<Collection> findAll();

    @Query("from Collection where IdCollection = ?1")
    Collection findByIdCollection(String idCollection);

    @Query("from Collection where UWUid = ?1")
    List<Collection> findByUWUid(String uwuid);

    @Query("from Collection where IdOeuvre = ?1")
    List<Collection> findByIdOeuvre(String idOeuvre);

}
