package org.emporium.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
//@Table(indexes = {@Index(columnList = "Oeuvres")})
public class Collection {
    @Id
    @GeneratedValue(generator = "IdCollection")
    @GenericGenerator(name = "IdCollection", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "IdCollection")
    public String IdCollection;
    @Column(name = "UWUid")
    public String UWUid;
    @Column(name = "IdOeuvre")
    public String IdOeuvre;

    public String getIdCollection() {
        return IdCollection;
    }

    public void setIdCollection(String idCollection) {
        IdCollection = idCollection;
    }

    public String getUWUid() {
        return UWUid;
    }

    public void setUWUid(String UWUid) {
        this.UWUid = UWUid;
    }

    public String getIdOeuvre() {
        return IdOeuvre;
    }

    public void setIdOeuvre(String idOeuvre) {
        IdOeuvre = idOeuvre;
    }
}
