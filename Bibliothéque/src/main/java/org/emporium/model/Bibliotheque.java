package org.emporium.model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.util.List;

@Entity
@TypeDef(name = JsonTypes.JSON_BIN, typeClass = JsonBinaryType::class)
public class Bibliotheque {
    @Id
    @GeneratedValue(generator = "UWUid")
    @GenericGenerator(name = "UWUid", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "Bid")
    public String Bid;
    public String theme;
    public String sousTheme;
    public String support;
    @JS
    public Object data;
}
