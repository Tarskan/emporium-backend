package org.emporium.service;

import org.emporium.model.Collection;
import org.emporium.repository.CollectionRepository;
import org.emporium.repository.OeuvresRepository;
import org.emporium.repository.UtilisateurRepository;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

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

    public List<Collection> getAllCollection() {
        return collectionRepository.findAll();
    }
    public Collection getByIdCollection(String IdCollection) {
        return collectionRepository.findByIdCollection(IdCollection);
    }

    public List<Collection> getByIdUWUid(String UWUid) {
        return collectionRepository.findByUWUid(UWUid);
    }

    public List<Collection> getByIdOeuvre(String IdOeuvre) {
        return collectionRepository.findByIdOeuvre(IdOeuvre);
    }

    public Collection addCollection(Collection collection) {
        if (collectionRepository.existsById(collection.getIdCollection())) {
            throw new IllegalArgumentException("Id " + collection.getIdCollection() + " déja utilisé");
        } else {
            return collectionRepository.save(collection);
        }
    }

    public Collection modifyCollection(Collection collection) {
        if (collectionRepository.existsById(collection.getIdCollection())) {
            return collectionRepository.save(collection);
        } else {
            throw new IllegalArgumentException("Id: " + collection.getIdCollection() + " Non trouvée dans la bdd");
        }
    }

    public String suppCollection(String idCollection) {
        if (collectionRepository.existsById(idCollection)) {
            Collection collectionToDelete = collectionRepository.findByIdCollection(idCollection);
            collectionRepository.delete(collectionToDelete);
            return "La collection a était supprimer";
        } else {
            return "Id " + idCollection + " n'existe pas ou a deja était supprimer";
        }
    }

}