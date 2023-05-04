package org.emporium.model;

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
    public String pwd;
    @Column(name = "grade")
    public String grade;
    @Column(name = "equipe")
    public String equipe;
    @Column(name = "resultat")
    public String resultat;

}
