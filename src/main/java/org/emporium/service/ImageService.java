package org.emporium.service;

import org.emporium.model.ImageUpload;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.Response;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class ImageService {

    public Response getImage(String path) {
        try {
            Path imageFile = Path.of("./ObjectStore/" + path);

            byte[] imageData = Files.readAllBytes(imageFile);

            return Response.ok(imageData, String.valueOf(MediaType.APPLICATION_OCTET_STREAM))
                    .header("Content-Disposition", "attachment; filename=\"" + imageFile.getFileName() + "\"")
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erreur lors de la récupération de l'image.").build();
        }
    }
    public String uploadImage(ImageUpload imageUpload) {
        try {
            String destinationFile = "./ObjectStore/" + imageUpload.getImageName().hashCode() + UUID.randomUUID() + "." + imageUpload.getImageExtension();
            FileOutputStream fos = new FileOutputStream(destinationFile);
            fos.write(imageUpload.getImage());
            fos.close();

            return destinationFile;
        } catch (IOException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Image problèmatique");
        }
    }

    public Response deleteImage(String path) {
        try {
            Path imagePath = Paths.get("./ObjectStore/" + path);

            if (Files.exists(imagePath)) {
                Files.delete(imagePath);
                return Response.ok("Image supprimée avec succès !").build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).entity("L'image spécifiée n'existe pas.").build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erreur lors de la suppression de l'image.").build();
        }
    }
}
