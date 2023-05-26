package org.emporium.service;

import org.emporium.model.*;
import org.emporium.repository.CommentaireRepository;
import org.emporium.repository.OeuvresRepository;
import org.emporium.repository.UtilisateurRepository;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Singleton
@Service
public class CommentaireService {
    @Inject
    CommentaireRepository commentaireRepository;

    @Inject
    OeuvresRepository oeuvresRepository;

    @Inject
    UtilisateurRepository utilisateurRepository;

    public List<Commentaire> getAllCommentaire() {
        return commentaireRepository.findAllSorted();
    }

    public Commentaire getByIdCommentaire(String idCommentaire) throws Exception {
        if (commentaireRepository.existsById(idCommentaire)) {
            return commentaireRepository.findById(idCommentaire).orElseThrow(() -> new Exception("Commentaire not found."));
        } else {
            throw new IllegalArgumentException("Id: " + idCommentaire + " Non trouvée dans la bdd");
        }
    }

    public List<CommentaireProfilDTO> getCommentaireByUwuid(String uwuid) throws Exception {
        if (utilisateurRepository.existsById(uwuid)) {
            List<CommentaireProfilDTO> listComUser = new ArrayList<CommentaireProfilDTO>();
            List<Commentaire> listCom = commentaireRepository.findByUWUid(uwuid);
            for (int i = 0; i < listCom.size(); i++) {
                listComUser.add(new CommentaireProfilDTO(listCom.get(i), listCom.get(i).getOeuvre().getImagePath(), listCom.get(i).getOeuvre().getIdOeuvre()));
            }
            if (listCom.size() > 3) {
                return listComUser.subList(0,3);
            }  else {
                return listComUser;
            }

        } else {
            throw new IllegalArgumentException("Id user: " + uwuid + " Non trouvée dans la bdd");
        }
    }

    public List<Commentaire> getCommentaireByIdoeuvres(String idOeuvre) throws Exception {
        if (oeuvresRepository.existsById(idOeuvre)) {
            return commentaireRepository.findByIdOeuvre(idOeuvre);
        } else {
            throw new IllegalArgumentException("Id oeuvres demander: " + idOeuvre + " Non trouvée dans la bdd");
        }
    }

    public Commentaire addCommentaire(CommentaireCreateDTO commentaire) throws Exception {
        Date myDate = new Date();

        Commentaire commentaireNew = Commentaire.builder()
                .utilisateur(utilisateurRepository.findById(commentaire.getUWUid()).orElseThrow(() -> new Exception("Utilisateur not found.")))
                .oeuvre(oeuvresRepository.findById(commentaire.getIdOeuvre()).orElseThrow(() -> new Exception("Oeuvres not found.")))
                .text(commentaire.getText())
                .nbLike(0)
                .nbDislike(0)
                .creationDate(myDate)
                .modificationDate(myDate)
                .build();

        return commentaireRepository.save(commentaireNew);
    }

    public Commentaire likeManagement(CommentaireLikeDTO commentaire) throws Exception {
        if (commentaireRepository.existsById(commentaire.getIdCommentaire())) {
            Commentaire commentaireOld = commentaireRepository.findById(commentaire.getIdCommentaire()).orElseThrow(() -> new Exception("Commentaire not found."));

            Commentaire commentaireModified;
            if (commentaire.getLike()) {
                commentaireModified = Commentaire.builder()
                        .idCommentaire(commentaire.idCommentaire)
                        .utilisateur(utilisateurRepository.findById(commentaireOld.getUtilisateur().getUWUid()).orElseThrow(() -> new Exception("Utilisateur not found.")))
                        .oeuvre(oeuvresRepository.findById(commentaire.getIdOeuvre()).orElseThrow(() -> new Exception("Oeuvres not found.")))
                        .text(commentaireOld.getText())
                        .nbLike(commentaireOld.getNbLike())
                        .nbDislike(commentaireOld.getNbDislike())
                        .creationDate(commentaireOld.getCreationDate())
                        .modificationDate(commentaireOld.getModificationDate())
                        .build();
                commentaireModified.setNbLike(commentaireModified.getNbLike() + 1);
            } else {
                commentaireModified = Commentaire.builder()
                        .utilisateur(utilisateurRepository.findById(commentaireOld.getUtilisateur().getUWUid()).orElseThrow(() -> new Exception("Utilisateur not found.")))
                        .oeuvre(oeuvresRepository.findById(commentaire.getIdOeuvre()).orElseThrow(() -> new Exception("Oeuvres not found.")))
                        .text(commentaireOld.getText())
                        .nbLike(commentaireOld.getNbLike())
                        .nbDislike(commentaireOld.getNbDislike())
                        .creationDate(commentaireOld.getCreationDate())
                        .modificationDate(commentaireOld.getModificationDate())
                        .build();
                commentaireModified.setNbDislike(commentaireModified.getNbDislike() + 1);
            }
            return commentaireRepository.save(commentaireModified);
        } else {
            throw new IllegalArgumentException("Id: " + commentaire.getIdCommentaire() + " Non trouvée dans la bdd");
        }
    }

    public Commentaire modifyCommentaire(CommentaireModifyDTO commentaire) throws Exception {
        Date myDate = new Date();

        if (commentaireRepository.existsById(commentaire.getIdCommentaire())) {
            Commentaire commentaireOld = commentaireRepository.findById(commentaire.getIdCommentaire()).orElseThrow(() -> new Exception("Commentaire not found."));
            Commentaire commentaireModified = Commentaire.builder()
                    .idCommentaire(commentaire.idCommentaire)
                    .utilisateur(utilisateurRepository.findById(commentaire.getUWUid()).orElseThrow(() -> new Exception("Utilisateur not found.")))
                    .oeuvre(oeuvresRepository.findById(commentaire.getIdOeuvre()).orElseThrow(() -> new Exception("Oeuvres not found.")))
                    .text(commentaire.getText())
                    .nbLike(commentaireOld.getNbLike())
                    .nbDislike(commentaireOld.getNbDislike())
                    .creationDate(commentaireOld.getCreationDate())
                    .modificationDate(myDate)
                    .build();

            return commentaireRepository.save(commentaireModified);
        } else {
            throw new IllegalArgumentException("Id: " + commentaire.getIdCommentaire() + " Non trouvée dans la bdd");
        }
    }

    public String suppCommentaire(String idCommentaire) throws Exception {
        if (commentaireRepository.existsById(idCommentaire)) {
            Commentaire commentaireToDelete = commentaireRepository.findById(idCommentaire).orElseThrow(() -> new Exception("Id " + idCommentaire + " n'existe pas ou a deja était supprimer"));
            commentaireRepository.delete(commentaireToDelete);
            return "Le commentaire a était supprimer";
        } else {
            throw new IllegalArgumentException("Id: " + idCommentaire + " Non trouvée dans la bdd");
        }
    }
}
