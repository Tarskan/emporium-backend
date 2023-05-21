package org.emporium.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ImageUpload {
    @JsonProperty("image")
    private byte[] image;

    @JsonProperty("imageName")
    private String imageName;

    @JsonProperty("imageExtension")
    private String imageExtension;
}
