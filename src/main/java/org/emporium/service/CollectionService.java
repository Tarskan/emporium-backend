package org.emporium.service;

import org.emporium.repository.CollectionRepository;
import org.emporium.repository.OeuvresRepository;
import org.emporium.repository.UtilisateurRepository;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
@Service
public class CollectionService {

    @Inject
    CollectionRepository collectionRepository;

    @Inject
    OeuvresRepository oeuvresRepository;

    @Inject
    UtilisateurRepository utilisateurRepository;

    public CollectionService(CollectionRepository collectionRepository, OeuvresRepository oeuvresRepository, UtilisateurRepository utilisateurRepository) {
        this.collectionRepository = collectionRepository;
        this.utilisateurRepository = utilisateurRepository;
        this.oeuvresRepository = oeuvresRepository;
    }

}