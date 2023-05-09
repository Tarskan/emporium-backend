package org.emporium.service;

import org.emporium.model.Auteur;
import org.emporium.model.GenericCreateDTO;
import org.emporium.model.GenericModifyDTO;
import org.emporium.repository.AuteurRepository;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

@Singleton
@Service
public class AuteurService {
    @Inject
    AuteurRepository auteurRepository;

    public List<Auteur> getAllAuteur() {
        return auteurRepository.findAll();
    }

    public Auteur getByIdAuteur(String idAuteur) throws Exception {
        return auteurRepository.findById(idAuteur).orElseThrow(() -> new Exception("Type not found."));
    }

    public Auteur addAuteur(GenericCreateDTO auteur) {
        Auteur auteurNew = Auteur.builder()
                .name(auteur.name)
                .build();

        return auteurRepository.save(auteurNew);
    }

    public Auteur modifyAuteur(GenericModifyDTO auteur) {
        if (auteurRepository.existsById(auteur.getId())) {
            Auteur auteurModified = Auteur.builder()
                    .idAuteur(auteur.getId())
                    .name(auteur.getName())
                    .build();

            return auteurRepository.save(auteurModified);
        } else {
            throw new IllegalArgumentException("Id: " + auteur.getId() + " Non trouvée dans la bdd");
        }
    }

    public String suppAuteur(String idAuteur) throws Exception {
        Auteur auteurToDelete = auteurRepository.findById(idAuteur).orElseThrow(() -> new Exception("Id " + idAuteur + " n'existe pas ou a deja était supprimer"));
        auteurRepository.delete(auteurToDelete);
        return "L'auteur a était supprimer";
    }
}
