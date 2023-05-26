package org.emporium.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CommentaireProfilDTO {
    public Commentaire commentaire;
    public String imagePath;
    public String idOeuvre;
}
