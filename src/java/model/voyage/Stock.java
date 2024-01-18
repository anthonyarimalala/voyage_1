
package model.voyage;

import generalise.Column;
import generalise.Table;
import java.sql.Date;


@Table(name="stock")
public class Stock {
    
    @Column(name="id_stock", autoIncrement = true, id = true)
    int idStock;
    
    @Column(name="date_modif")
    Date dateModif;
    
    @Column(name="id_activite")
    int idActivite;
    
    @Column(name="entree")
    double entree;
    
    @Column(name="sortie")
    double sortie;

    public Stock() {
    }

    public int getIdStock() {
        return idStock;
    }

    public void setIdStock(int idStock) {
        this.idStock = idStock;
    }

    public int getIdActivite() {
        return idActivite;
    }

    public void setIdActivite(int idActivite) {
        this.idActivite = idActivite;
    }

    public double getEntree() {
        return entree;
    }

    public void setEntree(double entree) {
        this.entree = entree;
    }

    public double getSortie() {
        return sortie;
    }

    public void setSortie(double sortie) {
        this.sortie = sortie;
    }

    public Date getDateModif() {
        return dateModif;
    }

    public void setDateModif(Date dateModif) {
        this.dateModif = dateModif;
    }
    
    
    
    
    
}
