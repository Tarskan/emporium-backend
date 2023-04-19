package org.emporium.controller;

import org.emporium.model.Oeuvres;
import org.emporium.service.OeuvresService;
import org.springframework.stereotype.Controller;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Controller
@Path("/oeuvres")
public class OeuvresController {
    @Inject
    OeuvresService oeuvresService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Oeuvres> getAll() {
        return oeuvresService.getAllOeuvres();
    }

    @Path("/{IdOeuvre}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Oeuvres getById(@PathParam("IdOeuvre") String IdOeuvre) {
        return oeuvresService.getByIdOeuvre(IdOeuvre);
    }

    @Path("/genre/{IdGenre}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Oeuvres> getByGenre(@PathParam("IdGenre") String IdGenre) {
        return oeuvresService.getByIdGenre(IdGenre);
    }

    @Path("/editeur/{IdEditeur}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Oeuvres> getByEditor(@PathParam("IdEditeur") String IdEditeur) {
        return oeuvresService.getByIdEditeur(IdEditeur);
    }

    @Path("/auteur/{IdAuteur}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Oeuvres> getByAuthor(@PathParam("IdAuteur") String IdAuteur) {
        return oeuvresService.getByIdAuteur(IdAuteur);
    }



}