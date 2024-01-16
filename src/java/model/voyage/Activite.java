
package model.voyage;

import generalise.Column;
import generalise.Table;
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
    
    public static Activite mapFromResultSet(ResultSet rs) throws SQLException {
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
