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
public class OeuvresCreateDTO {
    @FormParam("titre")
    @PartType(MediaType.TEXT_PLAIN)
    public String titre;
    @FormParam("sousTitre")
    @PartType(MediaType.TEXT_PLAIN)
    public String sousTitre;
    @FormParam("description")
    @PartType(MediaType.TEXT_PLAIN)
    public String description;
    @FormParam("image")
    @PartType(MediaType.APPLICATION_OCTET_STREAM)
    public InputStream image;
    @FormParam("imageName")
    @PartType(MediaType.TEXT_PLAIN)
    public String imageName;
    @FormParam("type")
    @PartType(MediaType.TEXT_PLAIN)
    public String type;
    @FormParam("auteur")
    @PartType(MediaType.TEXT_PLAIN)
    public String auteur;
    @FormParam("editeur")
    @PartType(MediaType.TEXT_PLAIN)
    public String editeur;
    @FormParam("support")
    @PartType(MediaType.TEXT_PLAIN)
    public String support;
    @FormParam("genre")
    @PartType(MediaType.TEXT_PLAIN)
    public String genre;

}
