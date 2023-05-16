package org.emporium.service;

import org.emporium.model.Commentaire;
import org.emporium.model.Oeuvres;
import org.emporium.model.OeuvresCreateDTO;
import org.emporium.model.OeuvresModifyDTO;
import org.emporium.repository.*;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Date;
import java.util.List;
import java.util.Objects;


@Singleton
@Service
public class OeuvresService {

    @Inject
    OeuvresRepository oeuvresRepository;

    @Inject
    CommentaireRepository commentaireRepository;

    @Inject
    TypeRepository typeRepository;

    @Inject
    AuteurRepository auteurRepository;

    @Inject
    EditeurRepository editeurRepository;

    @Inject
    GenreRepository genreRepository;

    @Inject
    SupportRepository supportRepository;

    public List<Oeuvres> getAllOeuvres() {
        return oeuvresRepository.findAll();
    }

    public Oeuvres getByIdOeuvre(String idOeuvre) {
        if (oeuvresRepository.existsById(idOeuvre)) {
            return oeuvresRepository.findByIdOeuvre(idOeuvre);
        } else {
            throw new IllegalArgumentException("Id: " + idOeuvre + " Non trouvée dans la bdd");
        }
    }

    public List<Oeuvres> getByIdGenre(String idGenre) {
        if (genreRepository.existsById(idGenre)) {
            return oeuvresRepository.findByIdGenre(idGenre);
        } else {
            throw new IllegalArgumentException("Id: " + idGenre + " Non trouvée dans la bdd");
        }
    }

    public List<Oeuvres> getByIdEditeur(String idEditeur) {
        if (editeurRepository.existsById(idEditeur)) {
            return oeuvresRepository.findByIdEditeur(idEditeur);
        } else {
            throw new IllegalArgumentException("Id: " + idEditeur + " Non trouvée dans la bdd");
        }
    }

    public List<Oeuvres> getByIdAuteur(String idAuteur) {
        if (auteurRepository.existsById(idAuteur)) {
            return oeuvresRepository.findByIdAuteur(idAuteur);
        } else {
            throw new IllegalArgumentException("Id: " + idAuteur + " Non trouvée dans la bdd");
        }
    }

    public List<Oeuvres> getByIdType(String idType) {
        if (typeRepository.existsById(idType)) {
            return oeuvresRepository.findByIdType(idType);
        } else {
            throw new IllegalArgumentException("Id: " + idType + " Non trouvée dans la bdd");
        }
    }

    public List<Oeuvres> getByIdSupport(String idSupport) {
        if (supportRepository.existsById(idSupport)) {
            return oeuvresRepository.findByIdSupport(idSupport);
        } else {
            throw new IllegalArgumentException("Id: " + idSupport + " Non trouvée dans la bdd");
        }
    }

    public List<Oeuvres> getByTitreAutocomplete(String titre) {
        return oeuvresRepository.findByTitreAutoComplete(titre);
    }

    public Oeuvres addOeuvre(OeuvresCreateDTO oeuvres) throws Exception {
            Date myDate = new Date();
            Oeuvres oeuvresNew = Oeuvres.builder()
                    .titre(oeuvres.titre)
                    .sousTitre(oeuvres.sousTitre)
                    .description(oeuvres.description)
                    .image(oeuvres.image)
                    .type(typeRepository.findById(oeuvres.getIdType()).orElseThrow(() -> new Exception("Type not found.")))
                    .auteur(auteurRepository.findById(oeuvres.getIdAuteur()).orElseThrow(() -> new Exception("Auteur not found.")))
                    .genre(genreRepository.findById(oeuvres.getIdGenre()).orElseThrow(() -> new Exception("Genre not found.")))
                    .editeur(editeurRepository.findById(oeuvres.getIdEditeur()).orElseThrow(() -> new Exception("Editeur not found.")))
                    .support(supportRepository.findById(oeuvres.getIdSupport()).orElseThrow(() -> new Exception("Support not found.")))
                    .creationDate(myDate)
                    .modificationDate(null)
                    .build();

            return oeuvresRepository.save(oeuvresNew);
    }

    public Oeuvres modifyOeuvre(OeuvresModifyDTO oeuvres) throws Exception {
        if (oeuvresRepository.existsById(oeuvres.getIdOeuvre())) {
                Date myDate = new Date();
                Oeuvres oeuvresOld = oeuvresRepository.findByIdOeuvre(oeuvres.getIdOeuvre());

                Oeuvres oeuvresModified =  Oeuvres.builder()
                        .idOeuvre(oeuvres.getIdOeuvre())
                        .titre(oeuvres.getTitre())
                        .sousTitre(oeuvres.getSousTitre())
                        .description(oeuvres.getDescription())
                        .image(oeuvres.getImage())
                        .type(typeRepository.findById(oeuvres.getIdType()).orElseThrow(() -> new Exception("Type not found.")))
                        .auteur(auteurRepository.findById(oeuvres.getIdAuteur()).orElseThrow(() -> new Exception("Auteur not found.")))
                        .genre(genreRepository.findById(oeuvres.getIdGenre()).orElseThrow(() -> new Exception("Genre not found.")))
                        .editeur(editeurRepository.findById(oeuvres.getIdEditeur()).orElseThrow(() -> new Exception("Editeur not found.")))
                        .support(supportRepository.findById(oeuvres.getIdSupport()).orElseThrow(() -> new Exception("Support not found.")))
                        .creationDate(oeuvresOld.getCreationDate())
                        .modificationDate(myDate)
                        .build();

                return oeuvresRepository.save(oeuvresModified);
        } else {
            throw new IllegalArgumentException("Id: " + oeuvres.getIdOeuvre() + " Non trouvée dans la bdd");
        }
    }

    public String suppOeuvre(String IdOeuvre) throws Exception {
        if (oeuvresRepository.existsById(IdOeuvre)) {
            Oeuvres oeuvreToDelete = oeuvresRepository.findById(IdOeuvre).orElseThrow(() -> new Exception("Id " + IdOeuvre + " n'existe pas ou a deja était supprimer"));
            List<Commentaire> comUser = commentaireRepository.findByIdOeuvre(IdOeuvre);
            commentaireRepository.deleteAll(comUser);
            oeuvresRepository.delete(oeuvreToDelete);
            return "L'oeuvre a était supprimer";
        } else {
            throw new IllegalArgumentException("Id: " + IdOeuvre + " Non trouvée dans la bdd");
        }
    }

}
