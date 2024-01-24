/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet.modification;

import database.Connex;
import generalise.CrudOperation;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.liaison.L_PromoEmp;
import model.voyage.PromoEmp;

/**
 *
 * @author PC
 */
public class ModifierPromotionServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ModifierPromotionServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ModifierPromotionServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
            try{
                Connection connection = Connex.getConnection();
                CrudOperation crud = new CrudOperation(connection);
                
                String successMessage = "";
                PromoEmp promoEmp = new PromoEmp();
                
                String action = request.getParameter("action");
                if(action.equals("update")){
                    int idPromotion = Integer.parseInt(request.getParameter("idPromotion"));
                    int anneeRequis = Integer.parseInt(request.getParameter("anneeRequis"));
                    double multipl = Double.parseDouble(request.getParameter("multipl"));
                    
                    promoEmp = crud.selectById(PromoEmp.class, idPromotion);
                    promoEmp.setAnneeRequis(anneeRequis);
                    promoEmp.setMultipl(multipl);
                    crud.update(promoEmp, idPromotion);
                    
                    successMessage = "<strong>Mis à jour</strong> avec success";
                    out.println("<br/>idPromotion: "+idPromotion);
                    out.println("<br/>anneeRequis: "+anneeRequis);
                    out.println("<br/>Update");
                    
                    
                    
                }
                else if(action.equals("delete")){
                    int idPromotion = Integer.parseInt(request.getParameter("idPromotion"));
                    crud.delete(PromoEmp.class, idPromotion);
                    successMessage = "<strong>Supprimé</strong> avec success";
                    out.println("<br/>Delete");
                }
                else if(action.equals("insert")){
                    String promotion = request.getParameter("promotion");
                    int anneeRequis = Integer.parseInt(request.getParameter("anneeRequis"));
                    promoEmp.setPromotion(promotion);
                    promoEmp.setAnneeRequis(anneeRequis);
                    double multipl = Double.parseDouble(request.getParameter("multipl"));
                    promoEmp.setMultipl(multipl);
                    crud.save(promoEmp);
                    successMessage = "<strong>Sauvegardé</strong> avec success";
                    out.println("<br/>Insert");
                }
                
                L_PromoEmp.updateL_PromoEmp(crud);
                request.setAttribute("successMessage", successMessage);
                
                connection.close();
                RequestDispatcher dispatcher = request.getRequestDispatcher("ToModifierPromotion");
                dispatcher.forward(request, response);
                
                
            }catch(Exception e){
                e.printStackTrace(out);
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
