package org.emporium.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class OeuvresModifyDTO {
    public String idOeuvre;
    public String titre;
    public String sousTitre;
    public String description;
    public String image;
    public String idType;
    public String idAuteur;
    public String idEditeur;
    public String idSupport;
    public String idGenre;

}
