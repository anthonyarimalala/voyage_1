
package model.voyage;

import generalise.Column;
import generalise.Table;
import java.sql.Date;

@Table(name = "reservation")
public class Reservation {
    
    @Column(name="id_reservation", autoIncrement = true, id = true)
    int idReservation;
    
    @Column(name="id_client")
    int idClient;
    
    @Column(name="date_reservation")
    Date dateReservation;
    
    @Column(name="id_voyage")
    int idVoyage;
    
    @Column(name="quantite")
    double quantite;
    
    @Column(name="etat")
    int etat;

    public Reservation() {
    }

    public int getIdReservation() {
        return idReservation;
    }

    public void setIdReservation(int idReservation) {
        this.idReservation = idReservation;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

 
    

    public Date getDateReservation() {
        return dateReservation;
    }

    public void setDateReservation(Date dateReservation) {
        this.dateReservation = dateReservation;
    }

    public int getIdVoyage() {
        return idVoyage;
    }

    public void setIdVoyage(int idVoyage) {
        this.idVoyage = idVoyage;
    }

    public double getQuantite() {
        return quantite;
    }

    public void setQuantite(double quantite) {
        this.quantite = quantite;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }
    
    
    
}
