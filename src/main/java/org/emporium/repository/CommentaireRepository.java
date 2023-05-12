package org.emporium.repository;

import org.emporium.model.Commentaire;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CommentaireRepository extends CrudRepository<Commentaire, String> {
    List<Commentaire> findAll();

    @Query("from Commentaire where UWUid = ?1")
    List<Commentaire> findByUWUid(String uwuid);

    @Query("from Commentaire where idOeuvre = ?1")
    List<Commentaire> findByIdOeuvre(String idOeuvre);

}
