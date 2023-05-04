package org.emporium.model;

public class OeuvresModifyDTO {
    public String idOeuvre;
    public String titre;
    public String sousTitre;
    public String description;
    public String image;
    public String idType;
    public String idAuteur;
    public String idEditeur;
    public String idSupport;
    public String idGenre;

    public OeuvresModifyDTO(String idOeuvre, String titre, String sousTitre, String description, String image, String idType, String idAuteur, String idEditeur, String idSupport, String idGenre) {
        this.idOeuvre = idOeuvre;
        this.titre = titre;
        this.sousTitre = sousTitre;
        this.description = description;
        this.image = image;
        this.idType = idType;
        this.idAuteur = idAuteur;
        this.idEditeur = idEditeur;
        this.idSupport = idSupport;
        this.idGenre = idGenre;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getSousTitre() {
        return sousTitre;
    }

    public void setSousTitre(String sousTitre) {
        this.sousTitre = sousTitre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public String getIdAuteur() {
        return idAuteur;
    }

    public void setIdAuteur(String idAuteur) {
        this.idAuteur = idAuteur;
    }

    public String getIdEditeur() {
        return idEditeur;
    }

    public void setIdEditeur(String idEditeur) {
        this.idEditeur = idEditeur;
    }

    public String getIdSupport() {
        return idSupport;
    }

    public void setIdSupport(String idSupport) {
        this.idSupport = idSupport;
    }

    public String getIdGenre() {
        return idGenre;
    }

    public void setIdGenre(String idGenre) {
        this.idGenre = idGenre;
    }

    public String getIdOeuvre() {
        return idOeuvre;
    }

    public void setIdOeuvre(String idOeuvre) {
        this.idOeuvre = idOeuvre;
    }
}
