package org.emporium.service;

import org.emporium.model.Bibliotheque;
import org.emporium.model.Utilisateur;
import org.emporium.repository.BibliothequeRepository;
import org.emporium.repository.UtilisateurRepository;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

@Singleton
public class UtilisateurService {

    @Inject
    private final UtilisateurRepository utilisateurRepository;
    @Inject
    private final BibliothequeRepository bibliothequeRepository;

    public UtilisateurService(UtilisateurRepository utilisateurRepository, BibliothequeRepository bibliothequeRepository) {
        this.utilisateurRepository = utilisateurRepository;
        this.bibliothequeRepository = bibliothequeRepository;
    }

    public List<Utilisateur> getAllUser() {
        return utilisateurRepository.findAll();
    }

    public List<Bibliotheque> getAllBibliotheque() {
        return bibliothequeRepository.findAll();
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

    public Utilisateur addUser(Utilisateur utilisateur) {
        if (utilisateurRepository.existsById(utilisateur.getUWUid())) {
            throw new IllegalArgumentException("Id " + utilisateur.getUWUid() + " déja utilisé");
        } else {
            return utilisateurRepository.save(utilisateur);
        }
    }

    public Utilisateur modifyUser(Utilisateur utilisateur) {
        if (utilisateurRepository.existsById(utilisateur.getUWUid())) {
            return utilisateurRepository.save(utilisateur);
        } else {
            throw new IllegalArgumentException("Id: " + utilisateur.getUWUid() + " Non trouvée dans la bdd");
        }
    }

    public String suppUitlisateur(String uwuid) {
        if (utilisateurRepository.existsById(uwuid)) {
            Utilisateur userToDelete = utilisateurRepository.findByUWUid(uwuid);
            utilisateurRepository.delete(userToDelete);
            return "L'utilisateur a était supprimer";
        } else {
            return "Id " + uwuid + " n'existe pas ou a deja était supprimer";
        }
    }
}
