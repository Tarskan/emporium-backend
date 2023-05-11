package org.emporium.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
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
    @Column(name = "pseudo")
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

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "collection", joinColumns = {@JoinColumn(name = "UWUid")}, inverseJoinColumns = {@JoinColumn(name = "idOeuvre")})
    @JsonBackReference
    public List<Oeuvres> listOeuvres;

    @OneToMany(mappedBy ="utilisateur", fetch = FetchType.LAZY)
    @JsonBackReference
    public List<Commentaire> listCommentaire;

}
