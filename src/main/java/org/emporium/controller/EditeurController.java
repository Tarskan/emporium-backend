package org.emporium.controller;

import org.eclipse.microprofile.jwt.JsonWebToken;
import org.emporium.model.GenericCreateDTO;
import org.emporium.model.GenericModifyDTO;
import org.emporium.service.EditeurService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Controller
@Path("/editeur")
public class EditeurController {
    @Inject
    EditeurService editeurService;

    @Inject
    JsonWebToken jwt;

    @GET
    @PermitAll
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllEditeur() throws Exception {
        return editeurService.getAllEditeur();
    }

    @Path("/{idEditeur}")
    @GET
    @PermitAll
    @Produces(MediaType.APPLICATION_JSON)
    public Response getByIdEditeur(@PathParam("idEditeur") String idEditeur) throws Exception {
        return editeurService.getByIdEditeur(idEditeur);
    }

    @Path("/search/{editeur}")
    @GET
    @PermitAll
    @Produces(MediaType.APPLICATION_JSON)
    public Response RechercheUtilisateurByPseudo(@PathParam("editeur") String editeur) {
        return editeurService.getEditeurAutocomplete(editeur);
    }

    @PUT
    @RolesAllowed({ "User", "Admin" })
    @Produces(MediaType.APPLICATION_JSON)
    public Response PutEditeur(@RequestBody GenericModifyDTO editeur) throws Exception {
        return editeurService.modifyEditeur(editeur);
    }

    @POST
    @RolesAllowed({ "User", "Admin" })
    @Produces(MediaType.APPLICATION_JSON)
    public Response AddEditeur(@RequestBody GenericCreateDTO editeur) throws Exception {
        return editeurService.addEditeur(editeur);
    }

    @Path("/delete/{idEditeur}")
    @DELETE
    @RolesAllowed("Admin")
    @Produces(MediaType.APPLICATION_JSON)
    public Response DeleteEditeur(@PathParam("idEditeur") String idEditeur) throws Exception {
        return editeurService.suppEditeur(idEditeur);
    }
}
