package org.emporium.controller;

import org.emporium.model.*;
import org.emporium.service.SupportService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Controller
@Path("/support")
public class SupportController {
    @Inject
    SupportService supportService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Support> getAllSupport() {
        return supportService.getAllSupport();
    }

    @Path("/{idSupport}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Support getByIdSupport(@PathParam("idSupport") String idSupport) throws Exception {
        return supportService.getByIdSupport(idSupport);
    }

    @Path("/search/{support}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Support> RechercheUtilisateurByPseudo(@PathParam("support") String support) {
        return supportService.getSupportAutocomplete(support);
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Support PutOeuvre(@RequestBody GenericModifyDTO support) {
        return supportService.modifySupport(support);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Support AddSupport(@RequestBody GenericCreateDTO support) throws Exception {
        return supportService.addSupport(support);
    }

    @Path("/delete/{idSupport}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public String DeleteSupport(@PathParam("idSupport") String idSupport) throws Exception {
        return supportService.suppSupport(idSupport);
    }
}
