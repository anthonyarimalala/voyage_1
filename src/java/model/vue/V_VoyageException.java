/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.vue;

import generalise.Column;
import generalise.Table;

/**
 *
 * @author PC
 */
@Table(name="v_voyage_exception")
public class V_VoyageException {
    @Column(name="id_voyage")
    int idVoyage;

    @Column(name="id_bouquet")
    int idBouquet;
    
    @Column(name="bouquet")
    String bouquet;

    @Column(name="id_lieu")
    int idLieu;
    
    @Column(name="lieu")
    String lieu;

    @Column(name="id_duree")
    int idDuree;
    
    @Column(name="duree")
    String duree;

    @Column(name="prix")
    double prix;

    @Column(name="voyage")
    String voyage;

    @Column(name="description")
    String description;

    public V_VoyageException() {
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

    public String getBouquet() {
        return bouquet;
    }

    public void setBouquet(String bouquet) {
        this.bouquet = bouquet;
    }

    public int getIdLieu() {
        return idLieu;
    }

    public void setIdLieu(int idLieu) {
        this.idLieu = idLieu;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public int getIdDuree() {
        return idDuree;
    }

    public void setIdDuree(int idDuree) {
        this.idDuree = idDuree;
    }

    public String getDuree() {
        return duree;
    }

    public void setDuree(String duree) {
        this.duree = duree;
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
