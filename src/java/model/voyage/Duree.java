
package model.voyage;

import database.Connex;
import generalise.Column;
import generalise.CrudOperation;
import generalise.Table;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@Table(name="duree")
public class Duree {
    
    @Column(name="id_duree", autoIncrement = true, id = true)
    int idDuree;
    
    @Column(name="duree")
    String duree;
    
    @Column(name="valeur")
    double valeur;
    
    public static void main(String[] args) throws ClassNotFoundException, SQLException{
        Connection connection = Connex.getConnection();
        CrudOperation crud = new CrudOperation(connection);
        
        List<Duree> durees = crud.selectAll(Duree.class);
        System.out.println(durees.size());
        
        Duree duree = crud.selectById(Duree.class, 1);
        System.out.println("duree: "+duree);
    }

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
