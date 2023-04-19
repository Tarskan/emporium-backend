package org.emporium.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(indexes = {@Index(columnList = "Oeuvres")})
public class Collection {
    @Id
    @GeneratedValue(generator = "IdCollection")
    @GenericGenerator(name = "IdCollection", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "IdCollection")
    String IdCollection;
    @Column(name = "UWUid")
    public String UWUid;
    @Column(name = "IdOeuvre")
    public String IdOeuvre;

}
