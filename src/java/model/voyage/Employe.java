
package model.voyage;

import generalise.Column;
import generalise.Table;

@Table(name="id_employe")
public class Employe {
    
    @Column(name="id_employe", autoIncrement = true, id = true)
    int idEmploye;
    
    @Column(name="service")
    String service;
    
    @Column(name="prix")
    double prix;

    public Employe() {
    }

    public int getIdEmploye() {
        return idEmploye;
    }

    public void setIdEmploye(int idEmploye) {
        this.idEmploye = idEmploye;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }
    
    
    
    
}
