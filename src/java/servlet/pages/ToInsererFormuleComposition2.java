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
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.liaison.L_BouquetActivite;
import model.voyage.Bouquet;
import model.voyage.Duree;
import model.voyage.Lieu;
import model.vue.V_BouquetActivite;

public class ToInsererFormuleComposition2 extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ToInsererFormuleComposition2</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ToInsererFormuleComposition2 at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
            
             try{
                Connection connection = Connex.getConnection();
                CrudOperation crud = new CrudOperation(connection);
                
                int idLieu = Integer.parseInt(request.getParameter("idLieu"));
                int idBouquet = Integer.parseInt(request.getParameter("idBouquet"));
                int idDuree = Integer.parseInt(request.getParameter("idDuree"));
                
                out.println("idLieu: "+idLieu+"<br/>");
                out.println("idBouquet: "+idBouquet+"<br/>");
                out.println("idDuree: "+idDuree+"<br/>");
                
                Lieu lieu = crud.selectById(Lieu.class, idLieu);
                Bouquet bouquet = crud.selectById(Bouquet.class, idBouquet);
                Duree duree = crud.selectById(Duree.class, idDuree );
//                
                out.println("lieu: "+lieu.getLieu()+"<br/>");
                out.println("bouquet: "+bouquet.getBouquet()+"<br/>");
                out.println("duree: "+duree.getDuree()+"/n");
//                
//                
                request.setAttribute("lieu", lieu);
                request.setAttribute("bouquet", bouquet);
                request.setAttribute("duree", duree);
//                
                
                List<V_BouquetActivite> v_bouquetActivite = crud.selectAllById(V_BouquetActivite.class, "id_bouquet", idBouquet);
                request.setAttribute("v_bouquetActivites", v_bouquetActivite);
                
                connection.close();
                RequestDispatcher dispatcher = request.getRequestDispatcher("pages/inserer/insererFormuleComposition2.jsp");
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
