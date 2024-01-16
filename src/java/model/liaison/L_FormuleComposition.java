
package model.liaison;

import generalise.Column;
import generalise.Table;

@Table(name="l_formule_composition")
public class L_FormuleComposition {
    
    @Column(name = "id_formule_composition", autoIncrement = true, id = true)
    private int idFormuleComposition;

    @Column(name = "id_duree")
    private int idDuree;

    @Column(name = "id_bouquet")
    private int idBouquet;

    @Column(name = "id_activite")
    private int idActivite;

    @Column(name = "quantite")
    private double quantite;

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

    
    
    
    
    
    
}
