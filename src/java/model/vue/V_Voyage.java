
package model.vue;

import generalise.Column;
import generalise.CrudOperation;
import generalise.Table;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.liaison.L_BouquetActivite;
import model.utils.U_BouquetActivite;
import model.voyage.Activite;
import model.voyage.Bouquet;

@Table(name="v_voyage")
public class V_Voyage {
    
    @Column(name="id_voyage")
    int idVoyage;

    @Column(name="id_bouquet")
    int idBouquet;

    @Column(name="id_lieu")
    int idLieu;

    @Column(name="id_duree")
    int idDuree;

    @Column(name="prix")
    double prix;

    @Column(name="voyage")
    String voyage;

    @Column(name="description")
    String description;
    
    @Column (name="p_total_activite")
    double pTotalActivite;
    
    @Column (name="p_total_employe")
    double pTotalEmploye;
    
    @Column (name="benefice")
    double benefice;
    
    public List<V_Voyage> getAllVoyageByBeneficeMinMax(Connection connection, double min, double max) {
        List<V_Voyage> result = new ArrayList<>();

        String sql = "SELECT * " +
                     "FROM v_voyage " +
                     "WHERE ? <= benefice AND benefice <= ? " +
                     "GROUP BY id_voyage, id_bouquet, id_lieu, id_duree, prix, voyage, description";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setDouble(1, min);
            statement.setDouble(2, max);

            try (ResultSet resultSet = statement.executeQuery()) {

                while (resultSet.next()) {
                    V_Voyage voyage = mapFromResultSet(resultSet);
                    result.add(voyage);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Gérer les exceptions de manière appropriée dans un environnement de production
        }

        return result;
    }
    
    public List<V_Voyage> getAllVoyageByTotalActiviteMinMax(Connection connection, double min, double max) {
        List<V_Voyage> result = new ArrayList<>();

        String sql = "SELECT * " +
                     "FROM v_voyage " +
                     "WHERE ? <= p_total_activite AND p_total_activite <= ? " +
                     "GROUP BY id_voyage, id_bouquet, id_lieu, id_duree, prix, voyage, description";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setDouble(1, min);
            statement.setDouble(2, max);

            try (ResultSet resultSet = statement.executeQuery()) {

                while (resultSet.next()) {
                    V_Voyage voyage = mapFromResultSet(resultSet);
                    result.add(voyage);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }
    
    public List<V_Voyage> getAllVoyageByIdActivite(Connection connection, int idActivite) {
        List<V_Voyage> result = new ArrayList<>();

        String sql = "SELECT * " +
                     "FROM v_voyage " +
                     "WHERE id_activite = ? " +
                     "GROUP BY id_voyage, id_bouquet, id_lieu, id_duree, prix, voyage, description";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, idActivite);

            try (ResultSet resultSet = statement.executeQuery()) {

                while (resultSet.next()) {
                    V_Voyage voyage = mapFromResultSet(resultSet);
                    result.add(voyage);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }
    
    public static V_Voyage mapFromResultSet(ResultSet rs) throws SQLException {
        V_Voyage voyage = new V_Voyage();

        voyage.setIdVoyage(rs.getInt("id_voyage"));
        voyage.setIdBouquet(rs.getInt("id_bouquet"));
        voyage.setIdLieu(rs.getInt("id_lieu"));
        voyage.setIdDuree(rs.getInt("id_duree"));
        voyage.setPrix(rs.getDouble("prix"));
        voyage.setVoyage(rs.getString("voyage"));
        voyage.setDescription(rs.getString("description"));
        voyage.setpTotalActivite(rs.getDouble("p_total_activite"));
        voyage.setpTotalEmploye(rs.getDouble("p_total_employe"));

        return voyage;
    }

    public V_Voyage() {
    }

    public int getIdVoyage() {
        return idVoyage;
    }

    public void setIdVoyage(int idVoyage) {
        this.idVoyage = idVoyage;
    }

    public int getIdBouquet() {
        return idBouquet;
    }

    public void setIdBouquet(int idBouquet) {
        this.idBouquet = idBouquet;
    }

    public int getIdLieu() {
        return idLieu;
    }

    public void setIdLieu(int idLieu) {
        this.idLieu = idLieu;
    }

    public int getIdDuree() {
        return idDuree;
    }

    public void setIdDuree(int idDuree) {
        this.idDuree = idDuree;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public String getVoyage() {
        return voyage;
    }

    public void setVoyage(String voyage) {
        this.voyage = voyage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getpTotalActivite() {
        return pTotalActivite;
    }

    public void setpTotalActivite(double pTotalActivite) {
        this.pTotalActivite = pTotalActivite;
    }

    public double getpTotalEmploye() {
        return pTotalEmploye;
    }

    public void setpTotalEmploye(double pTotalEmploye) {
        this.pTotalEmploye = pTotalEmploye;
    }
    
    

   
    
    
    
}
