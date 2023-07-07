package org.emporium.controller;

import org.eclipse.microprofile.jwt.JsonWebToken;
import org.emporium.model.*;
import org.emporium.service.CollectionService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Controller
@Path("/collection")
public class CollectionController {
    @Inject
    CollectionService collectionService;

    @Inject
    JsonWebToken jwt;

    @GET
    @PermitAll
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllCollection() {
        return collectionService.getAllCollection();
    }

    @Path("/{IdColection}")
    @GET
    @PermitAll
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("IdColection") String IdColection) throws Exception {
        return collectionService.getByIdCollection(IdColection);
    }

    @Path("/oeuvres/{idOeuvre}")
    @GET
    @PermitAll
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOeuvresWhoArePossesed(@PathParam("idOeuvre") String idOeuvre) {
        return collectionService.getByIdOeuvre(idOeuvre);
    }

    @Path("/utilisateur/{uwuid}")
    @GET
    @PermitAll
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserWhoPosses(@PathParam("uwuid") String uwuid) {
        return collectionService.getByUwuid(uwuid);
    }

    @Path("/utilisateur/{uwuid}/oeuvres/{idOeuvre}")
    @GET
    @PermitAll
    @Produces(MediaType.APPLICATION_JSON)
    public Response getInCollectionUser(@PathParam("uwuid") String uwuid, @PathParam("idOeuvre") String idOeuvre) {
        return collectionService.isInCollectionUser(uwuid, idOeuvre);
    }

    @Path("/oeuvres/favorite")
    @GET
    @PermitAll
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserFavorite(@RequestBody CollectionCreateDTO collectionDTO) {
        return collectionService.getByFavoriteForUwuid(collectionDTO);
    }

    @PUT
    @RolesAllowed({ "User", "Admin" })
    @Produces(MediaType.APPLICATION_JSON)
    public Response PutCollection(@RequestBody CollectionModifyDTO collection) throws Exception {
        return collectionService.modifyCollection(collection);
    }

    @POST
    @RolesAllowed({ "User", "Admin" })
    @Produces(MediaType.APPLICATION_JSON)
    public Response AddCollection(@RequestBody CollectionCreateDTO collection) throws Exception {
        return collectionService.addCollection(collection);
    }

    @Path("/delete/{IdColection}")
    @DELETE
    @RolesAllowed({ "User", "Admin" })
    @Produces(MediaType.APPLICATION_JSON)
    public Response DeleteCollection(@PathParam("IdColection") String IdColection) throws Exception {
        return collectionService.suppCollection(IdColection);
    }
}
