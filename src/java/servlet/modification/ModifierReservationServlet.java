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
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.voyage.Reservation;
import model.voyage.Stock;
import model.vue.V_StockQuantiteReste;

/**
 *
 * @author PC
 */
public class ModifierReservationServlet extends HttpServlet {

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
            out.println("<title>Servlet ModifierReservationServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ModifierReservationServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
            
             try{
                Connection connection = Connex.getConnection();
                CrudOperation crud = new CrudOperation(connection);
                
                int etat = Integer.parseInt(request.getParameter("etat"));
                int idReservation = Integer.parseInt(request.getParameter("idReservation"));
                
                Reservation reservation = crud.selectById(Reservation.class, idReservation);
                reservation.setEtat(etat);
                
//                annuler: v_stock_quantite_reste
                List<V_StockQuantiteReste> quantiteRestes = crud.selectAllById(V_StockQuantiteReste.class, "id_voyage", reservation.getIdVoyage());
                Stock stock = new Stock();
                
                if(etat==0){
                    for(int i=0; i<quantiteRestes.size(); i++){
                        stock.setEntree(quantiteRestes.get(i).getQuantite()*reservation.getQuantite());
                        stock.setSortie(0);
                        stock.setIdActivite(quantiteRestes.get(i).getIdActivite());
                        crud.save(stock);
                    }
                }
                
                
                crud.update(reservation, idReservation);
                
                
                connection.close();
                RequestDispatcher dispatcher = request.getRequestDispatcher("ToModifierReservation");
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
