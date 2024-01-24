<%@page import="model.voyage.Activite"%>
<%@page import="model.vue.V_Voyage"%>


<!DOCTYPE html>

<%@page import="model.voyage.Voyage"%>
<%@page import="utils.Utils"%>
<%@page import="java.util.List"%>
<%
    List<Activite> activites = (List<Activite>) request.getAttribute("activites");
    List<V_Voyage> v_voyages = (List<V_Voyage>) request.getAttribute("v_voyages");
    
    Object errorObj = request.getAttribute("errorMessage");
    String errorMessage = "";
    if(errorObj!=null) errorMessage = (String) errorObj; 

    Object successObj = request.getAttribute("successMessage");
    String successMessage = "";
    if(successObj!=null) successMessage = (String) successObj; 
    
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
            <h2 id="content-types">Liste des voyages (<%= v_voyages.size() %>):</h2><br/>
            <!--<p>Cards support a wide variety of content, including images, text, list groups, links, and more. Below are examples of what's supported.</p>-->
            <div class="dropdown">
                <a class="btn btn-primary" href="ToListerVoyage" role="button">
                  All
                </a>
              </div>
           
                  
          
            <% if(errorObj!=null) { %>
            <div class="alert alert-danger" role="alert">
                <%= errorMessage %>
              </div>
              <% } %>
              
              <% if(successObj!=null) { %>
            <div class="alert alert-success" role="alert">
                <%= successMessage %>
              </div>
              <% } %>
            <br>
            
            
           <div class="row">
              
              <% for(int i=0; i< v_voyages.size(); i++) { %>
              <form action="ModifierReservationServlet">
            <div class="col-md-4 grid-margin stretch-card">
              <div class="card" style="width: 18rem;">
                <!--<img src="..." class="card-img-top" alt="...">-->
                <div class="card-body">
                  <h5 class="card-title"><%= v_voyages.get(i).getVoyage() %></h5>
                  <p class="card-text"><%= v_voyages.get(i).getDescription() %></p>
                </div>
                <ul class="list-group list-group-flush">
                  <li class="list-group-item"><strong>Duree:    </strong><%= v_voyages.get(i).getDuree() %></li>
                  <li class="list-group-item"><strong>Lieu:    </strong><%= v_voyages.get(i).getLieu() %></li>
                  <li class="list-group-item"><strong>Bouquet: </strong><%= v_voyages.get(i).getBouquet() %></li>
                  <li class="list-group-item"><strong>Prix: </strong><input type="number" name="prix" min="1000" value="<%= v_voyages.get(i).getPrix() %>" required /> <%= Utils.formatDouble(v_voyages.get(i).getPrix()) %> </li>
                  <li class="list-group-item"><strong>Total activite: </strong><%= Utils.formatDouble(v_voyages.get(i).getPrixTotActivite()) %></li>
                  <li class="list-group-item"><strong>Total employe: </strong><%= Utils.formatDouble(v_voyages.get(i).getPrixTotEmploye()) %></li>
                  <li class="list-group-item"><strong>Benefice: </strong><%= Utils.formatDouble(v_voyages.get(i).getBenefice()) %></li>
                  
                  
                  
                </ul>
                  
                    <div class="card-body">
                      <input type="text" name="idVoyage" value="<%= v_voyages.get(i).getIdVoyage() %>" hidden />
                      <button type="submit" name="byPrix" value="ok" class="btn btn-primary me-2">Mise à jour</button>
                      
                    </div>
                  </form>
              </div>
            </div>
            <% } %>
                  
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
