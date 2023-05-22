package org.emporium.controller;

import org.emporium.model.*;
import org.emporium.service.CollectionService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Controller
@Path("/collection")
public class CollectionController {
    @Inject
    CollectionService collectionService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Collection> getAllCollection() {
        return collectionService.getAllCollection();
    }

    @Path("/{IdColection}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Collection getById(@PathParam("IdColection") String IdColection) throws Exception {
        return collectionService.getByIdCollection(IdColection);
    }

    @Path("/genre/{idGenre}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Collection> getByGenre(@PathParam("idGenre") String idGenre) {
        return collectionService.getByIdUWUid(idGenre);
    }

    @Path("/utilisateur/{idOeuvre}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Utilisateur> getUserWhoPosses(@PathParam("idOeuvre") String idOeuvre) {
        return collectionService.getByIdOeuvre(idOeuvre);
    }

    @Path("/oeuvres/{uwuid}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Oeuvres> getOeuvresWhoArePossesed(@PathParam("uwuid") String uwuid) {
        return collectionService.getByUwuid(uwuid);
    }

    @Path("/oeuvres/favorite")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Oeuvres> getUserFavorite(@RequestBody CollectionCreateDTO collectionDTO) {
        return collectionService.getByFavoriteForUwuid(collectionDTO);
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Collection PutCollection(@RequestBody CollectionModifyDTO collection) throws Exception {
        return collectionService.modifyCollection(collection);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Collection AddCollection(@RequestBody CollectionCreateDTO collection) throws Exception {
        return collectionService.addCollection(collection);
    }

    @Path("/delete/{IdColection}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public String DeleteCollection(@PathParam("IdColection") String IdColection) throws Exception {
        return collectionService.suppCollection(IdColection);
    }
}
