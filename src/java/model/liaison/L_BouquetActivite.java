
package model.liaison;

import generalise.Column;
import generalise.Table;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.voyage.Activite;

@Table(name="l_bouquet_activite")
public class L_BouquetActivite {
    
    @Column(name="id_bouquet_activite", autoIncrement = true, id = true)
    int idBouquetActivite;
    
    @Column(name="id_bouquet")
    int idBouquet;
    
    @Column(name="id_activite")
    int idActivite;
    

    public L_BouquetActivite() {
    }

    public int getIdBouquetActivite() {
        return idBouquetActivite;
    }

    public void setIdBouquetActivite(int idBouquetActivite) {
        this.idBouquetActivite = idBouquetActivite;
    }

    public int getIdBouquet() {
        return idBouquet;
    }

    public void setIdBouquet(int idBouquet) {
        this.idBouquet = idBouquet;
    }

    public int getIdActivite() {
        return idActivite;
    }

    public void setIdActivite(int idActivite) {
        this.idActivite = idActivite;
    }
    
    
    
}
