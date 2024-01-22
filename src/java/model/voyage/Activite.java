
package model.voyage;

import database.Connex;
import generalise.Column;
import generalise.CrudOperation;
import generalise.Table;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

@Table(name="activite")
public class Activite {
    
    @Column(name="id_activite", autoIncrement = true, id = true)
    int idActivite;
    
    @Column(name="activite")
    String activite;
    
    @Column(name="prix_u")
    double prixU;
    
    public static void main(String[] args) throws ClassNotFoundException, SQLException{
        Connection connection = Connex.getConnection();
        CrudOperation crud = new CrudOperation(connection);
        
        Activite activite = new Activite();
        activite.setActivite("Quad");
        activite.setPrixU(20000);
        
        System.out.println("id: "+crud.saveReturn(activite));
    }
    
    public static Activite mapFromResultSet(ResultSet rs) throws SQLException{
        
        
        Activite activite = new Activite();
        
        activite.setIdActivite(rs.getInt("id_activite"));
        activite.setActivite(rs.getString("activite"));
        activite.setPrixU(rs.getDouble("prix_u"));
        
        return activite;
    }

    public Activite() {
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
