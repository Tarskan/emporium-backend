package org.emporium.controller;

import org.emporium.model.Oeuvres;
import org.emporium.model.Utilisateur;
import org.emporium.service.OeuvresService;
import org.jboss.resteasy.reactive.PartType;
import org.jboss.resteasy.reactive.RestForm;
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
    public List<Oeuvres> getAllOeuvre() {
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

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Oeuvres PutOeuvre(@RestForm @PartType(MediaType.APPLICATION_JSON) Oeuvres oeuvres) {
        return oeuvresService.modifyOeuvre(oeuvres);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Oeuvres AddOeuvre(@RestForm @PartType(MediaType.APPLICATION_JSON) Oeuvres oeuvres) {
        return oeuvresService.addOeuvre(oeuvres);
    }

    @Path("/delete/{idOeuvre}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public String DeleteOeuvre(@PathParam("idOeuvre") String idOeuvre) {
        return oeuvresService.suppOeuvre(idOeuvre);
    }

}