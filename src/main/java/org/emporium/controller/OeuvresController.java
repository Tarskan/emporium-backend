package org.emporium.controller;

import org.eclipse.microprofile.jwt.JsonWebToken;
import org.emporium.model.Oeuvres;
import org.emporium.model.OeuvresCreateDTO;
import org.emporium.model.OeuvresModifyDTO;
import org.emporium.service.OeuvresService;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Controller
@Path("/oeuvres")
public class OeuvresController {
    @Inject
    OeuvresService oeuvresService;

    @Inject
    JsonWebToken jwt;

    @GET
    @PermitAll
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllOeuvre() {
        return oeuvresService.getAllOeuvres();
    }

    @Path("/{idOeuvre}")
    @GET
    @PermitAll
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("idOeuvre") String idOeuvre) {
        return oeuvresService.getByIdOeuvre(idOeuvre);
    }

    @Path("/lastModified")
    @GET
    @PermitAll
    @Produces(MediaType.APPLICATION_JSON)
    public Response getByLastAdded() {
        return oeuvresService.getLastModified();
    }

    @Path("/firstPack")
    @GET
    @PermitAll
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFirstPack() {
        return oeuvresService.getFirstPack();
    }

    @Path("/search/{oeuvreName}")
    @GET
    @PermitAll
    @Produces(MediaType.APPLICATION_JSON)
    public Response getByTitreAutoComplete(@PathParam("oeuvreName") String oeuvreName) {
        return oeuvresService.getByTitreAutocomplete(oeuvreName);
    }

    @Path("/genre/{idGenre}")
    @GET
    @PermitAll
    @Produces(MediaType.APPLICATION_JSON)
    public Response getByGenre(@PathParam("idGenre") String idGenre) {
        return oeuvresService.getByIdGenre(idGenre);
    }

    @Path("/editeur/{idEditeur}")
    @GET
    @PermitAll
    @Produces(MediaType.APPLICATION_JSON)
    public Response getByEditor(@PathParam("idEditeur") String idEditeur) {
        return oeuvresService.getByIdEditeur(idEditeur);
    }

    @Path("/auteur/{idAuteur}")
    @GET
    @PermitAll
    @Produces(MediaType.APPLICATION_JSON)
    public Response getByAuthor(@PathParam("idAuteur") String idAuteur) {
        return oeuvresService.getByIdAuteur(idAuteur);
    }

    @Path("/type/{idType}")
    @GET
    @PermitAll
    @Produces(MediaType.APPLICATION_JSON)
    public Response getByIdType(@PathParam("idType") String idType) {
        return oeuvresService.getByIdType(idType);
    }

    @Path("/support/{idSupport}")
    @GET
    @PermitAll
    @Produces(MediaType.APPLICATION_JSON)
    public Response getByIdSupport(@PathParam("idSupport") String idSupport) {
        return oeuvresService.getByIdSupport(idSupport);
    }

    @Path("/{idOeuvre}/auteur/{idAuteur}")
    @GET
    @PermitAll
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRelatedTo(@PathParam("idOeuvre") String idOeuvre, @PathParam("idAuteur") String idAuteur) {
        return oeuvresService.getRelatedTo(idAuteur, idOeuvre);
    }

    @PUT
    @RolesAllowed({ "User", "Admin" })
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response PutOeuvre(@MultipartForm OeuvresModifyDTO oeuvres) throws Exception {
        return oeuvresService.modifyOeuvre(oeuvres);
    }

    @POST
    @RolesAllowed({ "User", "Admin" })
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response AddOeuvre(@MultipartForm OeuvresCreateDTO oeuvres) throws Exception {
        return oeuvresService.addOeuvre(oeuvres);
    }

    @Path("/delete/{idOeuvre}")
    @DELETE
    @RolesAllowed("Admin")
    @Produces(MediaType.APPLICATION_JSON)
    public Response DeleteOeuvre(@PathParam("idOeuvre") String idOeuvre) throws Exception {
        return oeuvresService.suppOeuvre(idOeuvre);
    }

}