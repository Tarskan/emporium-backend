package org.emporium.service;

import org.emporium.model.Commentaire;
import org.emporium.model.Utilisateur;
import org.emporium.model.UtilisateurCreateDTO;
import org.emporium.repository.CommentaireRepository;
import org.emporium.repository.UtilisateurRepository;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

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
        Utilisateur User = utilisateurRepository.findByUWUid(uwuid);
        return User;
    }

    public Utilisateur GetUserByPseudo(String pseudo) {
        Utilisateur User = utilisateurRepository.findByPseudo(pseudo);
        return User;
    }

    public List<Utilisateur> SearchByPseudo(String pseudo) {
        List<Utilisateur> ListUser = utilisateurRepository.findByPseudoLike(pseudo);
        return ListUser;
    }

    public Utilisateur addUser(UtilisateurCreateDTO utilisateur) {
        Utilisateur utilisateurNew =  Utilisateur.builder()
                .pseudo(utilisateur.pseudo)
                .pwd(utilisateur.pwd)
                .build();

        return utilisateurRepository.save(utilisateurNew);
    }

    public Utilisateur modifyUser(Utilisateur utilisateur) {
        if (utilisateurRepository.existsById(utilisateur.getUWUid())) {
            Utilisateur utilisateurModify =  Utilisateur.builder()
                    .UWUid(utilisateur.getUWUid())
                    .pseudo(utilisateur.getPseudo())
                    .pwd(utilisateur.getPwd())
                    .equipe(utilisateur.getEquipe())
                    .grade(utilisateur.getGrade())
                    .resultat(utilisateur.getResultat())
                    .build();

            return utilisateurRepository.save(utilisateurModify);
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
