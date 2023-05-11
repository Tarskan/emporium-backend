package org.emporium.controller;


import org.emporium.model.Auteur;
import org.emporium.model.GenericCreateDTO;
import org.emporium.model.GenericModifyDTO;
import org.emporium.service.AuteurService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Controller
@Path("/auteur")
public class AuteurController {
    @Inject
    AuteurService auteurService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Auteur> getAllAuteur() {
        return auteurService.getAllAuteur();
    }

    @Path("/{idAuteur}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Auteur getByIdAuteur(@PathParam("idAuteur") String idAuteur) throws Exception {
        return auteurService.getByIdAuteur(idAuteur);
    }

    @Path("/search/{auteur}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Auteur> RechercheUtilisateurByPseudo(@PathParam("auteur") String auteur) {
        return auteurService.getAuteurAutocomplete(auteur);
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Auteur PutAuteur(@RequestBody GenericModifyDTO auteur) {
        return auteurService.modifyAuteur(auteur);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Auteur AddAuteur(@RequestBody GenericCreateDTO auteur) {
        return auteurService.addAuteur(auteur);
    }

    @Path("/delete/{idAuteur}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public String DeleteAuteur(@PathParam("idAuteur") String idAuteur) throws Exception {
        return auteurService.suppAuteur(idAuteur);
    }
}
