package org.emporium.service;

import org.emporium.model.*;
import org.emporium.repository.*;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.core.Response;

import org.emporium.model.Collection;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Random;


@Singleton
@Service
public class OeuvresService {

    @Inject
    OeuvresRepository oeuvresRepository;

    @Inject
    CollectionRepository collectionRepository;

    @Inject
    CommentaireRepository commentaireRepository;

    @Inject
    TypeRepository typeRepository;

    @Inject
    AuteurRepository auteurRepository;

    @Inject
    EditeurRepository editeurRepository;

    @Inject
    GenreRepository genreRepository;

    @Inject
    SupportRepository supportRepository;

    @Inject
    ImageService imageService;

    public Response getAllOeuvres() {
        return Response.ok(oeuvresRepository.findAllSorted()).build();
    }

    public Response getByIdOeuvre(String idOeuvre) {
        if (oeuvresRepository.existsById(idOeuvre)) {
            return Response.ok(oeuvresRepository.findByIdOeuvre(idOeuvre)).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Id Oeuvres: " + idOeuvre + " Non trouvée dans la bdd")
                    .build();
        }
    }

    public Response getByIdGenre(String idGenre) {
        if (genreRepository.existsById(idGenre)) {
            return Response.ok(oeuvresRepository.findByIdGenre(idGenre)).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Id Oeuvres: " + idGenre + " Non trouvée dans la bdd")
                    .build();
        }
    }

    public Response getByIdEditeur(String idEditeur) {
        if (editeurRepository.existsById(idEditeur)) {
            return Response.ok(oeuvresRepository.findByIdEditeur(idEditeur)).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Id Oeuvres: " + idEditeur + " Non trouvée dans la bdd")
                    .build();
        }
    }

    public Response getByIdAuteur(String idAuteur) {
        if (auteurRepository.existsById(idAuteur)) {
            return Response.ok(oeuvresRepository.findByIdAuteur(idAuteur)).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Id Oeuvres: " + idAuteur + " Non trouvée dans la bdd")
                    .build();
        }
    }

    public Response getByIdType(String idType) {
        if (typeRepository.existsById(idType)) {
            return Response.ok(oeuvresRepository.findByIdType(idType)).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Id Oeuvres: " + idType + " Non trouvée dans la bdd")
                    .build();
        }
    }

    public Response getByIdSupport(String idSupport) {
        if (supportRepository.existsById(idSupport)) {
            return Response.ok(oeuvresRepository.findByIdSupport(idSupport)).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Id Oeuvres: " + idSupport + " Non trouvée dans la bdd")
                    .build();
        }
    }

    public Response getByTitreAutocomplete(String titre) {
        return Response.ok(oeuvresRepository.findByTitreAutoComplete(titre)).build();
    }

    public Response getLastModified() {
        if (oeuvresRepository.findAllSorted().size() > 4) {
            return Response.ok(oeuvresRepository.findLastModified().subList(0,5)).build();
        } else {
            return Response.ok(oeuvresRepository.findLastModified()).build();
        }
    }

    public Response getFirstPack() {
        if (oeuvresRepository.findAllSorted().size() > 30) {
            return Response.ok(oeuvresRepository.findLastModified().subList(0,30)).build();
        } else {
            return Response.ok(oeuvresRepository.findLastModified()).build();
        }
    }

    public Response getRelatedTo(String idAuteur, String idOeuvre) {
        List<Oeuvres> listOeuvresRelated = oeuvresRepository.findRelatedToOeuvresFromAuteur(idAuteur);
        listOeuvresRelated.remove(oeuvresRepository.findByIdOeuvre(idOeuvre));
        Collections.shuffle(listOeuvresRelated);
        if (listOeuvresRelated.size() > 3) {
            Collections.shuffle(listOeuvresRelated);
            return Response.ok(listOeuvresRelated.subList(0, 3)).build();
        } else {
            return Response.ok(listOeuvresRelated).build();
        }
    }

    public Response addOeuvre(OeuvresCreateDTO oeuvres) throws Exception {
            Date myDate = new Date();
            ImageUpload imageUpload = new ImageUpload();
            imageUpload.setFile(oeuvres.getImage());
            imageUpload.setFileName(oeuvres.getImageName());
            ImageItem image = imageService.uploadImage(imageUpload);

            Oeuvres oeuvresNew = Oeuvres.builder()
                    .titre(oeuvres.titre)
                    .sousTitre(oeuvres.sousTitre)
                    .description(oeuvres.description)
                    .imageName(image.getImageName())
                    .imagePath(image.getImagePath())
                    .type(typeRepository.findById(oeuvres.getIdType()).orElseThrow(() -> new Exception("Type not found.")))
                    .auteur(auteurRepository.findById(oeuvres.getIdAuteur()).orElseThrow(() -> new Exception("Auteur not found.")))
                    .genre(genreRepository.findById(oeuvres.getIdGenre()).orElseThrow(() -> new Exception("Genre not found.")))
                    .editeur(editeurRepository.findById(oeuvres.getIdEditeur()).orElseThrow(() -> new Exception("Editeur not found.")))
                    .support(supportRepository.findById(oeuvres.getIdSupport()).orElseThrow(() -> new Exception("Support not found.")))
                    .creationDate(myDate)
                    .modificationDate(myDate)
                    .build();

            return Response.ok(oeuvresRepository.save(oeuvresNew)).build();
    }

    public Response modifyOeuvre(OeuvresModifyDTO oeuvres) throws Exception {
        if (oeuvresRepository.existsById(oeuvres.getIdOeuvre())) {
                Date myDate = new Date();
                Oeuvres oeuvresOld = oeuvresRepository.findByIdOeuvre(oeuvres.getIdOeuvre());
                ImageItem image = new ImageItem();
                if (oeuvres.getImage() != null) {
                    ImageUpload imageUpload = new ImageUpload();
                    imageUpload.setFile(oeuvres.getImage());
                    imageUpload.setFileName(oeuvres.getImageName());
                    image = imageService.uploadImage(imageUpload);
                    ImageRequest imageRequest =new ImageRequest();
                    imageRequest.setImageName(oeuvresOld.getImageName());
                    imageService.deleteImage(imageRequest);
                } else {
                    image.setImagePath(oeuvresOld.getImagePath());
                    image.setImageName(oeuvresOld.getImageName());
                }

                Oeuvres oeuvresModified =  Oeuvres.builder()
                        .idOeuvre(oeuvres.getIdOeuvre())
                        .titre(oeuvres.getTitre())
                        .sousTitre(oeuvres.getSousTitre())
                        .description(oeuvres.getDescription())
                        .imageName(image.getImageName())
                        .imagePath(image.getImagePath())
                        .type(typeRepository.findById(oeuvres.getIdType()).orElseThrow(() -> new Exception("Type not found.")))
                        .auteur(auteurRepository.findById(oeuvres.getIdAuteur()).orElseThrow(() -> new Exception("Auteur not found.")))
                        .genre(genreRepository.findById(oeuvres.getIdGenre()).orElseThrow(() -> new Exception("Genre not found.")))
                        .editeur(editeurRepository.findById(oeuvres.getIdEditeur()).orElseThrow(() -> new Exception("Editeur not found.")))
                        .support(supportRepository.findById(oeuvres.getIdSupport()).orElseThrow(() -> new Exception("Support not found.")))
                        .creationDate(oeuvresOld.getCreationDate())
                        .modificationDate(myDate)
                        .build();

            return Response.ok(oeuvresRepository.save(oeuvresModified)).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Id Oeuvres: " + oeuvres.getIdOeuvre() + " Non trouvée dans la bdd")
                    .build();
        }
    }

    public Response suppOeuvre(String IdOeuvre) throws Exception {
        if (oeuvresRepository.existsById(IdOeuvre)) {
            Oeuvres oeuvreToDelete = oeuvresRepository.findById(IdOeuvre).orElseThrow(() -> new Exception("Id Oeuvres " + IdOeuvre + " n'existe pas ou a deja était supprimer"));
            List<Commentaire> comUser = commentaireRepository.findByIdOeuvre(IdOeuvre);
            List<Collection> colUser = collectionRepository.findByIdOeuvre(IdOeuvre);
            commentaireRepository.deleteAll(comUser);
            collectionRepository.deleteAll(colUser);
            if (oeuvreToDelete.getImageName() != null) {
                ImageRequest imageRequest =new ImageRequest();
                imageRequest.setImageName(oeuvreToDelete.getImageName());
                imageService.deleteImage(imageRequest);
            }
            oeuvresRepository.delete(oeuvreToDelete);
            return Response.ok("L'oeuvre a était supprimer").build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Id Oeuvres: " + IdOeuvre + " Non trouvée dans la bdd")
                    .build();
        }
    }

}
