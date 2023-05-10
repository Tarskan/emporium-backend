package org.emporium.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CollectionModifyDTO {
    public String idCollection;
    public String UWUid;
    public String idOeuvre;
    public Boolean favorite;
}
