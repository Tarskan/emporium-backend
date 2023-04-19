package org.emporium.controller;

import org.emporium.service.CollectionService;
import org.springframework.stereotype.Controller;

import javax.inject.Inject;
import javax.ws.rs.Path;

@Controller
@Path("/oeuvres")
public class CollectionController {
    @Inject
    CollectionService collectionService;


}
