package org.emporium.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

public class Bibliotheque {
    public String Bid;
    public List<Utilisateur> listUti;
}
