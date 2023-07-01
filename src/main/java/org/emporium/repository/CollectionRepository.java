package org.emporium.repository;

import org.emporium.model.Collection;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CollectionRepository extends CrudRepository<Collection, String> {
    List<Collection> findAll();

    @Query("from Collection where UWUid = ?1")
    List<Collection> findByUWUid(String uwuid);

    @Query("from Collection where idOeuvre = ?1")
    List<Collection> findByIdOeuvre(String idOeuvre);

    @Query("from Collection where idOeuvre = ?1 AND UWUid = ?2")
    Collection findIfPosses(String idOeuvre, String uwuid);

    @Query("from Collection where UWUid = ?1 and favorite=?2")
    List<Collection> findByFavorisUser(String uwuid, Boolean favorite);

}
