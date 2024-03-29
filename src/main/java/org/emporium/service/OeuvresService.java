package org.emporium.service;

import org.emporium.model.*;
import org.emporium.repository.*;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.core.Response;

import org.emporium.model.Collection;

import java.util.*;


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
        if (oeuvresRepository.findByTitre(oeuvres.titre.toLowerCase()) == null) {
            Date myDate = new Date();
            ImageUpload imageUpload = new ImageUpload();
            imageUpload.setFile(oeuvres.getImage());
            imageUpload.setFileName(oeuvres.getImageName());
            ImageItem image = imageService.uploadImage(imageUpload);

            if (typeRepository.findByName(oeuvres.getType().toLowerCase()) == null) {
                Type typeNew = Type.builder()
                        .name(oeuvres.getType())
                        .build();
                typeRepository.save(typeNew);
            }

            if (auteurRepository.findByName(oeuvres.getAuteur().toLowerCase()) == null) {
                Auteur auteurNew = Auteur.builder()
                        .name(oeuvres.getAuteur())
                        .build();
                auteurRepository.save(auteurNew);
            }

            if (genreRepository.findByName(oeuvres.getGenre().toLowerCase()) == null) {
                Genre genreNew = Genre.builder()
                        .name(oeuvres.getGenre())
                        .build();
                genreRepository.save(genreNew);
            }

            if (editeurRepository.findByName(oeuvres.getEditeur().toLowerCase()) == null) {
                Editeur editeurNew = Editeur.builder()
                        .name(oeuvres.getEditeur())
                        .build();
                editeurRepository.save(editeurNew);
            }

            if (supportRepository.findByName(oeuvres.getSupport().toLowerCase()) == null) {
                Support supportNew = Support.builder()
                        .name(oeuvres.getSupport())
                        .build();
                supportRepository.save(supportNew);
            }

            Oeuvres oeuvresNew = Oeuvres.builder()
                    .titre(oeuvres.titre)
                    .sousTitre(oeuvres.sousTitre)
                    .description(oeuvres.description)
                    .imageName(image.getImageName())
                    .imagePath(image.getImagePath())
                    .type(typeRepository.findByName(oeuvres.getType().toLowerCase()))
                    .auteur(auteurRepository.findByName(oeuvres.getAuteur().toLowerCase()))
                    .genre(genreRepository.findByName(oeuvres.getGenre().toLowerCase()))
                    .editeur(editeurRepository.findByName(oeuvres.getEditeur().toLowerCase()))
                    .support(supportRepository.findByName(oeuvres.getSupport().toLowerCase()))
                    .creationDate(myDate)
                    .modificationDate(myDate)
                    .build();

            return Response.ok(oeuvresRepository.save(oeuvresNew)).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Le titre de l'oeuvre: " + oeuvres.getTitre() + " en doublon dans la bdd")
                    .build();
        }
    }

    public Response modifyOeuvre(OeuvresModifyDTO oeuvres) throws Exception {
        if (oeuvresRepository.existsById(oeuvres.getIdOeuvre())) {
            if (oeuvresRepository.findByTitre(oeuvres.titre.toLowerCase()) == null || Objects.equals(oeuvresRepository.findByTitre(oeuvres.titre.toLowerCase()).getIdOeuvre(), oeuvres.getIdOeuvre())) {
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

                if (typeRepository.findByName(oeuvres.getType().toLowerCase()) == null) {
                    Type typeNew = Type.builder()
                            .name(oeuvres.getType())
                            .build();
                    typeRepository.save(typeNew);
                }

                if (auteurRepository.findByName(oeuvres.getAuteur().toLowerCase()) == null) {
                    Auteur auteurNew = Auteur.builder()
                            .name(oeuvres.getAuteur())
                            .build();
                    auteurRepository.save(auteurNew);
                }

                if (genreRepository.findByName(oeuvres.getGenre().toLowerCase().toLowerCase()) == null) {
                    Genre genreNew = Genre.builder()
                            .name(oeuvres.getGenre())
                            .build();
                    genreRepository.save(genreNew);
                }

                if (editeurRepository.findByName(oeuvres.getEditeur().toLowerCase().toLowerCase()) == null) {
                    Editeur editeurNew = Editeur.builder()
                            .name(oeuvres.getEditeur())
                            .build();
                    editeurRepository.save(editeurNew);
                }

                if (supportRepository.findByName(oeuvres.getSupport().toLowerCase().toLowerCase()) == null) {
                    Support supportNew = Support.builder()
                            .name(oeuvres.getSupport())
                            .build();
                    supportRepository.save(supportNew);
                }

                Oeuvres oeuvresModified =  Oeuvres.builder()
                        .idOeuvre(oeuvres.getIdOeuvre())
                        .titre(oeuvres.getTitre())
                        .sousTitre(oeuvres.getSousTitre())
                        .description(oeuvres.getDescription())
                        .imageName(image.getImageName())
                        .imagePath(image.getImagePath())
                        .type(typeRepository.findByName(oeuvres.getType().toLowerCase()))
                        .auteur(auteurRepository.findByName(oeuvres.getAuteur().toLowerCase()))
                        .genre(genreRepository.findByName(oeuvres.getGenre().toLowerCase()))
                        .editeur(editeurRepository.findByName(oeuvres.getEditeur().toLowerCase()))
                        .support(supportRepository.findByName(oeuvres.getSupport().toLowerCase()))
                        .creationDate(oeuvresOld.getCreationDate())
                        .modificationDate(myDate)
                        .build();

                return Response.ok(oeuvresRepository.save(oeuvresModified)).build();
            } else {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("Le titre de l'oeuvre: " + oeuvres.getTitre() + " en doublon dans la bdd")
                        .build();
            }
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
