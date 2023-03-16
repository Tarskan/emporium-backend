package org.emporium.controller;

import org.emporium.model.Utilisateur;
import org.emporium.service.UtilisateurService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/hello")
public class GreetingResource {
    @Inject
    UtilisateurService utilisateurService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String hello() {
        return "Hello from RESTEasy Reactive";
    }

    @Path("/{pseudo}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Utilisateur> GetPseudo(@PathParam("pseudo") String pseudo) {
            return utilisateurService.GetPseudo(pseudo);
    }

    @Path("/search/{pseudo}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Utilisateur> RecherchePseudo(@PathParam("pseudo") String pseudo) {
        return utilisateurService.SearchByPseudo(pseudo);
    }
}

