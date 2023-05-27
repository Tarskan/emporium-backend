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
import java.io.IOException;
import java.util.List;

@Controller
@Path("/utilisateur")
public class UtilisateurController {
    @Inject
    UtilisateurService utilisateurService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Utilisateur> getAllUtilisateur() {
        return utilisateurService.getAllUser();
    }

    @Path("/{adresseMail}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Utilisateur GetPseudo(@PathParam("adresseMail") String adresseMail) {
            return utilisateurService.GetUserByEmail(adresseMail);
    }


    @Path("/search/{pseudo}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Utilisateur RechercheUtilisateurByPseudo(@PathParam("pseudo") String pseudo) {
        return utilisateurService.GetUserByPseudo(pseudo);
    }

    @Path("/searchLike/{pseudo}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Utilisateur> RechercheParPseudo(@PathParam("pseudo") String pseudo) {
        return utilisateurService.SearchByPseudo(pseudo);
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Utilisateur PutUtilisateur(@MultipartForm UtilisateurModifyDTO utilisateur) throws IOException {
        return utilisateurService.modifyUser(utilisateur);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Utilisateur AddUtilisateur(@MultipartForm UtilisateurCreateDTO utilisateur) throws IOException {
        return utilisateurService.addUser(utilisateur);
    }

    @Path("/delete/{uwuid}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public String DeleteUser(@PathParam("uwuid") String uwuid) {
        return utilisateurService.suppUser(uwuid);
    }
}

