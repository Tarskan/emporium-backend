package org.emporium.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jboss.resteasy.reactive.PartType;

import javax.ws.rs.FormParam;
import javax.ws.rs.core.MediaType;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UtilisateurDeleteDTO {
    @FormParam("uwuid")
    @PartType(MediaType.TEXT_PLAIN)
    public String uwuid;
    @FormParam("authId")
    @PartType(MediaType.TEXT_PLAIN)
    public String authId;
}
