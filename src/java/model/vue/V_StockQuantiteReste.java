
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


@Table(name = "v_stock_quantite_reste")
public class V_StockQuantiteReste {

    @Column(name = "id_voyage")
    private int idVoyage;

    @Column(name = "id_bouquet")
    private int idBouquet;

    @Column(name = "id_lieu")
    private int idLieu;

    @Column(name = "id_duree")
    private int idDuree;

    @Column(name = "prix")
    private double prix;

    @Column(name = "voyage")
    private String voyage;

    @Column(name = "description")
    private String description;

    @Column(name = "lieu")
    private String lieu;

    @Column(name = "bouquet")
    private String bouquet;

    @Column(name = "duree")
    private String duree;

    @Column(name = "activite")
    private String activite;

    @Column(name = "id_activite")
    private int idActivite;

    @Column(name = "quantite")
    private double quantite;

    @Column(name = "restant")
    private double restant;
    
    private double quantiteHiala;
    
    
    public static void main(String[] args) throws ClassNotFoundException, SQLException, Exception{
        Connection connection = Connex.getConnection();
        CrudOperation crud = new CrudOperation(connection);
        
        crud.selectAll(V_StockQuantiteReste.class);
        
        ampy(connection, 1, 1);
    }
    
    public static List<V_StockQuantiteReste> ampy(Connection connection, int idVoyage, double quantiteVoyage) throws Exception{
        List<V_StockQuantiteReste> myList = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        V_VoyageException v_voyageException = null;
        
        int o = -1;
        CrudOperation crud = new CrudOperation(connection);
        v_voyageException = crud.selectAllById(V_VoyageException.class, "id_voyage", idVoyage).get(0);
            String query = "SELECT * FROM v_stock_quantite_reste WHERE id_voyage=?";
            preparedStatement = connection.prepareStatement(query);
            
            preparedStatement.setInt(1, idVoyage);
            resultSet = preparedStatement.executeQuery();
            
            while(resultSet.next()){
                o++;
                String activite = resultSet.getString("activite");
                
                double quantiteIlaina = resultSet.getDouble("quantite");
                double quantiteIlainaTot = quantiteIlaina * quantiteVoyage;
                double restant = resultSet.getDouble("restant");
                
                if(quantiteIlainaTot <= restant){
                    V_StockQuantiteReste stock = mapFromResultSet(resultSet, quantiteVoyage);
                    myList.add(stock);
                }else{
                    String myException = "Nombre de "+activite+" n'est plus suffisant pour ce voyage!\n"
                            + activite +" ilaina: "+ quantiteIlaina * quantiteVoyage+" / \n"
                            + "Restant: "+restant;
                    throw new Exception(myException);
                }
                
            }
            
        if(o == -1){
            throw new Exception("Vous devez d'abord définir une formule de ce voyage: "+v_voyageException.getLieu()+" - "+v_voyageException.getBouquet()+" - "+v_voyageException.getDuree());
            //throw new Exception("Vous devez d'abord définir une formule de ce voyage: ");
        }
        return myList;
    }
    
    public static V_StockQuantiteReste mapFromResultSet(ResultSet resultSet, double quantiteVoyage) throws SQLException {
        V_StockQuantiteReste vStock = new V_StockQuantiteReste();

        vStock.setIdVoyage(resultSet.getInt("id_voyage"));
        vStock.setIdBouquet(resultSet.getInt("id_bouquet"));
        vStock.setIdLieu(resultSet.getInt("id_lieu"));
        vStock.setIdDuree(resultSet.getInt("id_duree"));
        vStock.setPrix(resultSet.getDouble("prix"));
        vStock.setVoyage(resultSet.getString("voyage"));
        vStock.setDescription(resultSet.getString("description"));
        vStock.setLieu(resultSet.getString("lieu"));
        vStock.setBouquet(resultSet.getString("bouquet"));
        vStock.setDuree(resultSet.getString("duree"));
        vStock.setActivite(resultSet.getString("activite"));
        vStock.setIdActivite(resultSet.getInt("id_activite"));
        vStock.setQuantite(resultSet.getDouble("quantite"));
        vStock.setRestant(resultSet.getDouble("restant"));
        vStock.setQuantiteHiala(quantiteVoyage * resultSet.getDouble("quantite"));

        return vStock;
    }

    public V_StockQuantiteReste() {
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

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public String getBouquet() {
        return bouquet;
    }

    public void setBouquet(String bouquet) {
        this.bouquet = bouquet;
    }

    public String getDuree() {
        return duree;
    }

    public void setDuree(String duree) {
        this.duree = duree;
    }

    public String getActivite() {
        return activite;
    }

    public void setActivite(String activite) {
        this.activite = activite;
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

    
  

    public double getRestant() {
        return restant;
    }

    public void setRestant(double restant) {
        this.restant = restant;
    }

    public double getQuantiteHiala() {
        return quantiteHiala;
    }

    public void setQuantiteHiala(double quantiteHiala) {
        this.quantiteHiala = quantiteHiala;
    }
    
    
     
}