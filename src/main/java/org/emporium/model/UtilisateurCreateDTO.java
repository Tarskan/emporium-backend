package org.emporium.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UtilisateurCreateDTO {
    public String pseudo;
    public String pwd;
    public ImageUpload profilPicture;

}
