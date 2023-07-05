package org.emporium.controller;

import io.quarkus.security.identity.SecurityIdentity;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.emporium.model.GenericCreateDTO;
import org.emporium.model.GenericModifyDTO;
import org.emporium.service.AuteurService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.security.PermitAll;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@Controller
@Path("/auteur")

public class AuteurController {
    @Inject
    AuteurService auteurService;

    @Inject
    @ApplicationScoped
    JsonWebToken jwt;

    @Inject
    @ApplicationScoped
    SecurityIdentity securityIdentity;

    @GET
    @PermitAll
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllAuteur(@Context SecurityContext securityContext) {
        System.out.println(jwt.getAudience());
        System.out.println(securityIdentity.getPrincipal());
        return auteurService.getAllAuteur();
    }

    @Path("/{idAuteur}")
    @GET
    @PermitAll
    @Produces(MediaType.APPLICATION_JSON)
    public Response getByIdAuteur(@PathParam("idAuteur") String idAuteur) throws Exception {
        return auteurService.getByIdAuteur(idAuteur);
    }

    @Path("/search/{auteur}")
    @GET
    @PermitAll
    @Produces(MediaType.APPLICATION_JSON)
    public Response RechercheUtilisateurByPseudo(@PathParam("auteur") String auteur) {
        return auteurService.getAuteurAutocomplete(auteur);
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Response PutAuteur(@RequestBody GenericModifyDTO auteur) {
        return auteurService.modifyAuteur(auteur);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response AddAuteur(@RequestBody GenericCreateDTO auteur) {
        return auteurService.addAuteur(auteur);
    }

    @Path("/delete/{idAuteur}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Response DeleteAuteur(@PathParam("idAuteur") String idAuteur) throws Exception {
        return auteurService.suppAuteur(idAuteur);
    }
}
