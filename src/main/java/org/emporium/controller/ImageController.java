package org.emporium.controller;

import org.emporium.model.ImageUpload;
import org.emporium.service.ImageService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.security.PermitAll;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Controller
@Path("/upload")
public class ImageController {

    @Inject
    ImageService imageService;

    @GET
    @Path("/image/{path}")
    @PermitAll
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getImage(@PathParam("path") String path) {
        return imageService.getImage(path);
    }

    @POST
    @Path("/image")
    @PermitAll
    @Consumes(MediaType.APPLICATION_JSON)
    public String postImage(@RequestBody ImageUpload imageUpload) {
        return imageService.uploadImage(imageUpload);
    }

    @DELETE
    @Path("/image/{path}")
    @PermitAll
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteImage(@PathParam("path") String path) {
        return imageService.deleteImage(path);
    }
}
