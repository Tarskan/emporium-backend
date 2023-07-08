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

    @Query("from Utilisateur where email = ?1")
    Utilisateur findByEmail(String email);

    @Query("from Utilisateur where email = ?1")
    Utilisateur findByMail(String email);

    @Query("from Utilisateur where pseudo = ?1")
    Utilisateur findByPseudo(String pseudo);

    @Query("from Utilisateur where authId = ?1")
    Utilisateur findByAuthId(String authId);

    @Query("from Utilisateur where lower(pseudo) like concat('%', lower(?1), '%')")
    List<Utilisateur> findByPseudoLike(String pseudo);

}
