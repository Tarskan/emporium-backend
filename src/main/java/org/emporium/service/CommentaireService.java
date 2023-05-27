package org.emporium.service;

import org.emporium.model.*;
import org.emporium.repository.CommentaireRepository;
import org.emporium.repository.OeuvresRepository;
import org.emporium.repository.UtilisateurRepository;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.core.Response;
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

    public Response getAllCommentaire() {
        return Response.ok(commentaireRepository.findAllSorted()).build();
    }

    public Response getByIdCommentaire(String idCommentaire) throws Exception {
        if (commentaireRepository.existsById(idCommentaire)) {
            return Response.ok(commentaireRepository.findById(idCommentaire)).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Id comentaire: " + idCommentaire + " Non trouvée dans la bdd")
                    .build();
        }
    }

    public Response getCommentaireByUwuid(String uwuid) throws Exception {
        if (utilisateurRepository.existsById(uwuid)) {
            List<CommentaireProfilDTO> listComUser = new ArrayList<CommentaireProfilDTO>();
            List<Commentaire> listCom = commentaireRepository.findByUWUid(uwuid);
            for (int i = 0; i < listCom.size(); i++) {
                listComUser.add(new CommentaireProfilDTO(listCom.get(i), listCom.get(i).getOeuvre().getImagePath(), listCom.get(i).getOeuvre().getIdOeuvre()));
            }
            if (listCom.size() > 3) {
                return Response.ok(listComUser.subList(0,3)).build();
            }  else {
                return Response.ok(listComUser).build();
            }
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Id user: " + uwuid + " Non trouvée dans la bdd")
                    .build();
        }
    }

    public Response getCommentaireByIdoeuvres(String idOeuvre) throws Exception {
        if (oeuvresRepository.existsById(idOeuvre)) {
            return Response.ok(commentaireRepository.findByIdOeuvre(idOeuvre)).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Id oeuvres demander: " + idOeuvre + " Non trouvée dans la bdd")
                    .build();
        }
    }

    public Response addCommentaire(CommentaireCreateDTO commentaire) throws Exception {
        Date myDate = new Date();
        if (utilisateurRepository.existsById(commentaire.getUWUid())) {
            if (oeuvresRepository.existsById(commentaire.getIdOeuvre())) {
                Commentaire commentaireNew = Commentaire.builder()
                        .utilisateur(utilisateurRepository.findById(commentaire.getUWUid()).orElseThrow(() -> new Exception("Utilisateur not found.")))
                        .oeuvre(oeuvresRepository.findById(commentaire.getIdOeuvre()).orElseThrow(() -> new Exception("Oeuvres not found.")))
                        .text(commentaire.getText())
                        .nbLike(0)
                        .nbDislike(0)
                        .creationDate(myDate)
                        .modificationDate(myDate)
                        .build();

                return Response.ok(commentaireRepository.save(commentaireNew)).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("Id oeuvres: " + commentaire.getIdOeuvre() + " Non trouvée dans la bdd")
                        .build();
            }
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Id utilisateur: " + commentaire.getUWUid() + " Non trouvée dans la bdd")
                    .build();
        }
    }

    public Response likeManagement(CommentaireLikeDTO commentaire) throws Exception {
        if (commentaireRepository.existsById(commentaire.getIdCommentaire())) {
            Commentaire commentaireOld = commentaireRepository.findById(commentaire.getIdCommentaire()).orElseThrow(() -> new Exception("Commentaire not found."));

            Commentaire commentaireModified;
            if (utilisateurRepository.existsById(commentaire.getUWUid())) {
                if (oeuvresRepository.existsById(commentaire.getIdOeuvre())) {
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
                } else {
                    return Response.status(Response.Status.NOT_FOUND)
                            .entity("Id oeuvres: " + commentaire.getIdOeuvre() + " Non trouvée dans la bdd")
                            .build();
                }
            } else {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("Id utilisateur: " + commentaire.getUWUid() + " Non trouvée dans la bdd")
                        .build();
            }

            return Response.ok(commentaireRepository.save(commentaireModified)).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Id commentaire: " + commentaire.getIdCommentaire() + " Non trouvée dans la bdd")
                    .build();
        }
    }

    public Response modifyCommentaire(CommentaireModifyDTO commentaire) throws Exception {
        Date myDate = new Date();

        if (commentaireRepository.existsById(commentaire.getIdCommentaire())) {
            if (utilisateurRepository.existsById(commentaire.getUWUid())) {
                if (oeuvresRepository.existsById(commentaire.getIdOeuvre())) {
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

                    return Response.ok(commentaireRepository.save(commentaireModified)).build();
                } else {
                    return Response.status(Response.Status.NOT_FOUND)
                            .entity("Id oeuvres: " + commentaire.getIdOeuvre() + " Non trouvée dans la bdd")
                            .build();
                }
            } else {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("Id utilisateur: " + commentaire.getUWUid() + " Non trouvée dans la bdd")
                        .build();
            }
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Id commentaire: " + commentaire.getIdCommentaire() + " Non trouvée dans la bdd")
                    .build();
        }
    }

    public Response suppCommentaire(String idCommentaire) throws Exception {
        if (commentaireRepository.existsById(idCommentaire)) {
            Commentaire commentaireToDelete = commentaireRepository.findById(idCommentaire).orElseThrow(() -> new Exception("Id " + idCommentaire + " n'existe pas ou a deja était supprimer"));
            commentaireRepository.delete(commentaireToDelete);
            return Response.ok("Le commentaire a était supprimer").build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Id: " + idCommentaire + " Non trouvée dans la bdd")
                    .build();
        }
    }
}
