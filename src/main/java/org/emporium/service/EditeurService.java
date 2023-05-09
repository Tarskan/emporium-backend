package org.emporium.service;

import org.emporium.model.Editeur;
import org.emporium.model.GenericCreateDTO;
import org.emporium.model.GenericModifyDTO;
import org.emporium.model.Genre;
import org.emporium.repository.EditeurRepository;
import org.emporium.repository.GenreRepository;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

@Singleton
@Service
public class EditeurService {
    @Inject
    EditeurRepository editeurRepository;

    public List<Editeur> getAllEditeur() {
        return editeurRepository.findAll();
    }

    public Editeur getByIdEditeur(String idEditeur) throws Exception {
        return editeurRepository.findById(idEditeur).orElseThrow(() -> new Exception("Genre not found."));
    }

    public Editeur addEditeur(GenericCreateDTO editeur) throws Exception {
        Editeur genreNew = Editeur.builder()
                .name(editeur.name)
                .build();

        return editeurRepository.save(genreNew);
    }

    public Editeur modifyEditeur(GenericModifyDTO editeur) {
        if (editeurRepository.existsById(editeur.getId())) {
            Editeur editeurModified = Editeur.builder()
                    .idEditeur(editeur.getId())
                    .name(editeur.getName())
                    .build();

            return editeurRepository.save(editeurModified);
        } else {
            throw new IllegalArgumentException("Id: " + editeur.getId() + " Non trouvée dans la bdd");
        }
    }

    public String suppEditeur(String idEditeur) throws Exception {
        Editeur editeurToDelete = editeurRepository.findById(idEditeur).orElseThrow(() -> new Exception("Id " + idEditeur + " n'existe pas ou a deja était supprimer"));
        editeurRepository.delete(editeurToDelete);
        return "L'editeur a était supprimer";
    }
}
