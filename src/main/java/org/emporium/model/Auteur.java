package org.emporium.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Auteur {
    @Id
    @GeneratedValue(generator = "idAuteur")
    @GenericGenerator(name = "idAuteur", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "idAuteur")
    private String idAuteur;
    @Column(name = "name", unique = true)
    public String name;

}
