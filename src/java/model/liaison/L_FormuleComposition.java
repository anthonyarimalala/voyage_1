
package model.liaison;

import database.Connex;
import generalise.Column;
import generalise.CrudOperation;
import generalise.Table;
import java.sql.Connection;
import java.sql.SQLException;

@Table(name="l_formule_composition")
public class L_FormuleComposition {
    
    @Column(name = "id_formule_composition", autoIncrement = true, id = true)
    private int idFormuleComposition;

    @Column(name = "id_lieu")
    private int idLieu;
    
    @Column(name = "id_bouquet")
    private int idBouquet;
    
    @Column(name = "id_duree")
    private int idDuree;

    @Column(name = "id_activite")
    private int idActivite;

    @Column(name = "quantite")
    private double quantite;
    
    public static void main(String[] args) throws ClassNotFoundException, SQLException{
        Connection connection = Connex.getConnection();
        CrudOperation crud = new CrudOperation(connection);
        
        L_FormuleComposition formule = new L_FormuleComposition();
        formule.setIdBouquet(1);
        formule.setIdLieu(1);
        formule.setIdDuree(1);
        formule.setIdActivite(1);
        formule.setQuantite(1);
        
        crud.save(formule);
    }

    public L_FormuleComposition() {
    }

    public int getIdFormuleComposition() {
        return idFormuleComposition;
    }

    public void setIdFormuleComposition(int idFormuleComposition) {
        this.idFormuleComposition = idFormuleComposition;
    }

    public int getIdDuree() {
        return idDuree;
    }

    public void setIdDuree(int idDuree) {
        this.idDuree = idDuree;
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

    public double getQuantite() {
        return quantite;
    }

    public void setQuantite(double quantite) {
        this.quantite = quantite;
    }

    public int getIdLieu() {
        return idLieu;
    }

    public void setIdLieu(int idLieu) {
        this.idLieu = idLieu;
    }
    

    
    
    
    
    
    
}
