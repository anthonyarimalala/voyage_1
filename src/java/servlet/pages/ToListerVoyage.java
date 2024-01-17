/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet.pages;

import database.Connex;
import generalise.CrudOperation;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.voyage.Activite;
import model.vue.V_Voyage;

/**
 *
 * @author PC
 */
public class ToListerVoyage extends HttpServlet {

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
            out.println("<title>Servlet ToListerVoyage</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ToListerVoyage at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
            
            try{
                Connection connection = Connex.getConnection();
                CrudOperation crud = new CrudOperation(connection);
                
                List<Activite> activites = crud.selectAll(Activite.class);
                request.setAttribute("activites", activites);
                
                String idActiviteStr = request.getParameter("idActivite");
                String prixMinStr = request.getParameter("prixMin");
                String prixMaxStr = request.getParameter("prixMax");
                
                List<V_Voyage> v_voyages = new ArrayList<>();
                
                if(idActiviteStr!=null && !idActiviteStr.isEmpty()){
                    int idActivite = Integer.parseInt(idActiviteStr);
                    v_voyages = V_Voyage.getAllVoyageByIdActivite(connection, idActivite);
                    
                }
                else if(prixMinStr!=null && !prixMinStr.isEmpty() && prixMaxStr!=null && !prixMaxStr.isEmpty()){
                    double prixMin = Double.parseDouble(prixMinStr);
                    double prixMax = Double.parseDouble(prixMaxStr);
                    
                    v_voyages = V_Voyage.getAllVoyageByTotalActiviteMinMax(connection, prixMin, prixMax);
                }
                else{
                    v_voyages  = crud.selectAll(V_Voyage.class);
                }

                
                request.setAttribute("v_voyages", v_voyages);
                
                
                connection.close();
                RequestDispatcher dispatcher = request.getRequestDispatcher("pages/lister/listerVoyage.jsp");
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
