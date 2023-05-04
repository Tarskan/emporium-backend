package org.emporium.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

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

}
