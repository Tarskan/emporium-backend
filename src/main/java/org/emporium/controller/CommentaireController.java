package org.emporium.controller;

import org.eclipse.microprofile.jwt.JsonWebToken;
import org.emporium.model.*;
import org.emporium.service.CommentaireService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Controller
@Path("/commentaire")
public class CommentaireController {
    @Inject
    CommentaireService commentaireService;

    @Inject
    JsonWebToken jwt;

    @GET
    @PermitAll
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllCommentaire() {
        return commentaireService.getAllCommentaire();
    }

    @Path("/{idCommentaire}")
    @GET
    @PermitAll
    @Produces(MediaType.APPLICATION_JSON)
    public Response getByIdCommentaire(@PathParam("idCommentaire") String idCommentaire) throws Exception {
        return commentaireService.getByIdCommentaire(idCommentaire);
    }

    @Path("/last")
    @GET
    @PermitAll
    @Produces(MediaType.APPLICATION_JSON)
    public Response getByIdCommentaire() throws Exception {
        return commentaireService.getLastCommentaire();
    }

    @Path("/utilisateur/{uwuid}")
    @GET
    @PermitAll
    @Produces(MediaType.APPLICATION_JSON)
    public Response getByCommentaireByUwuid(@PathParam("uwuid") String uwuid) throws Exception {
        return commentaireService.getCommentaireByUwuid(uwuid);
    }

    @Path("/oeuvres/{idOeuvres}")
    @GET
    @PermitAll
    @Produces(MediaType.APPLICATION_JSON)
    public Response getByCommentaireByIdOeuvres(@PathParam("idOeuvres") String idOeuvres) throws Exception {
        return commentaireService.getCommentaireByIdoeuvres(idOeuvres);
    }

    @PUT
    @RolesAllowed({ "User", "Admin" })
    @Produces(MediaType.APPLICATION_JSON)
    public Response PutCommentaire(@RequestBody CommentaireModifyDTO commentaire) throws Exception {
        return commentaireService.modifyCommentaire(commentaire);
    }

    @POST
    @RolesAllowed({ "User", "Admin" })
    @Produces(MediaType.APPLICATION_JSON)
    public Response AddCommentaire(@RequestBody CommentaireCreateDTO commentaire) throws Exception {
        return commentaireService.addCommentaire(commentaire);
    }

    @Path("/like")
    @POST
    @RolesAllowed({ "User", "Admin" })
    @Produces(MediaType.APPLICATION_JSON)
    public Response AddLikeCommentaire(@RequestBody CommentaireLikeDTO commentaire) throws Exception {
        return commentaireService.likeManagement(commentaire);
    }

    @Path("/delete/{idCommentaire}")
    @DELETE
    @RolesAllowed({ "User", "Admin" })
    @Produces(MediaType.APPLICATION_JSON)
    public Response DeleteCommentaire(@PathParam("idCommentaire") String idCommentaire) throws Exception {
        return commentaireService.suppCommentaire(idCommentaire);
    }
}
