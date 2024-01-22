<%@page import="model.utils.U_BouquetActivite"%>


<!DOCTYPE html>

<%@page import="java.util.List"%>
<%
    List<U_BouquetActivite> bouquetActivites = (List<U_BouquetActivite>) request.getAttribute("u_bouquetActivites");
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
          <div class="row">
              
            <% for(int i=0; i<bouquetActivites.size(); i++) { %>  
                <div class="col-lg-3 grid-margin stretch-card">
                  <div class="card">
                    <div class="card-body">
                      <h4 class="card-title">Bouquet: <%= bouquetActivites.get(i).getBouquet().getBouquet() %></h4>
                      <div class="table-responsive">
                        <table class="table">
                          <thead>
                            <tr>
                              <th>Activites: </th>
                            </tr>
                          </thead>
                          <tbody>
                              <% for(int j=0; j<bouquetActivites.get(i).getBouquetActivite().size(); j++) { %>
                            <tr>
                              <td><%= bouquetActivites.get(i).getBouquetActivite().get(j).getActivite() %></td>
                            </tr>
                            <% } %>
                          </tbody>
                        </table>
                      </div>
                    </div>
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
