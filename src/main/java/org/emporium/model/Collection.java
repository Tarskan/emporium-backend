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
public class Collection {
    @Id
    @GeneratedValue(generator = "idCollection")
    @GenericGenerator(name = "idCollection", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "idCollection")
    private String idCollection;
    @Column(name = "favorite")
    public Boolean favorite;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.MERGE)
    @JoinColumn(name="UWUid")
    @JsonManagedReference
    public Utilisateur utilisateur;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.MERGE)
    @JoinColumn(name="idOeuvre")
    @JsonManagedReference
    public Oeuvres oeuvre;

}
