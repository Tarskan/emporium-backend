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

    public Oeuvres getByIdOeuvre(String IdOeuvre) {
        return oeuvresRepository.findByIdOeuvre(IdOeuvre);
    }

    public List<Oeuvres> getByIdGenre(String IdGenre) {
        return oeuvresRepository.findByIdGenre(IdGenre);
    }

    public List<Oeuvres> getByIdEditeur(String IdEditeur) {
        return oeuvresRepository.findByIdEditeur(IdEditeur);
    }

    public List<Oeuvres> getByIdAuteur(String IdAuteur) {
        return oeuvresRepository.findByIdAuteur(IdAuteur);
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

    public String suppOeuvre(String IdOeuvre) {
        if (oeuvresRepository.existsById(IdOeuvre)) {
            Oeuvres oeuvreToDelete = oeuvresRepository.findByIdOeuvre(IdOeuvre);
            oeuvresRepository.delete(oeuvreToDelete);
            return "L'oeuvre a était supprimer";
        } else {
            return "Id " + IdOeuvre + " n'existe pas ou a deja était supprimer";
        }
    }

}
