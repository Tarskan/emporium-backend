package org.emporium.controller;

import org.emporium.model.GenericCreateDTO;
import org.emporium.model.GenericModifyDTO;
import org.emporium.model.Genre;
import org.emporium.service.GenreService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Controller
@Path("/genre")
public class GenreController {
    @Inject
    GenreService genreService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Genre> getAllSupport() {
        return genreService.getAllGenre();
    }

    @Path("/{idGenre}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Genre getByIdGenre(@PathParam("idGenre") String idGenre) throws Exception {
        return genreService.getByIdGenre(idGenre);
    }

    @Path("/search/{genre}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Genre> RechercheUtilisateurByPseudo(@PathParam("genre") String genre) {
        return genreService.getGenreAutocomplete(genre);
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Genre PutGenre(@RequestBody GenericModifyDTO support) {
        return genreService.modifyGenre(support);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Genre AddGenre(@RequestBody GenericCreateDTO support) {
        return genreService.addGenre(support);
    }

    @Path("/delete/{idGenre}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public String DeleteGenre(@PathParam("idGenre") String idGenre) throws Exception {
        return genreService.suppGenre(idGenre);
    }
}
