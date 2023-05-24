package org.emporium.service;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.quarkus.runtime.annotations.RegisterForReflection;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.emporium.model.ImageItem;
import org.emporium.model.ImageRequest;
import org.emporium.model.ImageUpload;
import org.springframework.stereotype.Service;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.UUID;

@Service
@RegisterForReflection
public class ImageService {

    private Storage storage;

    @ConfigProperty(name = "GCP_KEY")
    public String myConfigGCP;

    @ConfigProperty(name = "GOOGLE_BUCKET_NAME")
    public String bucketGCP;

    @Inject
    @ApplicationScoped
    public void CloudStorageClient() throws IOException, URISyntaxException {
        byte[] decodedBytes = Base64.getDecoder().decode(myConfigGCP);
        String decodedString = new String(decodedBytes);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Object jsonObject = gson.fromJson(decodedString, Object.class);
        Object finaljsonObject = gson.fromJson(jsonObject.toString().replace("\u003d", ""), Object.class);
        FileWriter fileWriter = new FileWriter("key.json");
        gson.toJson(finaljsonObject, fileWriter);
        fileWriter.close();
        GoogleCredentials credentials = GoogleCredentials.fromStream(new FileInputStream("key.json"));
        this.storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();
    }

    public String test() throws IOException, URISyntaxException {
        byte[] decodedBytes = Base64.getDecoder().decode(myConfigGCP);
        String decodedString = new String(decodedBytes);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Object jsonObject = gson.fromJson(decodedString, Object.class);
        Object finaljsonObject = gson.fromJson(jsonObject.toString().replace("\u003d", ""), Object.class);
        /*ClassLoader classLoader = getClass().getClassLoader();
        URL resourceUrl = classLoader.getResource("/key.json");
        Path resourcePath = Paths.get(resourceUrl.toURI());
        String absolutePath = resourcePath.toAbsolutePath().toString();*/
        FileWriter fileWriter = new FileWriter("key.json");
        gson.toJson(finaljsonObject, fileWriter);
        fileWriter.close();
        return "ok";
    }

    public ImageItem uploadImage(ImageUpload imageUpload) {
        BlobId blobId = BlobId.of(bucketGCP, (bucketGCP+"/") + imageUpload.getImageName().hashCode() + UUID.randomUUID() + "." + imageUpload.getImageExtension());
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();

        storage.create(blobInfo, imageUpload.getImage());
        ImageItem imageItem = new ImageItem();
        imageItem.setImageName(storage.get(blobId).getName());
        imageItem.setImagePath(storage.get(blobId).getMediaLink());
        return imageItem;
    }

    public String getImage(ImageRequest image) {
        BlobId blobId = BlobId.of((bucketGCP+"/"), image.getImageName());
        Blob blobInfo = storage.get(blobId);
        return blobInfo.getMediaLink();
    }

    public Response deleteImage(ImageRequest image) {
        try {
            BlobId blobId = BlobId.of((bucketGCP+"/"), image.getImageName());
            storage.delete(blobId);

            return Response.ok("Image supprimée avec succès !").build();

        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erreur lors de la suppression de l'image.").build();
        }
    }

}
