
package model.voyage;

import generalise.Column;
import generalise.Table;

@Table(name="voyage")
public class Voyage {

    @Column(name="id_voyage", autoIncrement = true, id = true)
    int idVoyage;

    @Column(name="id_bouquet")
    int idBouquet;

    @Column(name="id_lieu")
    int idLieu;

    @Column(name="id_duree")
    int idDuree;

    @Column(name="prix")
    double prix;

    @Column(name="voyage")
    String voyage;

    @Column(name="description")
    String description;

    public Voyage() {
    }

    public int getIdVoyage() {
        return idVoyage;
    }

    public void setIdVoyage(int idVoyage) {
        this.idVoyage = idVoyage;
    }

    public int getIdBouquet() {
        return idBouquet;
    }

    public void setIdBouquet(int idBouquet) {
        this.idBouquet = idBouquet;
    }

    public int getIdLieu() {
        return idLieu;
    }

    public void setIdLieu(int idLieu) {
        this.idLieu = idLieu;
    }

    public int getIdDuree() {
        return idDuree;
    }

    public void setIdDuree(int idDuree) {
        this.idDuree = idDuree;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public String getVoyage() {
        return voyage;
    }

    public void setVoyage(String voyage) {
        this.voyage = voyage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
