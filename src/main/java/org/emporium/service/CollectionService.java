package org.emporium.service;

import org.emporium.model.*;
import org.emporium.model.Collection;
import org.emporium.repository.CollectionRepository;
import org.emporium.repository.OeuvresRepository;
import org.emporium.repository.UtilisateurRepository;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.core.Response;
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

    public Response getAllCollection() {
        return Response.ok(collectionRepository.findAll()).build();
    }

    public Response getByIdCollection(String idCollection) throws Exception {
        if (collectionRepository.existsById(idCollection)) {

            return Response.ok(collectionRepository.findById(idCollection)).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Id collection: " + idCollection + " Non trouvée dans la bdd")
                    .build();
        }
    }

    public Response getByIdOeuvre(String idOeuvre) {
        if (oeuvresRepository.existsById(idOeuvre)) {
            List<Utilisateur> listUtilisateur = new ArrayList<Utilisateur>();
            List<Collection> ListCollect = collectionRepository.findByIdOeuvre(idOeuvre);
            listUtilisateur.addAll(ListCollect.stream().map(Collection::getUtilisateur).collect(Collectors.toList()));
            return Response.ok(listUtilisateur).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Id collection: " + idOeuvre + " Non trouvée dans la bdd")
                    .build();
        }
    }

    public Response getByUwuid(String uwuid) {
        if (utilisateurRepository.existsById(uwuid)) {
            List<Oeuvres> listOeuvres = new ArrayList<Oeuvres>();
            List<Collection> ListCollect = collectionRepository.findByUWUid(uwuid);
            listOeuvres.addAll(ListCollect.stream().map(Collection::getOeuvre).collect(Collectors.toList()));
            List<CollectionDTO> collectionUser = new ArrayList<CollectionDTO>();
            for (int i = 0; i < listOeuvres.size(); i++) {
                CollectionDTO objectCollection = new CollectionDTO(ListCollect.get(i), listOeuvres.get(i));
                collectionUser.add(objectCollection);
            }
            return Response.ok(collectionUser).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Id utilisateur: " + uwuid + " Non trouvée dans la bdd")
                    .build();
        }
    }

    public Response getByFavoriteForUwuid(CollectionCreateDTO collection) {
        if (utilisateurRepository.existsById(collection.getUWUid())) {
            List<Oeuvres> listOeuvres = new ArrayList<Oeuvres>();
            List<Collection> ListCollect = collectionRepository.findByFavorisUser(collection.getUWUid(), collection.getFavorite());
            listOeuvres.addAll(ListCollect.stream().map(Collection::getOeuvre).collect(Collectors.toList()));
            return Response.ok(listOeuvres).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Id collection: " + collection.getUWUid() + " Non trouvée dans la bdd")
                    .build();
        }
    }

    public Response isInCollectionUser(String uwuid, String idOeuvre) {
        if (oeuvresRepository.existsById(idOeuvre) && utilisateurRepository.existsById(uwuid)) {
            return Response.ok(collectionRepository.findIfPosses(idOeuvre, uwuid)).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Id utilisateur ou oeuvres: non trouvée dans la bdd")
                    .build();
        }
    }

    public Response addCollection(CollectionCreateDTO collection) throws Exception {
        Collection collectionNew =  Collection.builder()
                .utilisateur(utilisateurRepository.findById(collection.getUWUid()).orElseThrow(() -> new Exception("Utilisateur not found.")))
                .oeuvre(oeuvresRepository.findById(collection.getIdOeuvre()).orElseThrow(() -> new Exception("Oeuvres not found.")))
                .favorite(collection.getFavorite())
                .build();


        return Response.ok(collectionRepository.save(collectionNew)).build();
    }

    public Response modifyCollection(CollectionModifyDTO collection) throws Exception {
        if (collectionRepository.existsById(collection.getIdCollection())) {
            Collection collectionModify =  Collection.builder()
                    .idCollection(collection.getIdCollection())
                    .favorite(collection.getFavorite())
                    .utilisateur(utilisateurRepository.findById(collection.getUWUid()).orElseThrow(() -> new Exception("Utilisateur not found.")))
                    .oeuvre(oeuvresRepository.findById(collection.getIdOeuvre()).orElseThrow(() -> new Exception("Oeuvres not found.")))
                    .build();


            oeuvresRepository.save(collectionModify.getOeuvre());

            return Response.ok(collectionRepository.save(collectionModify)).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Id collection: " + collection.getIdCollection() + " Non trouvée dans la bdd")
                    .build();
        }
    }

    public Response suppCollection(String idCollection) throws Exception {
        if (collectionRepository.existsById(idCollection)) {
            Collection collectionToDelete = collectionRepository.findById(idCollection).orElseThrow(() -> new Exception("Id " + idCollection + " n'existe pas ou a deja était supprimer"));
            collectionRepository.delete(collectionToDelete);
            return Response.ok("La collection a était supprimer").build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Id collection: " + idCollection + " Non trouvée dans la bdd")
                    .build();
        }
    }

}