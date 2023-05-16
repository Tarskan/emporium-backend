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
public class Type {
    @Id
    @GeneratedValue(generator = "idType")
    @GenericGenerator(name = "idType", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "idType")
    private String idType;
    @Column(name = "name", unique = true)
    public String name;

}
