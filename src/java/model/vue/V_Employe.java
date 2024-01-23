
package model.vue;

import database.Connex;
import generalise.Column;
import generalise.Table;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;

@Table(name="v_employe")
public class V_Employe {
    @Column(name="id_employe", autoIncrement = true, id = true)
    int idEmploye;
    
    @Column(name="nom")
    String nom;
    
    @Column(name="fonction")
    String fonction;
    
    @Column(name="date_embauche")
    Date dateEmbauche;
    
    @Column(name="prix")
    double prix;
    
    @Column(name="annee_experience")
    int anneeExperience;
    
    @Column(name="experience")
    String experience;
    
    @Column(name="multipl")
    double multipl;
    
    @Column(name="new_prix")
    double newPrix;
    
    public static void main(String[] args) throws ClassNotFoundException, SQLException{
        Connection connection = Connex.getConnection();
        
    }

    public V_Employe() {
    }

    public int getIdEmploye() {
        return idEmploye;
    }

    public void setIdEmploye(int idEmploye) {
        this.idEmploye = idEmploye;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getFonction() {
        return fonction;
    }

    public void setFonction(String fonction) {
        this.fonction = fonction;
    }

    public Date getDateEmbauche() {
        return dateEmbauche;
    }

    public void setDateEmbauche(Date dateEmbauche) {
        this.dateEmbauche = dateEmbauche;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public int getAnneeExperience() {
        return anneeExperience;
    }

    public void setAnneeExperience(int anneeExperience) {
        this.anneeExperience = anneeExperience;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public double getMultipl() {
        return multipl;
    }

    public void setMultipl(double multipl) {
        this.multipl = multipl;
    }

    public double getNewPrix() {
        return newPrix;
    }

    public void setNewPrix(double newPrix) {
        this.newPrix = newPrix;
    }
    
    
}
