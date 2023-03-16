package org.emporium.repository;

import org.emporium.model.Collection;
import org.emporium.model.Utilisateur;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CollectionRepository extends CrudRepository<Collection, String> {
    List<Collection> findByUtilisateur(String utilisateur);

    List<Collection> findByBibliotheque(String bibliotheque);
}
