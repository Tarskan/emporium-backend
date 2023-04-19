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
    private final UtilisateurRepository utilisateurRepository;
    @Inject
    private final OeuvresRepository oeuvresRepository;

    public OeuvresService(UtilisateurRepository utilisateurRepository, OeuvresRepository oeuvresRepository) {
        this.utilisateurRepository = utilisateurRepository;
        this.oeuvresRepository = oeuvresRepository;
    }

    public List<Oeuvres> getAllOeuvres() {
        return oeuvresRepository.findAll();
    }

}
