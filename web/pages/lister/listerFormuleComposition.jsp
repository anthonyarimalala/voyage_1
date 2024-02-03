<%-- 
    Document   : modifierReservation
    Created on : Jan 29, 2024, 3:53:01 PM
    Author     : PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%@page import="java.util.List"%>
<%@page import="model.vue.V_FormuleComposition"%>

<%
    List<V_FormuleComposition> formuleCompositions = (List<V_FormuleComposition>)request.getAttribute("formuleCompositions");
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
            <h2 id="content-types">Liste des Formules des compositions</h2><br/>
            <!--<p>Cards support a wide variety of content, including images, text, list groups, links, and more. Below are examples of what's supported.</p>-->
            
            
           <div class="row">
              <div class="col-lg-8 grid-margin stretch-card">
              <div class="card">
                <div class="card-body">
                  <h4 class="card-title">Formules</h4>
                  <div class="table-responsive">
                    <table class="table">
                      <thead>
                        <tr>
                          <th>Lieu</th>
                          <th>Bouquet</th>
                          <th>Duree</th>
                          <th>Activite</th>
                          <th>Quantite</th>
                        </tr>
                      </thead>
                      <tbody>
                        <% for(int i=0; i<formuleCompositions.size(); i++) { %>
                        <tr>
                          <td><%= formuleCompositions.get(i).getLieu() %></td>
                          <td><%= formuleCompositions.get(i).getBouquet() %></td>
                          <td><%= formuleCompositions.get(i).getDuree() %></td>
                          <td><%= formuleCompositions.get(i).getActivite() %></td>
                          <td><%= (int)formuleCompositions.get(i).getQuantite() %></td>
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
