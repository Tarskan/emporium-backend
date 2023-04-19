package org.emporium.service;

import org.emporium.model.Oeuvres;
import org.emporium.repository.OeuvresRepository;
import org.emporium.repository.UtilisateurRepository;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;


@Singleton
@Service
public class OeuvresService {

    @Inject
    UtilisateurRepository utilisateurRepository;
    @Inject
    OeuvresRepository oeuvresRepository;

    public List<Oeuvres> getAllOeuvres() {
        return oeuvresRepository.findAll();
    }

    public Oeuvres getByIdOeuvre(String IdOeuvre) { return oeuvresRepository.findByIdOeuvre(IdOeuvre); }

    public List<Oeuvres> getByIdGenre(String IdGenre) {
        return oeuvresRepository.findByIdType(IdGenre);
    }

    public List<Oeuvres> getByIdEditeur(String IdEditeur) {
        return oeuvresRepository.findByIdType(IdEditeur);
    }

    public List<Oeuvres> getByIdAuteur(String IdAuteur) {
        return oeuvresRepository.findByIdType(IdAuteur);
    }

    public Oeuvres addOeuvre(Oeuvres oeuvres) {
        if (oeuvresRepository.existsById(oeuvres.getIdOeuvre())) {
            throw new IllegalArgumentException("Id " + oeuvres.getIdOeuvre() + " déja utilisé");
        } else {
            return oeuvresRepository.save(oeuvres);
        }
    }

    public Oeuvres modifyOeuvre(Oeuvres oeuvres) {
        if (oeuvresRepository.existsById(oeuvres.getIdOeuvre())) {
            return oeuvresRepository.save(oeuvres);
        } else {
            throw new IllegalArgumentException("Id: " + oeuvres.getIdOeuvre() + " Non trouvée dans la bdd");
        }
    }

    public String suppOeuvre(String IdOeuvre) {
        if (oeuvresRepository.existsById(IdOeuvre)) {
            Oeuvres oeuvreToDelete = oeuvresRepository.findByIdOeuvre(IdOeuvre);
            oeuvresRepository.delete(oeuvreToDelete);
            return "L'utilisateur a était supprimer";
        } else {
            return "Id " + IdOeuvre + " n'existe pas ou a deja était supprimer";
        }
    }

}
