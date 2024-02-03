<%@page import="model.voyage.Activite"%>

<!DOCTYPE html>

<%@page import="model.voyage.Voyage"%>
<%@page import="model.vue.V_Voyage"%>
<%@page import="model.vue.V_FormuleComposition"%>
<%@page import="model.vue.V_VoyageEmploye"%>
<%@page import="utils.Utils"%>
<%@page import="java.util.List"%>
<%
    
    V_Voyage voyage = (V_Voyage) request.getAttribute("voyage");
    List<V_FormuleComposition> formuleComps = (List<V_FormuleComposition>) request.getAttribute("formuleComp");
    List<V_VoyageEmploye> voyageEmps = (List<V_VoyageEmploye>) request.getAttribute("voyageEmp");

    
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
            <h2 id="content-types">Detail voyage <%= voyage.getVoyage() %>:</h2><br/>
            
            
            
           <div class="row">
              
           
        <div class="col-md-4 grid-margin stretch-card">
          <div class="card" style="width: 18rem;">
            <!--<img src="..." class="card-img-top" alt="...">-->
            <div class="card-body">
              <h5 class="card-title"><%= voyage.getVoyage() %></h5>
              <p class="card-text"><%= voyage.getDescription() %></p>
            </div>
            <ul class="list-group list-group-flush">
              <li class="list-group-item"><strong>Duree:    </strong><%= voyage.getDuree() %></li>
              <li class="list-group-item"><strong>Lieu:    </strong><%= voyage.getLieu() %></li>
              <li class="list-group-item"><strong>Bouquet: </strong><%= voyage.getBouquet() %></li>
              <li class="list-group-item"><strong>Prix: </strong><%= Utils.formatDouble(voyage.getPrix()) %></li>
              <li class="list-group-item"><strong>Total activite: </strong><%= Utils.formatDouble(voyage.getPrixTotActivite()) %></li>
              <li class="list-group-item"><strong>Total employe: </strong><%= Utils.formatDouble(voyage.getPrixTotEmploye()) %></li>
              <li class="list-group-item"><strong>Benefice: </strong><%= Utils.formatDouble(voyage.getBenefice()) %></li>    
               <li class="list-group-item">
                          <div class="form-group row">
                            <label for="exampleInputEmail2" class="col-sm-3 col-form-label"><strong>Employes:</strong></label>
                            <div class="col-sm-9">
                            <% for(int j=0; j<voyageEmps.size(); j++) { %>
                                <div class="form-check form-check-inline">
                                    <label class="form-check-label"><%= voyageEmps.get(j).getNom() %></label>
                                </div>
                            <% } %>
                            </div>
                        </div>
                      </li>
                       <li class="list-group-item">
                          <div class="form-group row">
                            <label for="exampleInputEmail2" class="col-sm-3 col-form-label"><strong>Activites</strong></label>
                            <div class="col-sm-9">
                            <% for(int j=0; j<formuleComps.size(); j++) { %>
                                <div class="form-check form-check-inline">
                                    <label class="form-check-label"><%= formuleComps.get(j).getActivite() %>: <%= (int)formuleComps.get(j).getQuantite() %></label>
                                </div>
                            <% } %>
                            </div>
                        </div>
                      </li>
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
