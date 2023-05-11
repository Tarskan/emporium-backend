package org.emporium.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CommentaireModifyDTO {
    public String idCommentaire;
    public String UWUid;
    public String idOeuvre;
    public String text;
}
