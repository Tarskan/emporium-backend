package org.emporium.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
public class Utilisateur {
    @Id
    @GeneratedValue(generator = "UWUid")
    @GenericGenerator(name = "UWUid", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "UWUid")
    public String UWUid;
    @Column(name = "pseudo")
    public String pseudo;
    @Column(name = "grade")
    public String grade;
    @Column(name = "equipe")
    public String equipe;
    @Column(name = "resultat")
    public String resultat;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "collection", joinColumns = {@JoinColumn(name = "bibliotheque")}, inverseJoinColumns = {@JoinColumn(name = "Utilisateur")})
    public List<Bibliotheque> bibliotheques;

    public Utilisateur() {
    }

    public Utilisateur(String UWUid, String pseudo, String grade, String equipe, String resultat) {
        this.UWUid = UWUid;
        this.pseudo = pseudo;
        this.grade = grade;
        this.equipe = equipe;
        this.resultat = resultat;
    }



    public String getUWUid() {
        return UWUid;
    }

    public void setUWUid(String UWUid) {
        this.UWUid = UWUid;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getEquipe() {
        return equipe;
    }

    public void setEquipe(String equipe) {
        this.equipe = equipe;
    }

    public String getResultat() {
        return resultat;
    }

    public void setResultat(String resultat) {
        this.resultat = resultat;
    }

    @Override
    public String toString() {
        return "Utilisateur{" +
                "UWUid='" + UWUid + '\'' +
                ", pseudo='" + pseudo + '\'' +
                ", grade='" + grade + '\'' +
                ", equipe='" + equipe + '\'' +
                ", resultat='" + resultat + '\'' +
                '}';
    }
}
