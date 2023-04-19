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

}
