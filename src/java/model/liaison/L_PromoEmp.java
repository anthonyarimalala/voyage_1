
package model.liaison;

import database.Connex;
import generalise.Column;
import generalise.CrudOperation;
import generalise.Table;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import model.voyage.PromoEmp;

@Table(name="l_promo_emp")
public class L_PromoEmp {
    
    @Column(name="id_l_promotion", autoIncrement = true, id = true)
    int idLPromoEmp;
    
    @Column(name="id_promotion")
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
        
        System.out.println(crud.selectAll(L_PromoEmp.class));
//        L_PromoEmp promoEmp = new L_PromoEmp();
//        promoEmp.setIdPromotion(1);
//        promoEmp.setAnneeRequis(0);
//        promoEmp.setMultipl(1);
//        promoEmp.setPromotion("Senior");
//        
//        crud.save(promoEmp);
        updateL_PromoEmp(crud);
        
    }
    
    public static void updateL_PromoEmp(CrudOperation crud){
        List<PromoEmp> promoEmps = PromoEmp.selectAllByAnneeRequisAsc(crud.getConnection());
        System.out.println("promoEmps.size(): "+promoEmps.size());
        crud.deleteAll(L_PromoEmp.class);
        
        L_PromoEmp l_promoEmp = new L_PromoEmp();
        
        int maxAnneeRequis = 0;
        int idPromotion = 0;
        String promotion = "";
        int anneeRequis = 0;
        double multipl = 0;
        
        if(promoEmps.size()>0){ 
            maxAnneeRequis = promoEmps.get(promoEmps.size()-1).getAnneeRequis();
            idPromotion = promoEmps.get(0).getIdPromotion();
            promotion = promoEmps.get(0).getPromotion();
            anneeRequis = promoEmps.get(0).getAnneeRequis();
            multipl = promoEmps.get(0).getMultipl();
            
            for(int i=0; i<=maxAnneeRequis; i++){
                l_promoEmp.setAnneeRequis(i);
                l_promoEmp.setIdPromotion(idPromotion);
                l_promoEmp.setPromotion(promotion);
                l_promoEmp.setMultipl(multipl);
            
                crud.save(l_promoEmp);
                
            }
        }
        for(int i=1; i<promoEmps.size(); i++){
            idPromotion = promoEmps.get(i).getIdPromotion();
            promotion = promoEmps.get(i).getPromotion();
            anneeRequis = promoEmps.get(i).getAnneeRequis();
            multipl = promoEmps.get(i).getMultipl();
            for(int j=anneeRequis; j<=maxAnneeRequis; j++){
                l_promoEmp.setAnneeRequis(j);
                l_promoEmp.setIdPromotion(idPromotion);
                l_promoEmp.setPromotion(promotion);
                l_promoEmp.setMultipl(multipl);
                update(crud, l_promoEmp, j);
                System.out.println("j: "+j);
            }
        }
        
        System.out.println("maxAnneeRequis: "+maxAnneeRequis);
        
        
    
    }
    
    public static void update(CrudOperation crud, L_PromoEmp promoEmp, int anneeRequis){
        crud.update(promoEmp, "annee_requis", anneeRequis);
    }

    public L_PromoEmp() {
    }

    public int getIdLPromoEmp() {
        return idLPromoEmp;
    }

    public void setIdLPromoEmp(int idLPromoEmp) {
        this.idLPromoEmp = idLPromoEmp;
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
