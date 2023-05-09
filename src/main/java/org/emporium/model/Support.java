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
public class Support {
    @Id
    @GeneratedValue(generator = "idSupport")
    @GenericGenerator(name = "idSupport", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "idSupport")
    private String idSupport;
    @Column(name = "name")
    public String name;

}
