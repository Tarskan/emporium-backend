package org.emporium.controller;

import org.emporium.model.Editeur;
import org.emporium.model.GenericCreateDTO;
import org.emporium.model.GenericModifyDTO;
import org.emporium.service.EditeurService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Controller
@Path("/editeur")
public class EditeurController {
    @Inject
    EditeurService editeurService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Editeur> getAllEditeur() throws Exception {
        return editeurService.getAllEditeur();
    }

    @Path("/{idEditeur}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Editeur getByIdEditeur(@PathParam("idEditeur") String idEditeur) throws Exception {
        return editeurService.getByIdEditeur(idEditeur);
    }

    @Path("/search/{editeur}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Editeur> RechercheUtilisateurByPseudo(@PathParam("editeur") String editeur) {
        return editeurService.getEditeurAutocomplete(editeur);
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Editeur PutEditeur(@RequestBody GenericModifyDTO editeur) throws Exception {
        return editeurService.modifyEditeur(editeur);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Editeur AddEditeur(@RequestBody GenericCreateDTO editeur) throws Exception {
        return editeurService.addEditeur(editeur);
    }

    @Path("/delete/{idEditeur}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public String DeleteEditeur(@PathParam("idEditeur") String idEditeur) throws Exception {
        return editeurService.suppEditeur(idEditeur);
    }
}
