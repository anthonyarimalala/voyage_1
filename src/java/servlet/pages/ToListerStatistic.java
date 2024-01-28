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
import model.vue.V_Statistic;

/**
 *
 * @author PC
 */
public class ToListerStatistic extends HttpServlet {

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
            out.println("<title>Servlet ToListerStatistic</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ToListerStatistic at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
             try{
                Connection connection = Connex.getConnection();
                CrudOperation crud = new CrudOperation(connection);
                
                
                String idVoyage = request.getParameter("idVoyage");
                List<V_Statistic> stats = new ArrayList<>();
                
                if(idVoyage!=null && !idVoyage.isEmpty()){
                    V_Statistic statistic = V_Statistic.selectByIdVoyage(connection, Integer.parseInt(idVoyage));
                    if(statistic==null){
                        Voyage voyage = crud.selectById(Voyage.class, Integer.parseInt(idVoyage));
                        statistic = new V_Statistic();
                        statistic.setIdVoyage(voyage.getIdVoyage());
                        statistic.setSommeFemme(0);
                        statistic.setSommeHomme(0);
                        statistic.setVoyage(voyage.getVoyage());
                    }
                    stats.add(statistic);
                }else{
                    stats = crud.selectAll(V_Statistic.class);
                }
                
                List<Voyage> voyages = crud.selectAll(Voyage.class);
                
                request.setAttribute("voyages", voyages);
                request.setAttribute("stats", stats);
                
                connection.close();
                RequestDispatcher dispatcher = request.getRequestDispatcher("pages/lister/listerStatistics.jsp");
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
