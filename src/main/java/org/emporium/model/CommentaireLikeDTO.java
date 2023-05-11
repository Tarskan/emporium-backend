package org.emporium.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CommentaireLikeDTO {
    public String idCommentaire;
    public String UWUid;
    public String idOeuvre;
    public Boolean like;
}
