
package model.voyage;

import generalise.Column;
import generalise.Table;

@Table(name="duree")
public class Duree {
    
    @Column(name="duree")
    int idDuree;
    
    @Column(name="duree")
    String duree;
    
    @Column(name="valeur")
    double valeur;

    public Duree() {
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

    public double getValeur() {
        return valeur;
    }

    public void setValeur(double valeur) {
        this.valeur = valeur;
    }
    
    
}
