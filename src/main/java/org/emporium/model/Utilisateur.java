package org.emporium.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.inject.Inject;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Utilisateur {
    @Id
    @GeneratedValue(generator = "UWUid")
    @GenericGenerator(name = "UWUid", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "UWUid", unique = true)
    private String UWUid;
    @Column(name = "pseudo", unique = true)
    public String pseudo;
    @Column(name = "pwd")
    @JsonIgnore
    public String pwd;
    @Column(name = "grade")
    public String grade;
    @Column(name = "equipe")
    public String equipe;
    @Column(name = "resultat")
    public String resultat;

    @Column(name = "email")
    public String email;
    @Column(name = "description", length = 3000)
    public String description;
    @Column(name = "creationDate")
    public Date creationDate;
    @Column(name = "modificationDate")
    public Date modificationDate;
    @Column(name = "profilPicture", length = 1000)
    public String profilPicture;
    @Column(name = "profilPicturePath", length = 1000)
    public String profilPicturePath;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "collection", joinColumns = {@JoinColumn(name = "UWUid")}, inverseJoinColumns = {@JoinColumn(name = "idOeuvre")})
    @JsonBackReference("collection")
    public List<Oeuvres> listOeuvres;

    @OneToMany(mappedBy ="utilisateur", fetch = FetchType.LAZY)
    @JsonBackReference("commentaireUti")
    public List<Commentaire> listCommentaire;

}
