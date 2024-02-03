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
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.voyage.Activite;
import model.voyage.Reservation;
import model.voyage.Stock;
import model.vue.V_Stock;
import model.vue.V_StockQuantiteReste;
import model.vue.V_Voyage;

/**
 *
 * @author PC
 */
public class InsererReservationServlet extends HttpServlet {

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
            out.println("<title>Servlet InsererReservationServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet InsererReservationServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
            
            try{
                Connection connection = Connex.getConnection();
                CrudOperation crud = new CrudOperation(connection);
                
                double quantite = Double.parseDouble(request.getParameter("quantite"));
                
                int idClient = Integer.parseInt(request.getParameter("idClient"));
                
                int idVoyage = Integer.parseInt(request.getParameter("idVoyage"));
                List<V_StockQuantiteReste> quantiteReste = V_StockQuantiteReste.ampy(connection, idVoyage, quantite);
                
                Stock stock = new Stock();
                
                for(int i=0; i<quantiteReste.size(); i++){
                    stock.setIdActivite(quantiteReste.get(i).getIdActivite());
                    stock.setEntree(0);
                    stock.setSortie(quantiteReste.get(i).getQuantiteHiala());
                    stock.setDateModif(new Date(System.currentTimeMillis()));
                    
                    crud.save(stock);
                }
                Reservation reservation = new Reservation();
                reservation.setIdVoyage(idVoyage);
                reservation.setDateReservation(new Date(System.currentTimeMillis()));
                reservation.setIdClient(idClient);
                reservation.setQuantite(quantite);
                reservation.setEtat(5);
                
                crud.save(reservation);
                
                request.setAttribute("successMessage", "Vous avez réservé pour ce voyage");
                
                
                connection.close();
                
            }catch(Exception e){
                request.setAttribute("errorMessage", e.getMessage());
            }
            
            RequestDispatcher dispatcher = request.getRequestDispatcher("ToListerVoyage");
            dispatcher.forward(request, response);
            
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
