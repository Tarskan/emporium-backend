package org.emporium.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
public class Oeuvres {
    @Id
    @GeneratedValue(generator = "IdOeuvre")
    @GenericGenerator(name = "IdOeuvre", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "IdOeuvre")
    public String IdOeuvre;
    @Column(name = "IdType")
    public String IdType;
    @Column(name = "IdGenre")
    public String IdGenre;
    @Column(name = "Titre")
    public String Titre;
    @Column(name = "SousTitre")
    public String SousTitre;
    @Column(name = "Description")
    public String Description;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "collection", joinColumns = {@JoinColumn(name = "Utilisateur")}, inverseJoinColumns = {@JoinColumn(name = "Oeuvres")})
    public List<Utilisateur> listUti;

    public Oeuvres(String idOeuvre, String idType, String idGenre, String titre, String sousTitre, String description, List<Utilisateur> listUti) {
        IdOeuvre = idOeuvre;
        IdType = idType;
        IdGenre = idGenre;
        Titre = titre;
        SousTitre = sousTitre;
        Description = description;
        this.listUti = listUti;
    }

    public Oeuvres() {
    }

    public String getIdOeuvre() {
        return IdOeuvre;
    }

    public void setIdOeuvre(String idOeuvre) {
        IdOeuvre = idOeuvre;
    }

    public String getIdType() {
        return IdType;
    }

    public void setIdType(String idType) {
        IdType = idType;
    }

    public String getIdGenre() {
        return IdGenre;
    }

    public void setIdGenre(String idGenre) {
        IdGenre = idGenre;
    }

    public String getTitre() {
        return Titre;
    }

    public void setTitre(String titre) {
        Titre = titre;
    }

    public String getSousTitre() {
        return SousTitre;
    }

    public void setSousTitre(String sousTitre) {
        SousTitre = sousTitre;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public List<Utilisateur> getListUti() {
        return listUti;
    }

    public void setListUti(List<Utilisateur> listUti) {
        this.listUti = listUti;
    }
}
