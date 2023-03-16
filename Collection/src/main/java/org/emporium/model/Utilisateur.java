package org.emporium.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

public class Utilisateur {
    public String UWUid;
    public String pseudo;
    public String grade;
    public String equipe;
    public String resultat;
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
}
