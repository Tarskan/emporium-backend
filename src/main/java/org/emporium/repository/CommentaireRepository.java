package org.emporium.repository;

import org.emporium.model.Commentaire;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CommentaireRepository extends CrudRepository<Commentaire, String> {
    @Query("from Commentaire order by modificationdate ASC")
    List<Commentaire> findAllSorted();

    @Query("from Commentaire order by modificationdate DESC")
    List<Commentaire> findLastSorted();

    @Query("from Commentaire where UWUid = ?1 ORDER BY modificationdate ASC")
    List<Commentaire> findByUWUid(String uwuid);

    @Query("from Commentaire where idOeuvre = ?1 ORDER BY modificationdate ASC")
    List<Commentaire> findByIdOeuvre(String idOeuvre);

}
