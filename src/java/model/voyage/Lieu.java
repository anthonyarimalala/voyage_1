
package model.voyage;

import generalise.Column;
import generalise.Table;

@Table(name="lieu")
public class Lieu {
    
    @Column(name="id_lieu", autoIncrement = true, id = true)
    int idLieu;
    
    @Column(name="lieu")
    String lieu;

    public Lieu() {
    }

    public int getIdLieu() {
        return idLieu;
    }

    public void setIdLieu(int idLieu) {
        this.idLieu = idLieu;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }
    
    
}
