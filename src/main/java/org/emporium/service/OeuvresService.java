package org.emporium.service;

import org.emporium.model.Oeuvres;
import org.emporium.model.OeuvresCreateDTO;
import org.emporium.model.OeuvresModifyDTO;
import org.emporium.repository.*;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;


@Singleton
@Service
public class OeuvresService {

    @Inject
    OeuvresRepository oeuvresRepository;

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
        return oeuvresRepository.findByIdOeuvre(idOeuvre);
    }

    public List<Oeuvres> getByIdGenre(String idGenre) {
        return oeuvresRepository.findByIdGenre(idGenre);
    }

    public List<Oeuvres> getByIdEditeur(String idEditeur) {
        return oeuvresRepository.findByIdEditeur(idEditeur);
    }

    public List<Oeuvres> getByIdAuteur(String idAuteur) {
        return oeuvresRepository.findByIdAuteur(idAuteur);
    }

    public List<Oeuvres> getByIdType(String idType) {
        return oeuvresRepository.findByIdType(idType);
    }

    public List<Oeuvres> getByTitreAutocomplete(String titre) {
        return oeuvresRepository.findByTitreAutoComplete(titre);
    }

    public Oeuvres addOeuvre(OeuvresCreateDTO oeuvres) throws Exception {
        Oeuvres oeuvresNew =  Oeuvres.builder()
                .titre(oeuvres.titre)
                .sousTitre(oeuvres.sousTitre)
                .description(oeuvres.description)
                .image(oeuvres.image)
                .type(typeRepository.findById(oeuvres.getIdType()).orElseThrow(() -> new Exception("Type not found.")))
                .auteur(auteurRepository.findById(oeuvres.getIdAuteur()).orElseThrow(() -> new Exception("Auteur not found.")))
                .genre(genreRepository.findById(oeuvres.getIdGenre()).orElseThrow(() -> new Exception("Genre not found.")))
                .editeur(editeurRepository.findById(oeuvres.getIdEditeur()).orElseThrow(() -> new Exception("Editeur not found.")))
                .support(supportRepository.findById(oeuvres.getIdSupport()).orElseThrow(() -> new Exception("Support not found.")))
                .build();

        return oeuvresRepository.save(oeuvresNew);
    }

    public Oeuvres modifyOeuvre(OeuvresModifyDTO oeuvres) throws Exception {
        if (oeuvresRepository.existsById(oeuvres.getIdOeuvre())) {
            Oeuvres oeuvresModified =  Oeuvres.builder()
                    .idOeuvre(oeuvres.idOeuvre)
                    .titre(oeuvres.titre)
                    .sousTitre(oeuvres.sousTitre)
                    .description(oeuvres.description)
                    .image(oeuvres.image)
                    .type(typeRepository.findById(oeuvres.getIdType()).orElseThrow(() -> new Exception("Type not found.")))
                    .auteur(auteurRepository.findById(oeuvres.getIdAuteur()).orElseThrow(() -> new Exception("Auteur not found.")))
                    .genre(genreRepository.findById(oeuvres.getIdGenre()).orElseThrow(() -> new Exception("Genre not found.")))
                    .editeur(editeurRepository.findById(oeuvres.getIdEditeur()).orElseThrow(() -> new Exception("Editeur not found.")))
                    .support(supportRepository.findById(oeuvres.getIdSupport()).orElseThrow(() -> new Exception("Support not found.")))
                    .build();

            return oeuvresRepository.save(oeuvresModified);
        } else {
            throw new IllegalArgumentException("Id: " + oeuvres.getIdOeuvre() + " Non trouvée dans la bdd");
        }
    }

    public String suppOeuvre(String IdOeuvre) throws Exception {
        Oeuvres oeuvreToDelete = oeuvresRepository.findById(IdOeuvre).orElseThrow(() -> new Exception("Id " + IdOeuvre + " n'existe pas ou a deja était supprimer"));
        oeuvresRepository.delete(oeuvreToDelete);
        return "L'oeuvre a était supprimer";
    }

}
