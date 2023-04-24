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
    public Collection getById(@PathParam("IdColection") String IdColection) {
        return collectionService.getByIdCollection(IdColection);
    }

    @Path("/genre/{idGenre}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Collection> getByGenre(@PathParam("idGenre") String idGenre) {
        return collectionService.getByIdUWUid(idGenre);
    }

    @Path("/oeuvres/{IdOeuvre}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Collection> getByEditor(@PathParam("IdOeuvre") String IdOeuvre) {
        return collectionService.getByIdOeuvre(IdOeuvre);
    }
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Collection PutCollection(@RestForm @PartType(MediaType.APPLICATION_JSON) Collection collection) {
        return collectionService.modifyCollection(collection);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Collection AddCollection(@RestForm @PartType(MediaType.APPLICATION_JSON) Collection collection) {
        return collectionService.addCollection(collection);
    }

    @Path("/delete/{IdColection}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public String DeleteCollection(@PathParam("IdColection") String IdColection) {
        return collectionService.suppCollection(IdColection);
    }
}
