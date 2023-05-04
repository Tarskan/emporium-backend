package org.emporium.repository;

import org.emporium.model.Oeuvres;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OeuvresRepository extends CrudRepository<Oeuvres, String> {
    List<Oeuvres> findAll();

    @Query("from Oeuvres where idOeuvre = ?1")
    Oeuvres findByIdOeuvre(String idoeuvre);

    @Query("from Oeuvres where idType = ?1")
    List<Oeuvres> findByIdType(String idtype);

    @Query("from Oeuvres where idGenre = ?1")
    List<Oeuvres> findByIdGenre(String idgenre);

    @Query("from Oeuvres where idEditeur = ?1")
    List<Oeuvres> findByIdEditeur(String idediteur);

    @Query("from Oeuvres where idAuteur = ?1")
    List<Oeuvres> findByIdAuteur(String idauteur);

}
