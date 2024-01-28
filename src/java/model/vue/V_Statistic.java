/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.vue;

import generalise.Column;
import generalise.Table;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author PC
 */
@Table(name="v_statistic")
public class V_Statistic {
    
    @Column(name="id_voyage")
    int idVoyage;
    
    @Column(name="voyage")
    String voyage;
    
    @Column(name="somme_femme")
    double sommeFemme;
    
    @Column(name="somme_homme")
    double sommeHomme;
    
    public static V_Statistic selectByIdVoyage(Connection connection, int idVoyage) throws SQLException {
        V_Statistic vStatistic = null;

        // Requête SQL SELECT avec une clause WHERE pour filtrer par id_voyage
        String sql = "SELECT * FROM v_statistic WHERE id_voyage = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            // Paramètre pour la clause WHERE
            preparedStatement.setInt(1, idVoyage);

            // Exécution de la requête
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                // Vérification s'il y a une ligne résultante
                if (resultSet.next()) {
                    // Création d'une instance de VStatistic avec les données de la ligne résultante
                    vStatistic = new V_Statistic();
                    vStatistic.setIdVoyage(resultSet.getInt("id_voyage"));
                    vStatistic.setVoyage(resultSet.getString("voyage"));
                    vStatistic.setSommeFemme(resultSet.getDouble("somme_femme"));
                    vStatistic.setSommeHomme(resultSet.getDouble("somme_homme"));
                }
            }
        }

        return vStatistic;
    }

    public V_Statistic() {
    }

    public int getIdVoyage() {
        return idVoyage;
    }

    public void setIdVoyage(int idVoyage) {
        this.idVoyage = idVoyage;
    }

    public String getVoyage() {
        return voyage;
    }

    public void setVoyage(String voyage) {
        this.voyage = voyage;
    }

    public double getSommeFemme() {
        return sommeFemme;
    }

    public void setSommeFemme(double sommeFemme) {
        this.sommeFemme = sommeFemme;
    }

    public double getSommeHomme() {
        return sommeHomme;
    }

    public void setSommeHomme(double sommeHomme) {
        this.sommeHomme = sommeHomme;
    }
    
    
    
}
