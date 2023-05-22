package org.emporium.service;

import org.emporium.model.*;
import org.emporium.repository.CommentaireRepository;
import org.emporium.repository.UtilisateurRepository;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.inject.Singleton;
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
    ImageService imageService;


    public List<Utilisateur> getAllUser() {
        return utilisateurRepository.findAllSorted();
    }

    public Utilisateur GetUserByUwuid(String uwuid) {
        return utilisateurRepository.findByUWUid(uwuid);
    }

    public Utilisateur GetUserByPseudo(String pseudo) {
        return utilisateurRepository.findByPseudo(pseudo);
    }

    public List<Utilisateur> SearchByPseudo(String pseudo) {
        return utilisateurRepository.findByPseudoLike(pseudo);
    }

    public Utilisateur addUser(UtilisateurCreateDTO utilisateur) {
        Date myDate = new Date();

        ImageItem image = imageService.uploadImage(utilisateur.getProfilPicture());

        Utilisateur utilisateurNew =  Utilisateur.builder()
                .pseudo(utilisateur.pseudo)
                .pwd(utilisateur.pwd)
                .creationDate(myDate)
                .modificationDate(myDate)
                .profilPicture(image.getImageName())
                .profilPicturePath(image.getImagePath())
                .build();


        try {
            return utilisateurRepository.save(utilisateurNew);
        } catch(Exception e) {
            throw new IllegalArgumentException("Pseudo: " + utilisateur.getPseudo() + " en doublon dans la bdd");
        }
    }

    public Utilisateur modifyUser(UtilisateurModifyDTO utilisateur) {
        if (utilisateurRepository.existsById(utilisateur.getUWUid())) {
            Date myDate = new Date();

            Utilisateur utilisateurOld = utilisateurRepository.findByUWUid(utilisateur.getUWUid());
            ImageItem image = new ImageItem();
            if (utilisateur.getProfilPicture() != null) {
                image = imageService.uploadImage(utilisateur.getProfilPicture());
                ImageRequest imageRequest = new ImageRequest();
                imageRequest.setImageName(utilisateurOld.getProfilPicture());
                imageService.deleteImage(imageRequest);
            } else {
                image.setImagePath(utilisateurOld.getProfilPicturePath());
                image.setImageName(utilisateurOld.getProfilPicture());
            }
            Utilisateur utilisateurModify =  Utilisateur.builder()
                    .UWUid(utilisateur.getUWUid())
                    .pseudo(utilisateur.getPseudo())
                    .pwd(utilisateur.getPwd())
                    .equipe(utilisateur.getEquipe())
                    .grade(utilisateur.getGrade())
                    .resultat(utilisateur.getResultat())
                    .creationDate(utilisateurOld.getCreationDate())
                    .modificationDate(myDate)
                    .profilPicture(image.getImageName())
                    .profilPicturePath(image.getImagePath())
                    .build();
            try {
                return utilisateurRepository.save(utilisateurModify);
            }
            catch(Exception e) {
                throw new IllegalArgumentException("Pseudo: " + utilisateur.getPseudo() + " en doublon dans la bdd");
            }
        } else {
            throw new IllegalArgumentException("Id: " + utilisateur.getUWUid() + " Non trouvée dans la bdd");
        }
    }

    public String suppUser(String uwuid) {
        if (utilisateurRepository.existsById(uwuid)) {
            Utilisateur userToDelete = utilisateurRepository.findByUWUid(uwuid);
            List<Commentaire> comUser = commentaireRepository.findByUWUid(uwuid);
            if (userToDelete.profilPicture != null) {
                ImageRequest imageRequest =new ImageRequest();
                imageRequest.setImageName(userToDelete.getProfilPicture());
                imageService.deleteImage(imageRequest);
            }
            commentaireRepository.deleteAll(comUser);
            utilisateurRepository.delete(userToDelete);
            return "L'utilisateur est supprimer";
        } else {
            return "Id " + uwuid + " n'existe pas ou a deja était supprimer";
        }
    }
}
