package org.emporium.service;

import org.emporium.model.Commentaire;
import org.emporium.model.Utilisateur;
import org.emporium.model.UtilisateurCreateDTO;
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


    public List<Utilisateur> getAllUser() {
        return utilisateurRepository.findAll();
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
        if(!Objects.equals(utilisateurRepository.findByPseudo(utilisateur.getPseudo()).getPseudo(), utilisateur.getPseudo())) {
            Date myDate = new Date();

            Utilisateur utilisateurNew =  Utilisateur.builder()
                    .pseudo(utilisateur.pseudo)
                    .pwd(utilisateur.pwd)
                    .creationDate(myDate)
                    .modificationDate(null)
                    .build();

            return utilisateurRepository.save(utilisateurNew);
        } else {
            throw new IllegalArgumentException("Pseudo: " + utilisateur.getPseudo() + " en doublon dans la bdd");
        }
    }

    public Utilisateur modifyUser(Utilisateur utilisateur) {
        if (utilisateurRepository.existsById(utilisateur.getUWUid())) {
            if(!Objects.equals(utilisateurRepository.findByPseudo(utilisateur.getPseudo()).getPseudo(), utilisateur.getPseudo())) {
                Date myDate = new Date();

                Utilisateur utilisateurOld = utilisateurRepository.findByUWUid(utilisateur.getUWUid());
                Utilisateur utilisateurModify =  Utilisateur.builder()
                        .UWUid(utilisateur.getUWUid())
                        .pseudo(utilisateur.getPseudo())
                        .pwd(utilisateur.getPwd())
                        .equipe(utilisateur.getEquipe())
                        .grade(utilisateur.getGrade())
                        .resultat(utilisateur.getResultat())
                        .creationDate(utilisateurOld.getCreationDate())
                        .modificationDate(myDate)
                        .build();

                return utilisateurRepository.save(utilisateurModify);
            } else {
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
            commentaireRepository.deleteAll(comUser);
            utilisateurRepository.delete(userToDelete);
            return "L'utilisateur est supprimer";
        } else {
            return "Id " + uwuid + " n'existe pas ou a deja était supprimer";
        }
    }
}
