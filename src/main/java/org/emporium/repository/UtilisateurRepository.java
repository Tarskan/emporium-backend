package org.emporium.repository;

import org.emporium.model.Utilisateur;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UtilisateurRepository extends CrudRepository<Utilisateur, String> {
    @Query("from Utilisateur order by modificationdate ASC")
    List<Utilisateur> findAllSorted();

    @Query("from Utilisateur where UWUid = ?1")
    Utilisateur findByUWUid(String uwuid);

    @Query("from Utilisateur where pseudo = ?1")
    Utilisateur findByPseudo(String pseudo);

    @Query("from Utilisateur where pseudo like concat(?1, '%')")
    List<Utilisateur> findByPseudoLike(String pseudo);

}
