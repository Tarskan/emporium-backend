package org.emporium.service;

import org.emporium.model.*;
import org.emporium.model.Collection;
import org.emporium.repository.CollectionRepository;
import org.emporium.repository.OeuvresRepository;
import org.emporium.repository.UtilisateurRepository;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.*;
import java.util.stream.Collectors;

@Singleton
@Service
public class CollectionService {

    @Inject
    CollectionRepository collectionRepository;

    @Inject
    OeuvresRepository oeuvresRepository;

    @Inject
    UtilisateurRepository utilisateurRepository;

    public List<Collection> getAllCollection() {
        return collectionRepository.findAll();
    }

    public Collection getByIdCollection(String idCollection) throws Exception {
        return collectionRepository.findById(idCollection).orElseThrow(() -> new Exception("Collection not found."));
    }

    public List<Collection> getByIdUWUid(String UWUid) {
        return collectionRepository.findByUWUid(UWUid);
    }

    public List<Utilisateur> getByIdOeuvre(String idOeuvre) {
        List<Utilisateur> listUtilisateur = new ArrayList<Utilisateur>();
        List<Collection> ListCollect = collectionRepository.findByIdOeuvre(idOeuvre);
        listUtilisateur.addAll(ListCollect.stream().map(Collection::getUtilisateur).collect(Collectors.toList()));
        return listUtilisateur;
    }

    public List<Oeuvres> getByUwuid(String uwuid) {
        List<Oeuvres> listOeuvres = new ArrayList<Oeuvres>();
        List<Collection> ListCollect = collectionRepository.findByUWUid(uwuid);
        listOeuvres.addAll(ListCollect.stream().map(Collection::getOeuvre).collect(Collectors.toList()));
        return listOeuvres;
    }

    public List<Oeuvres> getByFavoriteForUwuid(CollectionCreateDTO collection) {
        List<Oeuvres> listOeuvres = new ArrayList<Oeuvres>();
        List<Collection> ListCollect = collectionRepository.findByFavorisUser(collection.getUWUid(), collection.getFavorite());
        listOeuvres.addAll(ListCollect.stream().map(Collection::getOeuvre).collect(Collectors.toList()));
        return listOeuvres;
    }

    public Collection addCollection(CollectionCreateDTO collection) throws Exception {
        Collection collectionNew =  Collection.builder()
                .utilisateur(utilisateurRepository.findById(collection.getUWUid()).orElseThrow(() -> new Exception("Utilisateur not found.")))
                .oeuvre(oeuvresRepository.findById(collection.getIdOeuvre()).orElseThrow(() -> new Exception("Oeuvres not found.")))
                .favorite(collection.getFavorite())
                .build();

        if(collectionNew.getFavorite()) {
            collectionNew.getOeuvre().setCountFav(collectionNew.getOeuvre().getCountFav()+1);
            oeuvresRepository.save(collectionNew.getOeuvre());
        }

        return collectionRepository.save(collectionNew);
    }

    public Collection modifyCollection(CollectionModifyDTO collection) throws Exception {
        if (collectionRepository.existsById(collection.getIdCollection())) {
            Collection collectionOld = collectionRepository.findById(collection.getIdCollection()).orElseThrow(() -> new Exception("Un problème est survenu"));
            Collection collectionModify =  Collection.builder()
                    .idCollection(collection.getIdCollection())
                    .favorite(collection.getFavorite())
                    .utilisateur(utilisateurRepository.findById(collection.getUWUid()).orElseThrow(() -> new Exception("Utilisateur not found.")))
                    .oeuvre(oeuvresRepository.findById(collection.getIdOeuvre()).orElseThrow(() -> new Exception("Oeuvres not found.")))
                    .build();

            if(collectionModify.getFavorite()) {
                if (collectionModify.getFavorite() != collectionOld.getFavorite()) {
                    collectionModify.getOeuvre().setCountFav(collectionModify.getOeuvre().getCountFav()+1);
                }
            } else {
                if (collectionModify.getFavorite() != collectionOld.getFavorite()) {
                    collectionModify.getOeuvre().setCountFav(collectionModify.getOeuvre().getCountFav()-1);
                }
            }
            oeuvresRepository.save(collectionModify.getOeuvre());

            return collectionRepository.save(collectionModify);
        } else {
            throw new IllegalArgumentException("Id: " + collection.getIdCollection() + " Non trouvée dans la bdd");
        }
    }

    public String suppCollection(String idCollection) throws Exception {
        Collection collectionToDelete = collectionRepository.findById(idCollection).orElseThrow(() -> new Exception("Id " + idCollection + " n'existe pas ou a deja était supprimer"));
        if (collectionToDelete.getFavorite()) {
            collectionToDelete.getOeuvre().setCountFav(collectionToDelete.getOeuvre().getCountFav()-1);
            oeuvresRepository.save(collectionToDelete.getOeuvre());
        }
        collectionRepository.delete(collectionToDelete);
        return "La collection a était supprimer";
    }

}