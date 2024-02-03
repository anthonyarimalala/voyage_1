
package model.vue;

import database.Connex;
import generalise.Column;
import generalise.CrudOperation;
import generalise.Table;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Table(name="v_formule_composition")
public class V_FormuleComposition {
    
    @Column(name = "id_formule_composition")
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
    
    @Column(name="lieu")
    private String lieu;
    
    @Column(name="duree")
    private String duree;
    
    @Column(name="bouquet")
    private String bouquet;
    
    @Column(name="activite")
    private String activite;
    
    public static void main(String[] args) throws ClassNotFoundException, SQLException{
        Connection connection = Connex.getConnection();
        CrudOperation crud = new CrudOperation(connection);
        
        List<V_FormuleComposition> fo = crud.selectAll(V_FormuleComposition.class);
        System.out.println(fo.size());
    }

     public static List<V_FormuleComposition> selectAll(Connection connection, int idLieu, int idBouquet, int idDuree) throws SQLException {
        List<V_FormuleComposition> compositions = new ArrayList<>();
        String query = "SELECT * FROM v_formule_composition WHERE id_lieu = ? AND id_bouquet = ? AND id_duree = ?";
        
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, idLieu);
            statement.setInt(2, idBouquet);
            statement.setInt(3, idDuree);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                V_FormuleComposition composition = new V_FormuleComposition();
                composition.setIdFormuleComposition(resultSet.getInt("id_formule_composition"));
                composition.setIdLieu(resultSet.getInt("id_lieu"));
                composition.setIdBouquet(resultSet.getInt("id_bouquet"));
                composition.setIdDuree(resultSet.getInt("id_duree"));
                composition.setIdActivite(resultSet.getInt("id_activite"));
                composition.setQuantite(resultSet.getDouble("quantite"));
                composition.setLieu(resultSet.getString("lieu"));
                composition.setDuree(resultSet.getString("duree"));
                composition.setBouquet(resultSet.getString("bouquet"));
                composition.setActivite(resultSet.getString("activite"));
                compositions.add(composition);
            }
        }
        return compositions;
    }
     
    public V_FormuleComposition() {
    }

    public int getIdFormuleComposition() {
        return idFormuleComposition;
    }

    public void setIdFormuleComposition(int idFormuleComposition) {
        this.idFormuleComposition = idFormuleComposition;
    }

    public int getIdLieu() {
        return idLieu;
    }

    public void setIdLieu(int idLieu) {
        this.idLieu = idLieu;
    }

    public int getIdBouquet() {
        return idBouquet;
    }

    public void setIdBouquet(int idBouquet) {
        this.idBouquet = idBouquet;
    }

    public int getIdDuree() {
        return idDuree;
    }

    public void setIdDuree(int idDuree) {
        this.idDuree = idDuree;
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

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public String getDuree() {
        return duree;
    }

    public void setDuree(String duree) {
        this.duree = duree;
    }

    public String getBouquet() {
        return bouquet;
    }

    public void setBouquet(String bouquet) {
        this.bouquet = bouquet;
    }

    public String getActivite() {
        return activite;
    }

    public void setActivite(String activite) {
        this.activite = activite;
    }
    
    
}
