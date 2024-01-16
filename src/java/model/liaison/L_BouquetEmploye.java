
package model.liaison;

import generalise.Column;
import generalise.Table;

@Table(name="l_bouquet_employe")
public class L_BouquetEmploye {
    
    @Column(name="id_bouquet_employe", autoIncrement = true, id = true)
    int idBouquetEmploye;
    
    @Column(name="id_bouquet")
    int idBouquet;
    
    @Column(name="id_employe")
    int idEmploye;

    public L_BouquetEmploye() {
    }

    public int getIdBouquetEmploye() {
        return idBouquetEmploye;
    }

    public void setIdBouquetEmploye(int idBouquetEmploye) {
        this.idBouquetEmploye = idBouquetEmploye;
    }

    public int getIdBouquet() {
        return idBouquet;
    }

    public void setIdBouquet(int idBouquet) {
        this.idBouquet = idBouquet;
    }

    public int getIdEmploye() {
        return idEmploye;
    }

    public void setIdEmploye(int idEmploye) {
        this.idEmploye = idEmploye;
    }
    
    
}
