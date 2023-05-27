package org.emporium.controller;

import org.emporium.model.Utilisateur;
import org.emporium.model.UtilisateurCreateDTO;
import org.emporium.model.UtilisateurModifyDTO;
import org.emporium.service.UtilisateurService;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.List;

@Controller
@Path("/utilisateur")
public class UtilisateurController {
    @Inject
    UtilisateurService utilisateurService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllUtilisateur() {
        return utilisateurService.getAllUser();
    }

    @Path("/{adresseMail}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response GetPseudo(@PathParam("adresseMail") String adresseMail) {
            return utilisateurService.GetUserByEmail(adresseMail);
    }


    @Path("/search/{pseudo}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response RechercheUtilisateurByPseudo(@PathParam("pseudo") String pseudo) {
        return utilisateurService.GetUserByPseudo(pseudo);
    }

    @Path("/searchLike/{pseudo}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response RechercheParPseudo(@PathParam("pseudo") String pseudo) {
        return utilisateurService.SearchByPseudo(pseudo);
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response PutUtilisateur(@MultipartForm UtilisateurModifyDTO utilisateur) throws IOException {
        return utilisateurService.modifyUser(utilisateur);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response AddUtilisateur(@MultipartForm UtilisateurCreateDTO utilisateur) throws IOException {
        return utilisateurService.addUser(utilisateur);
    }

    @Path("/delete/{uwuid}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Response DeleteUser(@PathParam("uwuid") String uwuid) {
        return utilisateurService.suppUser(uwuid);
    }
}

