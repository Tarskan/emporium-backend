package org.emporium.model;

public class UtilisateurCreateDTO {
    public String pseudo;
    public String pwd;


    public UtilisateurCreateDTO(String pseudo, String pwd) {
        this.pseudo = pseudo;
        this.pwd = pwd;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

}
