package org.emporium.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.InputStream;
import java.util.Date;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UtilisateurFullDTO {
    public String UWUid;
    public String email;
    public String pseudo;
    public String description;
    public String profilPicture;
    public String profilPicturePath;
    public Date creationDate;
    public Date modificationDate;
    public Integer nbCom;
    public Integer nbFav;
    public Integer nbOeuvre;
}
