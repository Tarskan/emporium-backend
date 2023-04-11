package org.emporium.controller;

import org.emporium.model.Utilisateur;
import org.emporium.service.CollectionService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/collection")
public class CollectionController {
    @Inject
    CollectionService collectionService;

    @Path("utilisateur/{utilisateur}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<String> UtilisateurWhoHave(@PathParam("utilisateur") String utilisateur) {
            return collectionService.GetUtilisateur(utilisateur);
    }

    @Path("/bibliotheque/{bibliotheque}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<String> BibliothequeAssocierAUtilisateur(@PathParam("bibliotheque") String bibliotheque) {
        return collectionService.GetBibliotheque(bibliotheque);
    }
}

