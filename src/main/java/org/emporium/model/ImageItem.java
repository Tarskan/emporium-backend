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
public class ImageItem {
    @JsonProperty("imageName")
    private String imageName;

    @JsonProperty("imagePath")
    private String imagePath;
}
