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
    public String Type;
    public String Auteur;
    public String Editeur;
    public String Support;
    public String Genre;

}
