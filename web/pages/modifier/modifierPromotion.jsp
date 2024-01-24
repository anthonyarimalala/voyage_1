<!DOCTYPE html>
<%@page import="java.util.List"%>
<%@page import="model.voyage.PromoEmp"%>
<%
    List<PromoEmp> promotions = (List<PromoEmp>) request.getAttribute("promotions");
    String successMessage = (String) request.getAttribute("successMessage");

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
              
              <% if(successMessage != null && !successMessage.isEmpty()) { %>
                <div class="alert alert-success" id="successMessage">
                    <%= successMessage %>
                </div>
                <script>
                    document.addEventListener('DOMContentLoaded', function() {
                        setTimeout(function(){
                            document.getElementById("successMessage").style.display = "none";
                        }, 5000);
                    });
                </script>
              <% } %>
               
              
            <div class="col-md-6 grid-margin stretch-card">
              <div class="card">
                <div class="card-body">
                  <h4 class="card-title">Modifier promotion</h4>
                  <p class="card-description">
                  </p>
                  <form action="ModifierPromotionServlet" method="post" class="forms-sample">
                    <div class="form-group">
                        <label for="exampleInputUsername1">Promotion</label>
                        <select name="idPromotion" class="form-control">
                            <% for(int i=0; i<promotions.size(); i++) { %>
                            <option value="<%= promotions.get(i).getIdPromotion() %>"><%= promotions.get(i).getPromotion() %></option>
                            <% } %>
                        </select>
                      <label for="exampleInputUsername1">Année d'experience</label>
                      <input type="number" name="anneeRequis" class="form-control" id="exampleInputUsername1" placeholder="Année d'experience" required>
                      <label for="exampleInputUsername1">Multiplié par</label>
                      <input type="number" min="1" name="multipl" class="form-control" id="exampleInputUsername1" placeholder="Multipl" required>
                    </div>
                    <button type="submit" name="action" value="update" class="btn btn-primary me-2">Submit</button>
                    <button type="submit" name="action" value="delete" class="btn btn-danger me-2">Supprimer</button>
                  </form>
                </div>
              </div>
            </div>
              
              
              
            <div class="col-md-3 grid-margin stretch-card">
              <div class="card">
                <div class="card-body">
                  <h4 class="card-title">Ajouter promotion</h4>
                  <p class="card-description">
                    
                  </p>
                  <form action="ModifierPromotionServlet" method="post" class="forms-sample">
                    <div class="form-group">
                      <label for="exampleInputUsername1">Promotion</label>
                      <input type="text" name="promotion" class="form-control" id="exampleInputUsername1" placeholder="Promotion" required>
                      
                      <label for="exampleInputUsername1">Année d'experience requis: </label>
                      <input type="number" name="anneeRequis" class="form-control" id="exampleInputUsername1" placeholder="Année d'experience" required>
                      
                      <label for="exampleInputUsername1">Multiplié par</label>
                      <input type="number" name="multipl" min="1" class="form-control" id="exampleInputUsername1" placeholder="Multipl" required>
                      
                    </div>
                      <button type="submit" name="action" value="insert" class="btn btn-primary me-2">Submit</button>
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
