<!DOCTYPE html>


<%@page import="model.voyage.Bouquet"%>
<%@page import="model.voyage.Lieu"%>
<%@page import="model.voyage.Duree"%>
<%@page import="model.vue.V_BouquetActivite"%>
<%@page import="java.util.List"%>

<%
    Bouquet bouquet = (Bouquet)request.getAttribute("bouquet");
    Lieu lieu = (Lieu) request.getAttribute("lieu");
    Duree duree = (Duree) request.getAttribute("duree");
    
    List<V_BouquetActivite> v_bouquetActivites = (List<V_BouquetActivite>) request.getAttribute("v_bouquetActivites");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
          <div class="row">
              
              
            <div class="col-md-6 grid-margin stretch-card">
              <div class="card">
                <div class="card-body">
                  <h4 class="card-title">Formule de voyage</h4>
                  <p class="card-description">
                    Voyage <strong><%= lieu.getLieu() %> - <%= bouquet.getBouquet() %> - <%= duree.getDuree() %></strong>
                  </p>
                  <form action="InsererFormuleCompositionServlet" class="forms-sample" method="post">
                      <input type="hidden" name="idBouquet" value="<%= bouquet.getIdBouquet() %>">
                      <input type="hidden" name="idLieu" value="<%= lieu.getIdLieu() %>">
                      <input type="hidden" name="idDuree" value="<%= duree.getIdDuree() %>">
                    <div class="form-group row">
                      <% for(int i=0; i<v_bouquetActivites.size(); i++) { %>
                        <label for="exampleInputUsername2" class="col-sm-3 col-form-label"><%= v_bouquetActivites.get(i).getActivite() %></label>
                        <div class="col-sm-9">

                            <input type="text" name="<%= v_bouquetActivites.get(i).getIdActivite() %>" value="1" class="form-control" id="exampleInputUsername1" placeholder="Quantite" required>

                        </div>
                      <% } %>
                    </div>
                      
                    
                    <button type="submit" class="btn btn-primary me-2">Submit</button>
                    <button class="btn btn-light">Cancel</button>
                  </form>
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
