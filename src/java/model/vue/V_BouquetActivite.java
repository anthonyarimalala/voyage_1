
package model.vue;

import database.Connex;
import generalise.Column;
import generalise.CrudOperation;
import generalise.Table;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.voyage.Bouquet;

@Table(name="v_bouquet_activite")
public class V_BouquetActivite {
    
    @Column(name="id_bouquet", autoIncrement = true, id = true)
    int idBouquet;
    
    @Column(name="bouquet")
    String bouquet;
    
    @Column(name="id_activite", autoIncrement = true, id = true)
    int idActivite;
    
    @Column(name="activite")
    String activite;
    
    @Column(name="prix_u")
    double prixU;
    
    public static void main(String[] args) throws ClassNotFoundException, ClassNotFoundException, SQLException{
        Connection connection = Connex.getConnection();
        List<List<V_BouquetActivite>> myList = getActiviteSelonBouquet(connection);
        
        
    }
    
    public static List<List<V_BouquetActivite>> getActiviteSelonBouquet(Connection connection){
        CrudOperation crud = new CrudOperation(connection);
        List<List<V_BouquetActivite>> myList = new ArrayList<>();
        List<Bouquet> bouquets = crud.selectAll(Bouquet.class);
        
        for(Bouquet b: bouquets){
            List<V_BouquetActivite> bouquetActivite = crud.selectAllById(V_BouquetActivite.class, "id_bouquet", b.getIdBouquet());
            myList.add(bouquetActivite);
        }
        
        
        return myList;
    }

    public V_BouquetActivite() {
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

    public int getIdActivite() {
        return idActivite;
    }

    public void setIdActivite(int idActivite) {
        this.idActivite = idActivite;
    }

    public String getActivite() {
        return activite;
    }

    public void setActivite(String activite) {
        this.activite = activite;
    }

    public double getPrixU() {
        return prixU;
    }

    public void setPrixU(double prixU) {
        this.prixU = prixU;
    }
    
    
    
}
