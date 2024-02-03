<%-- 
    Document   : modifierReservation
    Created on : Jan 29, 2024, 3:53:01 PM
    Author     : PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%@page import="java.util.List"%>
<%@page import="model.vue.V_Reservation"%>
<%@page import="model.voyage.Voyage"%>
<%@page import="utils.Utils"%>

<%
    List<V_Reservation> reservations = (List<V_Reservation>)request.getAttribute("reservations");
    List<Voyage> voyages = (List<Voyage>)request.getAttribute("voyages");
    String tableTitle = (String)request.getAttribute("tableTitle");
%>

<html lang="en">

<head>
  <!-- Required meta tags -->
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <title>Star Admin2 </title>
  <!-- plugins:css -->
  <jsp:include page="../styles.jsp" />
</head>

<body>
  <div class="container-scroller">
    <!-- partial:../../partials/_navbar.html -->
    <jsp:include page="../header.jsp"/>
    
    <!-- partial -->
    <div class="container-fluid page-body-wrapper">
      <!-- partial:../../partials/_settings-panel.html -->
      
      
      <!-- partial -->
      <!-- partial:../../partials/_sidebar.html -->
      
      <jsp:include page="../sidebar.jsp" />
      
      <!-- partial -->
      <div class="main-panel">        
        <div class="content-wrapper">
            <h2 id="content-types">Liste des Reservations:</h2><br/>
            <!--<p>Cards support a wide variety of content, including images, text, list groups, links, and more. Below are examples of what's supported.</p>-->
           
            <div class="card-body">
                <a class="btn btn-primary dropdown-toggle" href="#" role="button" id="dropdownMenuLink" data-bs-toggle="dropdown" aria-expanded="false">
                  Par Etat
                </a>

                <ul class="dropdown-menu" aria-labelledby="Find activitis">
                    <li><a class="dropdown-item" href="ToModifierReservation?action=all">Tous</a></li>
                  <li><a class="dropdown-item" href="ToModifierReservation?action=wait">En attente</a></li>
                  <li><a class="dropdown-item" href="ToModifierReservation?action=validate">Validés</a></li>
                  <li><a class="dropdown-item" href="ToModifierReservation?action=dismissed">Refusés</a></li>
                </ul>
                <a class="btn btn-primary dropdown-toggle" href="#" role="button" id="dropdownMenuLink" data-bs-toggle="dropdown" aria-expanded="false">
                  Par Voyage
                </a>

                <ul class="dropdown-menu" aria-labelledby="Find activitis">
                  <% for(int i=0; i<voyages.size(); i++) { %>
                  <li><a class="dropdown-item" href="ToModifierReservation?idVoyage=<%= voyages.get(i).getIdVoyage() %>"><%= voyages.get(i).getVoyage() %></a></li>
                  <% } %>
                </ul>
                
                
              </div>
            
            
           <div class="row">
              <div class="col-lg-8 grid-margin stretch-card">
              <div class="card">
                <div class="card-body">
                  <h4 class="card-title"><%= tableTitle %></h4>
                  <div class="table-responsive">
                    <table class="table">
                      <thead>
                        <tr>
                          <th>Date reservation</th>
                          <th>Voyage</th>
                          <th>Quantite</th>
                          <th>Nom</th>
                          <th>Etat</th>
                        </tr>
                      </thead>
                      <tbody>
                        <% for(int i=0; i<reservations.size(); i++) { %>
                        <tr>
                          <td><%= reservations.get(i).getDateReservation() %></td>
                          <td><%= reservations.get(i).getVoyage() %></td>
                          <td><%= (int)reservations.get(i).getQuantite() %></td>
                          <td><%= reservations.get(i).getNom() %> <%= reservations.get(i).getPrenom() %></td>
                          <td><label class="badge badge-<%= reservations.get(i).getCssEtatReservation() %>"><%= reservations.get(i).getEtatReservation() %></label></td>
                          <% if(reservations.get(i).getEtat()==5) { %>
                          <td><a href="ModifierReservationServlet?idReservation=<%= reservations.get(i).getIdReservation() %>&&etat=10"><label class="badge badge-success">Valider</label></a></td>
                          <td><a href="ModifierReservationServlet?idReservation=<%= reservations.get(i).getIdReservation() %>&&etat=0"><label class="badge badge-danger">Annuler</label></a></td>
                          <% } %>
                        </tr>
                        <% } %>
                        
                      </tbody>
                    </table>
                  </div>
                </div>
              </div>
            </div>
          </div>
            
        </div>
        <!-- content-wrapper ends -->
        <!-- partial:../../partials/_footer.html -->
        <footer class="footer">
          <div class="d-sm-flex justify-content-center justify-content-sm-between">
            <span class="text-muted text-center text-sm-left d-block d-sm-inline-block">Premium <a href="https://www.bootstrapdash.com/" target="_blank">Bootstrap admin template</a> from BootstrapDash.</span>
            <span class="float-none float-sm-right d-block mt-1 mt-sm-0 text-center">Copyright © 2021. All rights reserved.</span>
          </div>
        </footer>
        <!-- partial -->
      </div>
      <!-- main-panel ends -->
    </div>
    <!-- page-body-wrapper ends -->
  </div>
  <!-- container-scroller -->
  <!-- plugins:js -->
  <jsp:include page="../scripts.jsp" />
  <!-- End custom js for this page-->
</body>

</html>
