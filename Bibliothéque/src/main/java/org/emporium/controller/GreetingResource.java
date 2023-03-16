package org.emporium.controller;

import org.emporium.model.Bibliotheque;
import org.emporium.service.BibliothequeService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/Bibliotheque")
public class GreetingResource {
    @Inject
    BibliothequeService bibliothequeService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Bibliotheque> hello() {
        return bibliothequeService.GetAllBibliotheque();
    }

}

