
package model.utils;

import generalise.CrudOperation;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import model.liaison.L_BouquetActivite;
import model.voyage.Activite;
import model.voyage.Bouquet;

public class U_BouquetActivite {
    
    Bouquet bouquet;
    List<Activite> activite;
    
    public static List<U_BouquetActivite> getAllU_BouquetActivite(Connection connection){
        CrudOperation crud = new CrudOperation(connection);
        
        List<U_BouquetActivite> bouquetActivites = new ArrayList<>();
        List<Bouquet> bouquets = crud.selectAll(Bouquet.class);
        for(int i=0; i<bouquets.size(); i++){
            Bouquet bouquet = bouquets.get(i);
            List<Activite> activites = L_BouquetActivite.getAllActiviteByIdBouquet(connection, bouquets.get(i).getIdBouquet());
            
            U_BouquetActivite ba = new U_BouquetActivite();
            ba.setBouquet(bouquet);
            ba.setActivite(activites);
            
            bouquetActivites.add(ba);
        }
        return bouquetActivites;
    }
    

    public U_BouquetActivite() {
    }

    public Bouquet getBouquet() {
        return bouquet;
    }

    public void setBouquet(Bouquet bouquet) {
        this.bouquet = bouquet;
    }

    public List<Activite> getActivite() {
        return activite;
    }

    public void setActivite(List<Activite> activite) {
        this.activite = activite;
    }

    
    
}
