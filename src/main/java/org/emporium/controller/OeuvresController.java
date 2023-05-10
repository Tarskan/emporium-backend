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

    @Path("/{idOeuvre}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Oeuvres getById(@PathParam("idOeuvre") String idOeuvre) {
        return oeuvresService.getByIdOeuvre(idOeuvre);
    }

    @Path("/search/{oeuvreName}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Oeuvres> getByTitreAutoComplete(@PathParam("oeuvreName") String oeuvreName) {
        return oeuvresService.getByTitreAutocomplete(oeuvreName);
    }

    @Path("/genre/{idGenre}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Oeuvres> getByGenre(@PathParam("idGenre") String idGenre) {
        return oeuvresService.getByIdGenre(idGenre);
    }

    @Path("/editeur/{idEditeur}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Oeuvres> getByEditor(@PathParam("idEditeur") String idEditeur) {
        return oeuvresService.getByIdEditeur(idEditeur);
    }

    @Path("/auteur/{idAuteur}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Oeuvres> getByAuthor(@PathParam("idAuteur") String idAuteur) {
        return oeuvresService.getByIdAuteur(idAuteur);
    }

    @Path("/type/{idType}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Oeuvres> getByType(@PathParam("idType") String idType) {
        return oeuvresService.getByIdType(idType);
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
    public String DeleteOeuvre(@PathParam("idOeuvre") String idOeuvre) throws Exception {
        return oeuvresService.suppOeuvre(idOeuvre);
    }

}