package org.emporium.controller;

import org.emporium.model.Oeuvres;
import org.emporium.service.OeuvresService;
import org.springframework.stereotype.Controller;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Controller
@Path("/oeuvres")
public class OeuvresController {
    @Inject
    OeuvresService oeuvresService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Oeuvres> getAll() {
        return oeuvresService.getAllOeuvres();
    }


}