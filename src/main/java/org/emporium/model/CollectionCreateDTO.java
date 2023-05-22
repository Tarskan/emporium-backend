package org.emporium.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class CollectionCreateDTO {
    public String UWUid;
    public String idOeuvre;
    private Boolean favorite;
}
