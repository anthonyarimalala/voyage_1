/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.vue;

import database.Connex;
import generalise.Column;
import generalise.CrudOperation;
import generalise.Table;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.voyage.Bouquet;
import model.voyage.Duree;
import model.voyage.Lieu;
import model.voyage.Stock;

/**
 *
 * @author PC
 */
@Table(name="v_stock")
public class V_Stock {
    
    @Column(name = "id_activite")
    private int idActivite;

    @Column(name = "activite")
    private String activite;

    @Column(name = "restant")
    private double restant;
    
    @Column(name = "prix_u")
    private double prixU;
    
    @Column(name = "prix_totale")
    private double prixTotale;
    
    public static void main(String[] args) throws ClassNotFoundException, SQLException, Exception{
        Connection connection = Connex.getConnection();
        CrudOperation crud = new CrudOperation(connection);
        
        System.out.println(crud.selectAll(V_Stock.class).size());
        
    }
    
    public static void enleverStock(Connection connection, int idVoyage, double quantite){
        
        
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        V_VoyageException v_voyageException = null;
        
        CrudOperation crud = new CrudOperation(connection);
        v_voyageException = crud.selectAllById(V_VoyageException.class, "id_voyage", idVoyage).get(0);
        System.out.println("v_voyage.size(): "+crud.selectAllById(V_VoyageException.class, "id_voyage", idVoyage).size());
        try{
            
            
            System.out.println("v_voyage: "+ v_voyageException);
            String query = "SELECT * FROM v_stock_quantite_reste WHERE id_voyage=?";
            preparedStatement = connection.prepareStatement(query);
            
            preparedStatement.setInt(1, idVoyage);
            resultSet = preparedStatement.executeQuery();
            
            while(resultSet.next()){
                
                int idActivite = resultSet.getInt("id_activite");
                double quantiteIlaina = resultSet.getDouble("quantite");
                double quantiteIlainaTot = quantiteIlaina * quantite;
                
                
                Stock stock = new Stock();
                stock.setEntree(0);
                stock.setSortie(quantiteIlainaTot);
                stock.setIdActivite(idActivite);
                
                
                crud.save(stock);
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    

    public V_Stock() {
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

    public double getRestant() {
        return restant;
    }

    public void setRestant(double restant) {
        this.restant = restant;
    }

    public double getPrixU() {
        return prixU;
    }

    public void setPrixU(double prixU) {
        this.prixU = prixU;
    }

    public double getPrixTotale() {
        return prixTotale;
    }

    public void setPrixTotale(double prixTotale) {
        this.prixTotale = prixTotale;
    }
    
    
}
