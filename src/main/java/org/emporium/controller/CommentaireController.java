package org.emporium.controller;

import org.emporium.model.*;
import org.emporium.service.CommentaireService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Controller
@Path("/commentaire")
public class CommentaireController {
    @Inject
    CommentaireService commentaireService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Commentaire> getAllCommentaire() {
        return commentaireService.getAllCommentaire();
    }

    @Path("/{idCommentaire}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Commentaire getByIdCommentaire(@PathParam("idCommentaire") String idCommentaire) throws Exception {
        return commentaireService.getByIdCommentaire(idCommentaire);
    }

    @Path("/utilisateur/{uwuid}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<CommentaireProfilDTO> getByCommentaireByUwuid(@PathParam("uwuid") String uwuid) throws Exception {
        return commentaireService.getCommentaireByUwuid(uwuid);
    }

    @Path("/oeuvres/{idOeuvres}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Commentaire> getByCommentaireByIdOeuvres(@PathParam("idOeuvres") String idOeuvres) throws Exception {
        return commentaireService.getCommentaireByIdoeuvres(idOeuvres);
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Commentaire PutCommentaire(@RequestBody CommentaireModifyDTO commentaire) throws Exception {
        return commentaireService.modifyCommentaire(commentaire);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Commentaire AddCommentaire(@RequestBody CommentaireCreateDTO commentaire) throws Exception {
        return commentaireService.addCommentaire(commentaire);
    }

    @Path("/like")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Commentaire AddLikeCommentaire(@RequestBody CommentaireLikeDTO commentaire) throws Exception {
        return commentaireService.likeManagement(commentaire);
    }

    @Path("/delete/{idCommentaire}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public String DeleteCommentaire(@PathParam("idCommentaire") String idCommentaire) throws Exception {
        return commentaireService.suppCommentaire(idCommentaire);
    }
}
