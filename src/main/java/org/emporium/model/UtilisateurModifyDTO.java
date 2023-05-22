package org.emporium.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UtilisateurModifyDTO {
    public String UWUid;
    public String pseudo;
    public String pwd;
    public String grade;
    public String equipe;
    public String resultat;
    public ImageUpload profilPicture;

}
