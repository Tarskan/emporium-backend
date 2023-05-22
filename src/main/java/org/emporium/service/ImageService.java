package org.emporium.service;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.*;
import io.github.cdimascio.dotenv.Dotenv;
import io.quarkus.runtime.annotations.RegisterForReflection;
import org.emporium.model.ImageItem;
import org.emporium.model.ImageRequest;
import org.emporium.model.ImageUpload;
import org.springframework.stereotype.Service;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.io.*;
import java.util.UUID;

@Service
@ApplicationScoped
@RegisterForReflection
public class ImageService {

    Dotenv dotenv = Dotenv.load();

    private Storage storage;

    @Inject
    public void CloudStorageClient() throws IOException {
        GoogleCredentials credentials = GoogleCredentials.fromStream(new FileInputStream("./src/main/resources/deft-province-387511-152879b26382.json"));
        this.storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();
    }

    public ImageItem uploadImage(ImageUpload imageUpload) {
        BlobId blobId = BlobId.of(dotenv.get("GOOGLE_BUCKET_NAME"), (dotenv.get("GOOGLE_BUCKET_NAME")+ "/") + imageUpload.getImageName().hashCode() + UUID.randomUUID() + "." + imageUpload.getImageExtension());
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();

        storage.create(blobInfo, imageUpload.getImage());
        ImageItem imageItem = new ImageItem();
        imageItem.setImageName(storage.get(blobId).getName());
        imageItem.setImagePath(storage.get(blobId).getMediaLink());
        return imageItem;
    }

    public String getImage(ImageRequest image) {
        BlobId blobId = BlobId.of(dotenv.get("GOOGLE_BUCKET_NAME"), image.getImageName());
        Blob blobInfo = storage.get(blobId);
        return blobInfo.getMediaLink();
    }

    public Response deleteImage(ImageRequest image) {
        try {
            BlobId blobId = BlobId.of(dotenv.get("GOOGLE_BUCKET_NAME"), image.getImageName());
            storage.delete(blobId);

            return Response.ok("Image supprimée avec succès !").build();

        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erreur lors de la suppression de l'image.").build();
        }
    }

}
