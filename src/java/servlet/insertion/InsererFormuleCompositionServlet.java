/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet.insertion;

import database.Connex;
import generalise.CrudOperation;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.liaison.L_FormuleComposition;
import model.vue.V_BouquetActivite;

/**
 *
 * @author PC
 */
public class InsererFormuleCompositionServlet extends HttpServlet {

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
            out.println("<title>Servlet InsererFormuleCompositionServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet InsererFormuleCompositionServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
            
             try{
                Connection connection = Connex.getConnection();
                CrudOperation crud = new CrudOperation(connection);
                
                int idLieu = Integer.parseInt(request.getParameter("idLieu"));
                int idBouquet = Integer.parseInt(request.getParameter("idBouquet"));
                int idDuree = Integer.parseInt(request.getParameter("idDuree"));
                
                List<V_BouquetActivite> v_bouquetServlet = crud.selectAllById(V_BouquetActivite.class , "id_bouquet", idBouquet);
                
                L_FormuleComposition formule = new L_FormuleComposition();
                formule.setIdLieu(idLieu);
                formule.setIdBouquet(idBouquet);
                formule.setIdDuree(idDuree);
                
                for(V_BouquetActivite ba: v_bouquetServlet){
                    formule.setIdActivite(ba.getIdActivite());
                    formule.setQuantite(Integer.parseInt(request.getParameter(String.valueOf(ba.getIdActivite()))));
                    crud.save(formule);
                }
                
                
                connection.close();
//                response.sendRedirect("ToInsererFormuleComposition");
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
