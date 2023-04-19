package org.emporium.controller;

import org.emporium.model.Utilisateur;
import org.emporium.service.UtilisateurService;
import org.jboss.resteasy.reactive.PartType;
import org.jboss.resteasy.reactive.RestForm;
import org.springframework.stereotype.Controller;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Controller
@Path("/utilisateur")
public class UtilisateurController {
    @Inject
    UtilisateurService utilisateurService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Utilisateur> getAll() {
        return utilisateurService.getAllUser();
    }

    @Path("/{UWUid}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Utilisateur GetPseudo(@PathParam("UWUid") String UWUid) {
            return utilisateurService.GetUserByUwuid(UWUid);
    }

    @Path("/search/{pseudo}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Utilisateur> RecherchePseudo(@PathParam("pseudo") String pseudo) {
        return utilisateurService.SearchByPseudo(pseudo);
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Utilisateur PutUtilisateur(@RestForm @PartType(MediaType.APPLICATION_JSON) Utilisateur utilisateur) {
        return utilisateurService.modifyUser(utilisateur);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Utilisateur AddUtilisateur(@RestForm @PartType(MediaType.APPLICATION_JSON) Utilisateur utilisateur) {
        return utilisateurService.addUser(utilisateur);
    }

    @Path("/delete/{uwuid}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public String DeleteUser(@PathParam("uwuid") String uwuid) {
        return utilisateurService.suppUser(uwuid);
    }
}

