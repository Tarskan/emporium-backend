package org.emporium.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDTO {
    private String UWUid;
    public String pseudo;
    public String pwd;
    public String grade;
    public String equipe;
    public String resultat;
    public Date creationDate;
    public Date modificationDate;
    public String profilPicturePath;

}
