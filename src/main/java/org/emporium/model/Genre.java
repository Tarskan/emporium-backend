package org.emporium.model;

import lombok.*;
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
public class Genre {
    @Id
    @GeneratedValue(generator = "idGenre")
    @GenericGenerator(name = "idGenre", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "idGenre")
    private String idGenre;
    @Column(name = "name")
    public String name;

}
