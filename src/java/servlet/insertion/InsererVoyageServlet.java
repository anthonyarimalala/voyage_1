package servlet.insertion;

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

public class InsererVoyageServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet InsererVoyageServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet InsererVoyageServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
            
            try{
                Connection connection = Connex.getConnection();
                CrudOperation crud = new CrudOperation(connection);

                int idBouquet = Integer.parseInt(request.getParameter("idBouquet"));
                int idLieu = Integer.parseInt(request.getParameter("idLieu"));
                int idDuree = Integer.parseInt(request.getParameter("idDuree"));
                double prix = Double.parseDouble(request.getParameter("prix"));
                String voyageStr = request.getParameter("voyage");
                String description = request.getParameter("description");
                
                Voyage voyage = new Voyage();
                voyage.setDescription(description);
                voyage.setIdBouquet(idBouquet);
                voyage.setIdDuree(idDuree);
                voyage.setIdLieu(idLieu);
                voyage.setPrix(prix);
                voyage.setVoyage(voyageStr);
                
                int idVoyage = Integer.parseInt(crud.saveReturn(voyage));
                String[] idEmployesStr = request.getParameterValues("idEmployes");
                L_VoyageEmploye voyageEmploye = new L_VoyageEmploye();
                
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
                
               
                 
                connection.close();
                response.sendRedirect("ToInsererVoyage");
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
