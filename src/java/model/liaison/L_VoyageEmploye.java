
package model.liaison;

import database.Connex;
import generalise.Column;
import generalise.CrudOperation;
import generalise.Table;
import java.sql.Connection;
import java.sql.SQLException;

@Table(name="l_voyage_employe")
public class L_VoyageEmploye {
    
    @Column(name="id_voyage_employe", autoIncrement = true, id = true)
    int idVoyageEmploye;
    
    @Column(name="id_voyage")
    int idVoyage;
    
    @Column(name="id_employe")
    int idEmploye;
    
    @Column(name="volume_h")
    double volumeH;
    
    public static void main(String[] args) throws ClassNotFoundException, SQLException{
        Connection connection = Connex.getConnection();
        CrudOperation crud = new CrudOperation(connection);
        
        L_VoyageEmploye voyageEmploye = new L_VoyageEmploye();
        voyageEmploye.setIdEmploye(1);
        voyageEmploye.setIdVoyage(1);
        voyageEmploye.setVolumeH(5);
        
        crud.save(voyageEmploye);
        
        
    }

    public L_VoyageEmploye() {
    }

    public int getIdVoyageEmploye() {
        return idVoyageEmploye;
    }

    public void setIdVoyageEmploye(int idVoyageEmploye) {
        this.idVoyageEmploye = idVoyageEmploye;
    }

    public int getIdVoyage() {
        return idVoyage;
    }

    public void setIdVoyage(int idVoyage) {
        this.idVoyage = idVoyage;
    }

    public int getIdEmploye() {
        return idEmploye;
    }

    public void setIdEmploye(int idEmploye) {
        this.idEmploye = idEmploye;
    }

    public double getVolumeH() {
        return volumeH;
    }

    public void setVolumeH(double volumeH) {
        this.volumeH = volumeH;
    }
    
    
}
