package org.emporium.repository;

import org.emporium.model.Oeuvres;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface OeuvresRepository extends CrudRepository<Oeuvres, String> {

    @Query("from Oeuvres order by modificationdate ASC")
    List<Oeuvres> findAllSorted();

    @Query("from Oeuvres order by modificationdate DESC")
    List<Oeuvres> findLastModified();

    @Query("from Oeuvres where idOeuvre = ?1")
    Oeuvres findByIdOeuvre(String idoeuvre);

    @Query("from Oeuvres where idType = ?1 order by modificationdate DESC")
    List<Oeuvres> findByIdType(String idtype);

    @Query("from Oeuvres where idGenre = ?1 order by modificationdate ASC")
    List<Oeuvres> findByIdGenre(String idgenre);

    @Query("from Oeuvres where idEditeur = ?1 order by modificationdate ASC")
    List<Oeuvres> findByIdEditeur(String idediteur);

    @Query("from Oeuvres where idAuteur = ?1 order by modificationdate ASC")
    List<Oeuvres> findByIdAuteur(String idauteur);

    @Query("from Oeuvres where idSupport = ?1 order by modificationdate ASC")
    List<Oeuvres> findByIdSupport(String idsupport);

    @Query("from Oeuvres where lower(titre) like concat('%', lower(?1), '%')")
    List<Oeuvres> findByTitreAutoComplete(String titre);

    @Query("from Oeuvres where lower(titre) = lower(?1)")
    Oeuvres findByTitre(String titre);

    @Query("from Oeuvres where idAuteur = ?1")
    List<Oeuvres> findRelatedToOeuvresFromAuteur(String idauteur);

}
