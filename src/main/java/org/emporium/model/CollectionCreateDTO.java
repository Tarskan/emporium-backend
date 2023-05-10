package org.emporium.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CollectionCreateDTO {
    public String UWUid;
    public String idOeuvre;
    private Boolean favorite;
}
