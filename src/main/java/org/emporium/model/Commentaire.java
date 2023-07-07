package org.emporium.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Commentaire {
    @Id
    @GeneratedValue(generator = "idCommentaire")
    @GenericGenerator(name = "idCommentaire", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "idCommentaire")
    private String idCommentaire;
    @Column(name = "nbLike")
    public Integer nbLike;
    @Column(name = "nbDislike")
    public Integer nbDislike;
    @Column(name = "text", length = 2000)
    public String text;
    @Column(name = "creationDate")
    public Date creationDate;
    @Column(name = "modificationDate")
    public Date modificationDate;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.MERGE)
    @JoinColumn(name="UWUid")
    public Utilisateur utilisateur;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.MERGE)
    @JoinColumn(name="idOeuvre")
    @JsonBackReference("oeuvresCom")
    public Oeuvres oeuvre;
}
