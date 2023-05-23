package org.emporium.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class OeuvresCreateDTO {
    public String titre;
    public String sousTitre;
    public String description;
    public ImageUpload image;
    public String idType;
    public String idAuteur;
    public String idEditeur;
    public String idSupport;
    public String idGenre;

}
