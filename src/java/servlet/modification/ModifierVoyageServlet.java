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
import model.liaison.L_VoyageEmploye;
import model.voyage.Voyage;

/**
 *
 * @author PC
 */
public class ModifierVoyageServlet extends HttpServlet {

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
            out.println("<title>Servlet ModifierVoyageServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ModifierVoyageServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
            
            try{
                Connection connection = Connex.getConnection();
                CrudOperation crud = new CrudOperation(connection);
                
                
                
                
                int idVoyage = Integer.parseInt(request.getParameter("idVoyage"));
                double prix = Double.parseDouble(request.getParameter("prix"));
                Voyage voyage = crud.selectById(Voyage.class, idVoyage);
                voyage.setPrix(prix);
                
                int isModifEmploye = Integer.parseInt(request.getParameter("isModifEmploye"));
                if(isModifEmploye == 1){
                    String[] idEmployesStr = request.getParameterValues("idEmployes");
                    L_VoyageEmploye voyageEmploye = new L_VoyageEmploye();
                    crud.delete(L_VoyageEmploye.class, "id_voyage", idVoyage);
                    if(idEmployesStr != null){
                        for(int i=0; i<idEmployesStr.length; i++){
                            out.println("<br/> idEmploye: "+idEmployesStr[i]);
                            int idEmploye = Integer.parseInt(idEmployesStr[i]);
                            String myParameter = "heure"+idEmploye;
                            double volumeHoraire = Double.parseDouble(request.getParameter(myParameter));
                            out.println(", Volume horaire: "+volumeHoraire);


                            voyageEmploye.setIdEmploye(idEmploye);
                            voyageEmploye.setIdVoyage(idVoyage);
                            voyageEmploye.setVolumeH(volumeHoraire);

                            out.println("<br/> idVoyageEmploye: "+crud.saveReturn(voyageEmploye));
                       }
                    }
                }
                
                crud.update(voyage, idVoyage);
                
                request.setAttribute("successMessage", "Voyage modifié avec success!");
                
                connection.close();
                RequestDispatcher dispatcher = request.getRequestDispatcher("ToModifierVoyage");
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
