package org.emporium.service;

import org.emporium.model.Collection;
import org.emporium.model.Utilisateur;
import org.emporium.repository.CollectionRepository;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;
import java.util.stream.Collectors;

@Singleton
public class CollectionService {

    @Inject
    CollectionRepository collectionRepository;

    // retourne une liste de collection d'id bibliotheque que possede l'utilisateur
    public List<String> GetUtilisateur(String utilisateur) {
        List<String> collectionIdBibliotheque = collectionRepository.findByUtilisateur(utilisateur).stream().map(Collection::getBibliotheque).collect(Collectors.toList());
        return collectionIdBibliotheque;
    }

    // retourne une liste de collection d'id utilisateur qui possédent la bibliotheque
    public List<String> GetBibliotheque (String bibliotheque) {
        List<String> collectionIdUtilisateur = collectionRepository.findByUtilisateur(bibliotheque).stream().map(Collection::getUtilisateur).collect(Collectors.toList());
        return collectionIdUtilisateur;
    }
}
