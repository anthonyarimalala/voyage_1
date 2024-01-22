
package model.utils;

import database.Connex;
import generalise.CrudOperation;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.liaison.L_BouquetActivite;
import model.voyage.Activite;
import model.voyage.Bouquet;
import model.vue.V_BouquetActivite;

public class U_BouquetActivite {
    
    Bouquet bouquet;
    List<V_BouquetActivite> bouquetActivite;
    
    public static void main(String[] args) throws ClassNotFoundException, SQLException{
        Connection connection = Connex.getConnection();
        List<U_BouquetActivite> myList = getAllU_BouquetActivite(connection);
        
        System.out.println(myList.get(0).getBouquet());
        
        myList.get(0).getBouquet().getIdBouquet();
        myList.get(0).getBouquetActivite().size();
        myList.get(0).getBouquetActivite().get(0).getActivite();
        
    }
    
    public static List<U_BouquetActivite> getAllU_BouquetActivite(Connection connection){
        CrudOperation crud = new CrudOperation(connection);
        
        List<U_BouquetActivite> bouquetActivites = new ArrayList<>();
        List<Bouquet> bouquets = crud.selectAll(Bouquet.class);
        for(int i=0; i<bouquets.size(); i++){
            Bouquet bouquet = bouquets.get(i);
            List<V_BouquetActivite> activites = crud.selectAllById(V_BouquetActivite.class, "id_bouquet", bouquets.get(i).getIdBouquet());
            
            U_BouquetActivite ba = new U_BouquetActivite();
            ba.setBouquet(bouquet);
            ba.setBouquetActivite(activites);
            
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

    public List<V_BouquetActivite> getBouquetActivite() {
        return bouquetActivite;
    }

    public void setBouquetActivite(List<V_BouquetActivite> bouquetActivite) {
        this.bouquetActivite = bouquetActivite;
    }
    

    
    
}
