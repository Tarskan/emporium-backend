package org.emporium.controller;

import org.emporium.model.GenericCreateDTO;
import org.emporium.model.GenericModifyDTO;
import org.emporium.model.Type;
import org.emporium.model.TypeDTO;
import org.emporium.service.TypeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Controller
@Path("/type")
public class TypeController {
    @Inject
    TypeService typeService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllType() throws Exception {
        return typeService.getAllType();
    }

    @Path("/{idType}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getByIdType(@PathParam("idType") String idType) throws Exception {
        return typeService.getByIdType(idType);
    }

    @Path("/search/{type}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response RechercheUtilisateurByPseudo(@PathParam("type") String type) {
        return typeService.getTypeAutocomplete(type);
    }

    @Path("/popular")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response MostThreePopularOne() {
        return typeService.getMostThreePopular();
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Response PutType(@RequestBody GenericModifyDTO type) throws Exception {
        return typeService.modifyType(type);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response AddType(@RequestBody GenericCreateDTO type) throws Exception {
        return typeService.addType(type);
    }

    @Path("/delete/{idType}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Response DeleteType(@PathParam("idType") String idType) throws Exception {
        return typeService.suppType(idType);
    }
}
