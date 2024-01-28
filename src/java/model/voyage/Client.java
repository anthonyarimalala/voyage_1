/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.voyage;

import generalise.Column;
import generalise.Table;

/**
 *
 * @author PC
 */
@Table(name="client")
public class Client {
    @Column(name="id_client", autoIncrement = true, id = true)
    int idClient;
    
    @Column(name="nom")
    String nom;
    
    @Column(name="prenom")
    String prenom;
    
    @Column(name="genre")
    int genre;

    public Client() {
    }

    public Client(int idClient, String nom, String prenom, int genre) {
        this.idClient = idClient;
        this.nom = nom;
        this.prenom = prenom;
        this.genre = genre;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public int getGenre() {
        return genre;
    }

    public void setGenre(int genre) {
        this.genre = genre;
    }
}
