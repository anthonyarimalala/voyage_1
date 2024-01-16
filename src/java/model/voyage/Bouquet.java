
package model.voyage;

import generalise.Column;
import generalise.Table;

@Table(name="bouquet")
public class Bouquet {
    
    @Column(name="id_bouquet", autoIncrement = true, id = true)
    int idBouquet;
    
    @Column(name="bouquet")
    String bouquet;

    public Bouquet() {
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
    
    
}
