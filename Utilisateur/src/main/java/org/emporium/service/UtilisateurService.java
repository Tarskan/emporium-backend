package org.emporium.service;

import org.emporium.model.Utilisateur;
import org.emporium.repository.UtilisateurRepository;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

@Singleton
public class UtilisateurService {

    @Inject
    UtilisateurRepository utilisateurRepository;

    public List<Utilisateur> GetPseudo(String pseudo) {
        List<Utilisateur> ListUser = utilisateurRepository.findByPseudo(pseudo);
        return ListUser;
    }

    public List<Utilisateur> SearchByPseudo(String pseudo) {
        List<Utilisateur> ListUser = utilisateurRepository.findByPseudoLike(pseudo);
        return ListUser;
    }
}
