package org.emporium.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jboss.resteasy.reactive.PartType;

import javax.ws.rs.FormParam;
import javax.ws.rs.core.MediaType;
import java.io.InputStream;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UtilisateurModifyDTO {
    @FormParam("UWUid")
    @PartType(MediaType.TEXT_PLAIN)
    public String UWUid;
    @FormParam("pseudo")
    @PartType(MediaType.TEXT_PLAIN)
    public String pseudo;
    @FormParam("pwd")
    @PartType(MediaType.TEXT_PLAIN)
    public String pwd;
    @FormParam("profilPicture")
    @PartType(MediaType.APPLICATION_OCTET_STREAM)
    public InputStream profilPicture;
    @FormParam("imageName")
    @PartType(MediaType.TEXT_PLAIN)
    public String imageName;
    @FormParam("grade")
    @PartType(MediaType.TEXT_PLAIN)
    public String grade;
    @FormParam("equipe")
    @PartType(MediaType.TEXT_PLAIN)
    public String equipe;
    @FormParam("resultat")
    @PartType(MediaType.TEXT_PLAIN)
    public String resultat;

}
