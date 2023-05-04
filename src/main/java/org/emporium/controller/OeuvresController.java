package org.emporium.controller;

import org.emporium.model.Oeuvres;
import org.emporium.model.OeuvresCreateDTO;
import org.emporium.model.OeuvresModifyDTO;
import org.emporium.service.OeuvresService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

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
    public Oeuvres PutOeuvre(@RequestBody OeuvresModifyDTO oeuvres) throws Exception {
        return oeuvresService.modifyOeuvre(oeuvres);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Oeuvres AddOeuvre(@RequestBody OeuvresCreateDTO oeuvres) throws Exception {
        return oeuvresService.addOeuvre(oeuvres);
    }

    @Path("/delete/{idOeuvre}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public String DeleteOeuvre(@PathParam("idOeuvre") String idOeuvre) {
        return oeuvresService.suppOeuvre(idOeuvre);
    }

}