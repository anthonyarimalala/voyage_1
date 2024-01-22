
package model.voyage;

import database.Connex;
import generalise.Column;
import generalise.CrudOperation;
import generalise.Table;
import java.sql.Connection;
import java.sql.SQLException;

@Table(name="employe")
public class Employe {
    
    @Column(name="id_employe", autoIncrement = true, id = true)
    int idEmploye;
    
    @Column(name="nom")
    String nom;
    
    @Column(name="fonction")
    String fonction;
    
    @Column(name="prix")
    double prix;
    
    public static void main(String[] args) throws ClassNotFoundException, SQLException{
        Connection connection = Connex.getConnection();
        CrudOperation crud = new CrudOperation(connection);
        
        Employe employe = new Employe();
        employe.setNom("Sahondra");
        employe.setFonction("Menage");
        employe.setPrix(7000);
        
        String id = crud.saveReturn(employe);
        System.out.println(id);
    }

    public Employe() {
    }

    public int getIdEmploye() {
        return idEmploye;
    }

    public void setIdEmploye(int idEmploye) {
        this.idEmploye = idEmploye;
    }

    public String getFonction() {
        return fonction;
    }

    public void setFonction(String fonction) {
        this.fonction = fonction;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    
    
    
    
}
