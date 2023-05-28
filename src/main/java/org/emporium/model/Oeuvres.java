package org.emporium.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.Hibernate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Oeuvres {
    @Id
    @GeneratedValue(generator = "idOeuvre")
    @GenericGenerator(name = "idOeuvre", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "idOeuvre")
    private String idOeuvre;
    @Column(name = "titre")
    public String titre;
    @Column(name = "sousTitre")
    public String sousTitre;
    @Column(name = "description", length = 2000)
    public String description;
    @Column(name = "imageName", length = 1000)
    public String imageName;
    @Column(name = "imagePath", length = 1000)
    public String imagePath;
    @Column(name = "creationDate")
    public Date creationDate;
    @Column(name = "modificationDate")
    public Date modificationDate;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name="idType")
    private Type type;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name="idAuteur")
    private Auteur auteur;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name="idEditeur")
    private Editeur editeur;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name="idSupport")
    private Support support;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name="idGenre")
    private Genre genre;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "collection", joinColumns = {@JoinColumn(name = "idOeuvre")}, inverseJoinColumns = {@JoinColumn(name = "UWUid")})
    @JsonBackReference("collection")
    public List<Utilisateur> listUti;

    @OneToMany(mappedBy ="oeuvre", fetch = FetchType.LAZY)
    @JsonBackReference("oeuvresCom")
    public List<Commentaire> listCommentaire;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Oeuvres oeuvres = (Oeuvres) o;
        return idOeuvre != null && Objects.equals(idOeuvre, oeuvres.idOeuvre);
    }

}
