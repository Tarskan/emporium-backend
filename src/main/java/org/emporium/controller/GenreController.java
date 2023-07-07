package org.emporium.controller;

import org.emporium.model.GenericCreateDTO;
import org.emporium.model.GenericModifyDTO;
import org.emporium.service.GenreService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Controller
@Path("/genre")
public class GenreController {
    @Inject
    GenreService genreService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllSupport() {
        return genreService.getAllGenre();
    }

    @Path("/{idGenre}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getByIdGenre(@PathParam("idGenre") String idGenre) throws Exception {
        return genreService.getByIdGenre(idGenre);
    }

    @Path("/search/{genre}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response RechercheUtilisateurByPseudo(@PathParam("genre") String genre) {
        return genreService.getGenreAutocomplete(genre);
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Response PutGenre(@RequestBody GenericModifyDTO support) {
        return genreService.modifyGenre(support);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response AddGenre(@RequestBody GenericCreateDTO support) {
        return genreService.addGenre(support);
    }

    @Path("/delete/{idGenre}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Response DeleteGenre(@PathParam("idGenre") String idGenre) throws Exception {
        return genreService.suppGenre(idGenre);
    }
}
