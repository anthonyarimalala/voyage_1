
package generalise;

import java.sql.Date;
import java.sql.Time;

@Table(name = "personne")
public class Personne {
    
    @Column(name="id_personne", autoIncrement = true, id = true)
    int idPersonne;
    @Column(name="nom")
    String nom;
    @Column(name="date_naissance")
    Date dateNaissance;

    public Personne(int idPersonne, String nom, Date dateNaissance) {
        this.idPersonne = idPersonne;
        this.nom = nom;
        this.dateNaissance = dateNaissance;
    }

    public Personne(String nom, Date dateNaissance) {
        this.nom = nom;
        this.dateNaissance = dateNaissance;
    }
    
    

    public Personne() {
    }

    public int getIdPersonne() {
        return idPersonne;
    }

    public void setIdPersonne(int idPersonne) {
        this.idPersonne = idPersonne;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    @Override
    public String toString() {
        return "Personne{" + "idPersonne=" + idPersonne + ", nom=" + nom + ", dateNaissance=" + dateNaissance + '}';
    }
    
    
}
