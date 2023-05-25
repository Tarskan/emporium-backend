package org.emporium.controller;

import org.emporium.model.ImageItem;
import org.emporium.model.ImageRequest;
import org.emporium.model.ImageUpload;
import org.emporium.service.ImageService;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.security.PermitAll;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.net.URISyntaxException;

@Controller
@Path("/image")
public class ImageController {

    @Inject
    ImageService imageService;

    @GET
    @PermitAll
    @Consumes(MediaType.APPLICATION_JSON)
    public String Image(@RequestBody ImageRequest imageRequest) throws IOException {
        return imageService.getImage(imageRequest);
    }

    @POST
    @Path("/upload")
    @PermitAll
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public ImageItem postImage(@MultipartForm ImageUpload imageUpload) throws IOException {
        return imageService.uploadImage(imageUpload);
    }

    @DELETE
    @Path("/delete")
    @PermitAll
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteImage(@RequestBody ImageRequest imageRequest) {
        return imageService.deleteImage(imageRequest);
    }
}
