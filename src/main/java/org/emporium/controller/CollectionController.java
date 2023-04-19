package org.emporium.controller;

import org.emporium.model.Collection;
import org.emporium.service.CollectionService;
import org.jboss.resteasy.reactive.PartType;
import org.jboss.resteasy.reactive.RestForm;
import org.springframework.stereotype.Controller;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Controller
@Path("/oeuvres")
public class CollectionController {
    @Inject
    CollectionService collectionService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Collection> getAll() {
        return collectionService.getAllCollection();
    }

    @Path("/{IdColection}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Collection getById(@PathParam("IdColection") String IdColection) {
        return collectionService.getByIdCollection(IdColection);
    }

    @Path("/utilisateur/{UWUid}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Collection> getByGenre(@PathParam("UWUid") String UWUid) {
        return collectionService.getByIdUWUid(UWUid);
    }

    @Path("/Oeuvres/{IdOeuvre}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Collection> getByEditor(@PathParam("IdOeuvre") String IdOeuvre) {
        return collectionService.getByIdOeuvre(IdOeuvre);
    }
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Collection PutUtilisateur(@RestForm @PartType(MediaType.APPLICATION_JSON) Collection collection) {
        return collectionService.modifyCollection(collection);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Collection AddUtilisateur(@RestForm @PartType(MediaType.APPLICATION_JSON) Collection collection) {
        return collectionService.addCollection(collection);
    }

    @Path("/delete/{IdColection}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public String DeleteUser(@PathParam("IdColection") String IdColection) {
        return collectionService.suppCollection(IdColection);
    }
}
