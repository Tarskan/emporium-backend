package org.emporium.controller;

import org.emporium.model.*;
import org.emporium.service.CommentaireService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Controller
@Path("/commentaire")
public class CommentaireController {
    @Inject
    CommentaireService commentaireService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllCommentaire() {
        return commentaireService.getAllCommentaire();
    }

    @Path("/{idCommentaire}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getByIdCommentaire(@PathParam("idCommentaire") String idCommentaire) throws Exception {
        return commentaireService.getByIdCommentaire(idCommentaire);
    }

    @Path("/last")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getByIdCommentaire() throws Exception {
        return commentaireService.getLastCommentaire();
    }

    @Path("/utilisateur/{uwuid}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getByCommentaireByUwuid(@PathParam("uwuid") String uwuid) throws Exception {
        return commentaireService.getCommentaireByUwuid(uwuid);
    }

    @Path("/oeuvres/{idOeuvres}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getByCommentaireByIdOeuvres(@PathParam("idOeuvres") String idOeuvres) throws Exception {
        return commentaireService.getCommentaireByIdoeuvres(idOeuvres);
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Response PutCommentaire(@RequestBody CommentaireModifyDTO commentaire) throws Exception {
        return commentaireService.modifyCommentaire(commentaire);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response AddCommentaire(@RequestBody CommentaireCreateDTO commentaire) throws Exception {
        return commentaireService.addCommentaire(commentaire);
    }

    @Path("/like")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response AddLikeCommentaire(@RequestBody CommentaireLikeDTO commentaire) throws Exception {
        return commentaireService.likeManagement(commentaire);
    }

    @Path("/delete/{idCommentaire}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Response DeleteCommentaire(@PathParam("idCommentaire") String idCommentaire) throws Exception {
        return commentaireService.suppCommentaire(idCommentaire);
    }
}
