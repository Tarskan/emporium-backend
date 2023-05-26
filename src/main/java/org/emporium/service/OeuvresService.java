package org.emporium.service;

import org.emporium.model.*;
import org.emporium.repository.*;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.inject.Singleton;
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

    public List<Oeuvres> getAllOeuvres() {
        return oeuvresRepository.findAllSorted();
    }

    public Oeuvres getByIdOeuvre(String idOeuvre) {
        if (oeuvresRepository.existsById(idOeuvre)) {
            return oeuvresRepository.findByIdOeuvre(idOeuvre);
        } else {
            throw new IllegalArgumentException("Id Oeuvres: " + idOeuvre + " Non trouvée dans la bdd");
        }
    }

    public List<Oeuvres> getByIdGenre(String idGenre) {
        if (genreRepository.existsById(idGenre)) {
            return oeuvresRepository.findByIdGenre(idGenre);
        } else {
            throw new IllegalArgumentException("Id Oeuvres: " + idGenre + " Non trouvée dans la bdd");
        }
    }

    public List<Oeuvres> getByIdEditeur(String idEditeur) {
        if (editeurRepository.existsById(idEditeur)) {
            return oeuvresRepository.findByIdEditeur(idEditeur);
        } else {
            throw new IllegalArgumentException("Id Oeuvres: " + idEditeur + " Non trouvée dans la bdd");
        }
    }

    public List<Oeuvres> getByIdAuteur(String idAuteur) {
        if (auteurRepository.existsById(idAuteur)) {
            return oeuvresRepository.findByIdAuteur(idAuteur);
        } else {
            throw new IllegalArgumentException("Id Oeuvres: " + idAuteur + " Non trouvée dans la bdd");
        }
    }

    public List<Oeuvres> getByIdType(String idType) {
        if (typeRepository.existsById(idType)) {
            return oeuvresRepository.findByIdType(idType);
        } else {
            throw new IllegalArgumentException("Id Oeuvres: " + idType + " Non trouvée dans la bdd");
        }
    }

    public List<Oeuvres> getByIdSupport(String idSupport) {
        if (supportRepository.existsById(idSupport)) {
            return oeuvresRepository.findByIdSupport(idSupport);
        } else {
            throw new IllegalArgumentException("Id Oeuvres: " + idSupport + " Non trouvée dans la bdd");
        }
    }

    public List<Oeuvres> getByTitreAutocomplete(String titre) {
        return oeuvresRepository.findByTitreAutoComplete(titre);
    }

    public List<Oeuvres> getLastModified() {
        if (oeuvresRepository.findAllSorted().size() > 4) {
            return oeuvresRepository.findLastModified().subList(0,5);
        } else {
            return oeuvresRepository.findLastModified();
        }
    }

    public List<Oeuvres> getRelatedTo(String idAuteur, String idOeuvre) {
        List<Oeuvres> listOeuvresRelated = oeuvresRepository.findRelatedToOeuvresFromAuteur(idAuteur);
        listOeuvresRelated.remove(oeuvresRepository.findByIdOeuvre(idOeuvre));
        if (listOeuvresRelated.size() > 3) {
            Collections.shuffle(listOeuvresRelated);
            return listOeuvresRelated.subList(0, 3);
        }
        return Collections.emptyList();
    }

    public Oeuvres addOeuvre(OeuvresCreateDTO oeuvres) throws Exception {
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

            return oeuvresRepository.save(oeuvresNew);
    }

    public Oeuvres modifyOeuvre(OeuvresModifyDTO oeuvres) throws Exception {
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

                return oeuvresRepository.save(oeuvresModified);
        } else {
            throw new IllegalArgumentException("Id Oeuvres: " + oeuvres.getIdOeuvre() + " Non trouvée dans la bdd");
        }
    }

    public String suppOeuvre(String IdOeuvre) throws Exception {
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
            return "L'oeuvre a était supprimer";
        } else {
            throw new IllegalArgumentException("Id Oeuvres: " + IdOeuvre + " Non trouvée dans la bdd");
        }
    }

}
