package org.emporium.controller;

import io.quarkus.security.Authenticated;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.emporium.model.UtilisateurCreateDTO;
import org.emporium.model.UtilisateurDeleteDTO;
import org.emporium.model.UtilisateurModifyDTO;
import org.emporium.service.UtilisateurService;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;

@Controller
@Path("/utilisateur")
public class UtilisateurController {
    @Inject
    UtilisateurService utilisateurService;

    @Inject
    JsonWebToken jwt;

    @GET
    @PermitAll
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllUtilisateur() {
        return utilisateurService.getAllUser();
    }

    @Path("/{adresseMail}")
    @GET
    @PermitAll
    @Produces(MediaType.APPLICATION_JSON)
    public Response GetByEmail(@PathParam("adresseMail") String adresseMail) {
            return utilisateurService.GetUserByEmail(adresseMail);
    }

    @Path("/identification/{uwuid}")
    @GET
    @PermitAll
    @Produces(MediaType.APPLICATION_JSON)
    public Response GetByUwuid(@PathParam("uwuid") String uwuid) {
        return utilisateurService.GetUserByUwuid(uwuid);
    }
    
    @Path("/search/{pseudo}")
    @GET
    @PermitAll
    @Produces(MediaType.APPLICATION_JSON)
    public Response RechercheUtilisateurByPseudo(@PathParam("pseudo") String pseudo) {
        return utilisateurService.GetUserByPseudo(pseudo);
    }

    @Path("/searchLike/{pseudo}")
    @GET
    @PermitAll
    @Produces(MediaType.APPLICATION_JSON)
    public Response RechercheParPseudo(@PathParam("pseudo") String pseudo) {
        return utilisateurService.SearchByPseudo(pseudo);
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @RolesAllowed({ "User", "Admin" })
    public Response PutUtilisateur(@MultipartForm UtilisateurModifyDTO utilisateur) throws IOException {
        return utilisateurService.modifyUser(utilisateur);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Authenticated
    public Response AddUtilisateur(@MultipartForm UtilisateurCreateDTO utilisateur) throws IOException {
        return utilisateurService.addUser(utilisateur);
    }

    @Path("/delete")
    @DELETE
    @RolesAllowed({ "User", "Admin" })
    @Produces(MediaType.APPLICATION_JSON)
    public Response DeleteUser(@RequestBody UtilisateurDeleteDTO utilisateurDeleteDTO) {
        return utilisateurService.suppUser(utilisateurDeleteDTO);
    }
}

