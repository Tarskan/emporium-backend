package org.emporium.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

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
    @Column(name = "description")
    public String description;
    @Column(name = "image")
    public String image;
    @Column(name = "countFav")
    public Integer countFav;

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
    @JsonBackReference
    public List<Utilisateur> listUti;

}
