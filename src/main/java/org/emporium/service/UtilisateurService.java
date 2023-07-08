package org.emporium.service;

import org.emporium.model.*;
import org.emporium.repository.CollectionRepository;
import org.emporium.repository.CommentaireRepository;
import org.emporium.repository.UtilisateurRepository;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Singleton
@Service
public class UtilisateurService {

    @Inject
    UtilisateurRepository utilisateurRepository;

    @Inject
    CommentaireRepository commentaireRepository;

    @Inject
    CollectionRepository collectionRepository;

    @Inject
    ImageService imageService;


    public Response getAllUser() {
        List<Utilisateur> utilisateurList = utilisateurRepository.findAllSorted();
        List<UtilisateurFullDTO> utilisateurFullDTOList = new ArrayList<UtilisateurFullDTO>();
        for (int i = 0; i < utilisateurList.size(); i++) {
            UtilisateurFullDTO utilisateurFull = UtilisateurFullDTO.builder()
                    .profilPicture(utilisateurList.get(i).getProfilPicture())
                    .profilPicturePath(utilisateurList.get(i).getProfilPicturePath())
                    .email(utilisateurList.get(i).getEmail())
                    .pseudo(utilisateurList.get(i).getPseudo())
                    .UWUid(utilisateurList.get(i).getUWUid())
                    .description(utilisateurList.get(i).getDescription())
                    .modificationDate(utilisateurList.get(i).getModificationDate())
                    .creationDate(utilisateurList.get(i).getCreationDate())
                    .nbCom(commentaireRepository.findByUWUid(utilisateurList.get(i).getUWUid()).size())
                    .nbFav(collectionRepository.findByFavorisUser(utilisateurList.get(i).getUWUid(), true).size())
                    .nbOeuvre(collectionRepository.findByUWUid(utilisateurList.get(i).getUWUid()).size())
                    .build();
            utilisateurFullDTOList.add(utilisateurFull);
        }
        return Response.ok(utilisateurFullDTOList).build();
    }

    public Response GetUserByEmail(String email) {
        if (utilisateurRepository.findByMail(email) != null) {
            return Response.ok(utilisateurRepository.findByEmail(email)).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Email :" + email + "non trouvé")
                    .build();
        }
    }

    public Response GetUserByUwuid(String uwuid) {
        if (utilisateurRepository.existsById(uwuid)) {
            Utilisateur utilisateur = utilisateurRepository.findByUWUid(uwuid);
            UtilisateurFullDTO utilisateurFull = UtilisateurFullDTO.builder()
                    .profilPicture(utilisateur.getProfilPicture())
                    .profilPicturePath(utilisateur.getProfilPicturePath())
                    .email(utilisateur.getEmail())
                    .pseudo(utilisateur.getPseudo())
                    .UWUid(utilisateur.getUWUid())
                    .description(utilisateur.getDescription())
                    .modificationDate(utilisateur.getModificationDate())
                    .creationDate(utilisateur.getCreationDate())
                    .nbCom(commentaireRepository.findByUWUid(uwuid).size())
                    .nbFav(collectionRepository.findByFavorisUser(uwuid, true).size())
                    .nbOeuvre(collectionRepository.findByUWUid(uwuid).size())
                    .build();

            return Response.ok(utilisateurFull).build();
        } else  {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Uwuid :" + uwuid + " non trouvé")
                    .build();
        }
    }

    public Response GetUserByPseudo(String pseudo) {
        if (utilisateurRepository.findByPseudo(pseudo) != null) {
            return Response.ok(utilisateurRepository.findByPseudo(pseudo)).build();
        } else  {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Pseudo :" + pseudo + "non trouvé")
                    .build();
        }
    }

    public Response SearchByPseudo(String pseudo) {
        return Response.ok(utilisateurRepository.findByPseudoLike(pseudo)).build();
    }

    public Response addUser(UtilisateurCreateDTO utilisateur) throws IOException {
        if (utilisateurRepository.findByAuthId(utilisateur.getAuthId()) == null) {
            Date myDate = new Date();
            ImageUpload imageUpload = new ImageUpload();
            imageUpload.setFile(utilisateur.getProfilPicture());
            imageUpload.setFileName(utilisateur.getImageName());
            ImageItem image = imageService.uploadImage(imageUpload);

            Utilisateur utilisateurNew =  Utilisateur.builder()
                    .email(utilisateur.getEmail())
                    .pseudo(utilisateur.getPseudo())
                    .creationDate(myDate)
                    .modificationDate(myDate)
                    .profilPicture(image.getImageName())
                    .profilPicturePath(image.getImagePath())
                    .authId(utilisateur.getAuthId())
                    .build();


            try {
                return Response.ok(utilisateurRepository.save(utilisateurNew)).build();
            } catch(Exception e) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("Pseudo ou email en doublon dans la bdd")
                        .build();
            }
        } else {
            return Response.status(Response.Status.CONFLICT)
                    .entity("Auth0 id déja utiliser")
                    .build();
        }
    }

    public Response modifyUser(UtilisateurModifyDTO utilisateur) throws IOException {
        if (Objects.equals(utilisateurRepository.findByEmail(utilisateur.getEmail()).getUWUid(), utilisateur.getUWUid()) && Objects.equals(utilisateurRepository.findByUWUid(utilisateur.getUWUid()).getAuthId(), utilisateur.getAuthId())) {
            Date myDate = new Date();

            Utilisateur utilisateurOld = utilisateurRepository.findByUWUid(utilisateur.getUWUid());
            ImageItem image = new ImageItem();
            ImageUpload imageUpload = new ImageUpload();
            imageUpload.setFile(utilisateur.getProfilPicture());
            imageUpload.setFileName(utilisateur.getImageName());
            if (utilisateur.getProfilPicture() != null) {
                image = imageService.uploadImage(imageUpload);
                ImageRequest imageRequest = new ImageRequest();
                imageRequest.setImageName(utilisateurOld.getProfilPicture());
                imageService.deleteImage(imageRequest);
            } else {
                image.setImagePath(utilisateurOld.getProfilPicturePath());
                image.setImageName(utilisateurOld.getProfilPicture());
            }
            Utilisateur utilisateurModify =  Utilisateur.builder()
                    .UWUid(utilisateurOld.getUWUid())
                    .email(utilisateur.getEmail())
                    .pseudo(utilisateur.getPseudo())
                    .description(utilisateur.getDescription())
                    .creationDate(utilisateurOld.getCreationDate())
                    .modificationDate(myDate)
                    .profilPicture(image.getImageName())
                    .profilPicturePath(image.getImagePath())
                    .listOeuvres(utilisateurOld.getListOeuvres())
                    .listCommentaire(utilisateurOld.getListCommentaire())
                    .authId(utilisateurOld.getAuthId())
                    .build();
            try {
                return Response.ok(utilisateurRepository.save(utilisateurModify)).build();
            }
            catch(Exception e) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("Pseudo ou email en doublon dans la bdd")
                        .build();
            }
        } else {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Auth0 id ou Uwuid ne matchent pas")
                    .build();
        }
    }

    public Response suppUser(UtilisateurDeleteDTO utilisateurDeleteDTO) {
        if (utilisateurRepository.existsById(utilisateurDeleteDTO.getUwuid())) {
            Utilisateur userToDelete = utilisateurRepository.findByUWUid(utilisateurDeleteDTO.getUwuid());
            if (Objects.equals(userToDelete.getAuthId(), utilisateurDeleteDTO.getAuthId())) {
                List<Commentaire> comUser = commentaireRepository.findByUWUid(utilisateurDeleteDTO.getUwuid());
                if (userToDelete.profilPicture != null) {
                    ImageRequest imageRequest =new ImageRequest();
                    imageRequest.setImageName(userToDelete.getProfilPicture());
                    imageService.deleteImage(imageRequest);
                }
                commentaireRepository.deleteAll(comUser);
                utilisateurRepository.delete(userToDelete);
                return Response.ok("L'utilisateur est supprimer").build();
            } else {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("Les informations ne correspondent pas")
                        .build();
            }
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Id " + utilisateurDeleteDTO.getUwuid() + " n'existe pas ou a deja était supprimer")
                    .build();
        }
    }
}
