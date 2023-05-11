package org.emporium.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

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
    @Column(name = "text")
    public String text;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.MERGE)
    @JoinColumn(name="UWUid")
    @JsonManagedReference
    public Utilisateur utilisateur;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.MERGE)
    @JoinColumn(name="idOeuvre")
    @JsonManagedReference
    public Oeuvres oeuvre;
}
