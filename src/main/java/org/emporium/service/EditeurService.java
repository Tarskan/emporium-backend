package org.emporium.service;

import org.emporium.model.Editeur;
import org.emporium.model.GenericCreateDTO;
import org.emporium.model.GenericModifyDTO;
import org.emporium.repository.EditeurRepository;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;
import java.util.Objects;

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

    public List<Editeur> getEditeurAutocomplete(String name) {
        return editeurRepository.findEditeurAutocomplete(name);
    }

    public Editeur addEditeur(GenericCreateDTO editeur) throws Exception {
        if(!Objects.equals(editeurRepository.findByName(editeur.getName()).getName(), editeur.getName())) {
            Editeur genreNew = Editeur.builder()
                    .name(editeur.name)
                    .build();

            return editeurRepository.save(genreNew);
        } else {
            throw new IllegalArgumentException("Name: " + editeur.getName() + " en doublon dans la bdd");
        }
    }

    public Editeur modifyEditeur(GenericModifyDTO editeur) {
        if (editeurRepository.existsById(editeur.getId())) {
            if(!Objects.equals(editeurRepository.findByName(editeur.getName()).getName(), editeur.getName())) {
                Editeur editeurModified = Editeur.builder()
                        .idEditeur(editeur.getId())
                        .name(editeur.getName())
                        .build();

                return editeurRepository.save(editeurModified);
            } else {
                throw new IllegalArgumentException("Name: " + editeur.getName() + " en doublon dans la bdd");
            }
        } else {
            throw new IllegalArgumentException("Id: " + editeur.getId() + " Non trouvée dans la bdd");
        }
    }

    public String suppEditeur(String idEditeur) throws Exception {
        if (editeurRepository.existsById(idEditeur)) {
            Editeur editeurToDelete = editeurRepository.findById(idEditeur).orElseThrow(() -> new Exception("Id " + idEditeur + " n'existe pas ou a deja était supprimer"));
            editeurRepository.delete(editeurToDelete);
            return "L'editeur a était supprimer";
        } else {
            throw new IllegalArgumentException("Id: " + idEditeur + " Non trouvée dans la bdd");
        }
    }
}
