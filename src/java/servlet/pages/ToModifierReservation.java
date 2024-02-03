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
import model.voyage.Voyage;
import model.vue.V_Reservation;

/**
 *
 * @author PC
 */
public class ToModifierReservation extends HttpServlet {

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
            out.println("<title>Servlet ToModifierReservation</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ToModifierReservation at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
            
            try{
                Connection connection = Connex.getConnection();
                CrudOperation crud = new CrudOperation(connection);
                
                List<V_Reservation> reservations = new ArrayList<>();
                List<Voyage> voyages = crud.selectAll(Voyage.class);
                
                String tableTitle = "";
                
                String action = request.getParameter("action");
                String idVoyage = request.getParameter("idVoyage");
                
                if(action!=null && !action.isEmpty()){
                    if(action.equals("wait")){
                        reservations = V_Reservation.selectAllReservationWait(connection);
                        tableTitle = "Les reservations en attentes";
                    }else if(action.equals("validate")){
                        reservations = V_Reservation.selectAllReservationValidate(connection);
                        tableTitle = "Les reservations en validés";
                    }else if(action.equals("dismissed")){
                        reservations = V_Reservation.selectAllReservationDismissed(connection);
                        tableTitle = "Les reservations en annulés";
                    }else if(action.equals("all")){
                        reservations = V_Reservation.selectAllReservation(connection);
                        tableTitle = "Toutes les réservations";
                    }else{
                        reservations = V_Reservation.selectAllReservation(connection);
                        tableTitle = "Toutes les réservations";
                    }
                }else if(idVoyage!=null && !idVoyage.isEmpty()){
                    Voyage voyage = crud.selectById(Voyage.class, Integer.parseInt(idVoyage));
                    reservations = V_Reservation.selectAllReservationByIdVoyage(connection, Integer.parseInt(idVoyage));
                    tableTitle = "Toutes les réservations pour le voyage "+voyage.getVoyage();
                }
                else{
                    reservations = V_Reservation.selectAllReservation(connection);
                    tableTitle = "Toutes les réservations";
                }
                
                
                request.setAttribute("voyages", voyages);
                request.setAttribute("reservations", reservations);
                
                request.setAttribute("tableTitle", tableTitle);
                
                connection.close();
                RequestDispatcher dispatcher = request.getRequestDispatcher("pages/modifier/modifierReservation.jsp");
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
