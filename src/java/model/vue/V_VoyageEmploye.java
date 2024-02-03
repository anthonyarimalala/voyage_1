
package model.vue;

import database.Connex;
import generalise.Column;
import generalise.CrudOperation;
import generalise.Table;
import java.sql.Connection;
import java.sql.SQLException;

@Table(name="v_voyage_employe")
public class V_VoyageEmploye {
    
    @Column(name="id_voyage")
    int idVoyage;
    
    @Column(name="nom")
    String nom;
    
    public static void main(String[] args) throws ClassNotFoundException, SQLException{
        Connection connection = Connex.getConnection();
        CrudOperation crud = new CrudOperation(connection);
        
        System.out.println(crud.selectAllById(V_VoyageEmploye.class, "id_voyage", 15).size());
    }

    public V_VoyageEmploye() {
    }

    public int getIdVoyage() {
        return idVoyage;
    }

    public void setIdVoyage(int idVoyage) {
        this.idVoyage = idVoyage;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    
    
}
