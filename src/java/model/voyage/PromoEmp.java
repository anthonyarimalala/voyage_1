package model.voyage;

import database.Connex;
import generalise.Column;
import generalise.CrudOperation;
import generalise.Table;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Table(name="promo_emp")
public class PromoEmp {
    
    @Column(name="id_promotion", autoIncrement = true, id = true)
    int idPromotion;
    
    @Column(name="promotion")
    String promotion;
    
    @Column(name="annee_requis")
    int anneeRequis;
    
    @Column(name="multipl")
    double multipl;
    
    public static void main(String[] args) throws ClassNotFoundException, SQLException{
        Connection connection = Connex.getConnection();
        CrudOperation crud = new CrudOperation(connection);
        
        System.out.println(crud.selectAll(PromoEmp.class));
        
        PromoEmp promoEmp = crud.selectById(PromoEmp.class, 4);
        promoEmp.setAnneeRequis(0);
        
        //crud.update(promoEmp, 4);
        
        List<PromoEmp> promoEmps = selectAllByAnneeRequisDesc(connection);
        
        System.out.println("size(): "+promoEmps.size());
    }
    
    public static List<PromoEmp> selectAllByAnneeRequisAsc(Connection connection) {
        List<PromoEmp> promoEmpList = new ArrayList<>();

        try {
            String query = "SELECT * FROM promo_emp ORDER BY annee_requis ASC";
            try (PreparedStatement statement = connection.prepareStatement(query);
                 ResultSet resultSet = statement.executeQuery()) {

                while (resultSet.next()) {
                    PromoEmp promoEmp = PromoEmp.mapFromResultSet(resultSet);
                    promoEmpList.add(promoEmp);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return promoEmpList;
    }
    
    public static List<PromoEmp> selectAllByAnneeRequisDesc(Connection connection) {
        List<PromoEmp> promoEmpList = new ArrayList<>();

        try {
            String query = "SELECT * FROM promo_emp ORDER BY annee_requis DESC";
            try (PreparedStatement statement = connection.prepareStatement(query);
                 ResultSet resultSet = statement.executeQuery()) {

                while (resultSet.next()) {
                    PromoEmp promoEmp = PromoEmp.mapFromResultSet(resultSet);
                    promoEmpList.add(promoEmp);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return promoEmpList;
    }
      
     public static PromoEmp mapFromResultSet (ResultSet resultSet) throws SQLException{
         int idPromotion = resultSet.getInt("id_promotion");
            String promotion = resultSet.getString("promotion");
            int anneeRequis = resultSet.getInt("annee_requis");
            double multipl = resultSet.getDouble("multipl");
            
            PromoEmp promo = new PromoEmp();
            promo.setAnneeRequis(anneeRequis);
            promo.setIdPromotion(idPromotion);
            promo.setMultipl(multipl);
            promo.setPromotion(promotion);
            
            return promo;
            
            
     }

    public PromoEmp() {
    }

    public int getIdPromotion() {
        return idPromotion;
    }

    public void setIdPromotion(int idPromotion) {
        this.idPromotion = idPromotion;
    }

    public String getPromotion() {
        return promotion;
    }

    public void setPromotion(String promotion) {
        this.promotion = promotion;
    }

    public int getAnneeRequis() {
        return anneeRequis;
    }

    public void setAnneeRequis(int anneeRequis) {
        this.anneeRequis = anneeRequis;
    }

    public double getMultipl() {
        return multipl;
    }

    public void setMultipl(double multipl) {
        this.multipl = multipl;
    }
    
    
    
    
}
