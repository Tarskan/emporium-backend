package org.emporium.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User {
    @Id
    @Column(name = "UWUid")
    public String UWUid;
    @Column(name = "pseudo")
    public String pseudo;
    /*@Column(name = "grade")
    public String grade;
    @Column(name = "equipe")
    public String equipe;
    @Column(name = "resultat")
    public String resultat;
    @Column(name = "idCollection")
    public Integer idCollection;*/

    /*public User(String UWUid, String pseudo, String grade, String equipe, String resultat, Integer idCollection) {
        this.UWUid = UWUid;
        this.pseudo = pseudo;
        this.grade = grade;
        this.equipe = equipe;
        this.resultat = resultat;
        this.idCollection = idCollection;
    }*/
}
