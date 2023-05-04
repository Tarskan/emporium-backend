package org.emporium.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Editeur {
    @Id
    @GeneratedValue(generator = "idEditeur")
    @GenericGenerator(name = "idEditeur", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "idEditeur")
    public String idEditeur;
    public String name;

}
