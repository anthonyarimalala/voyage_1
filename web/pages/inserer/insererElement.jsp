<!DOCTYPE html>
<%@page import="model.voyage.Bouquet"%>
<%@page import="model.voyage.Activite"%>
<%@page import="java.util.List"%>

<%
    List<Activite> activites = (List<Activite>)request.getAttribute("activites");
    List<Bouquet> bouquets = (List<Bouquet>)request.getAttribute("bouquets");
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
              
              
            <div class="col-md-6 grid-margin stretch-card">
              <div class="card">
                <div class="card-body">
                  <h4 class="card-title">Inserer bouquet</h4>
                  <p class="card-description">
                    Inserer le nouveau bouquet
                  </p>
                  <form action="InsererBouquetServlet" method="post" class="forms-sample">
                    <div class="form-group">
                      <label for="exampleInputUsername1">Bouquet designation</label>
                      <input type="text" name="bouquet" class="form-control" id="exampleInputUsername1" placeholder="Bouquet" required>
                    </div>
                    <button type="submit" class="btn btn-primary me-2">Submit</button>
                  </form>
                </div>
              </div>
            </div>
              
              
              
            <div class="col-md-6 grid-margin stretch-card">
              <div class="card">
                <div class="card-body">
                  <h4 class="card-title">Inserer activite</h4>
                  <p class="card-description">
                    Nouveau activite à proposer
                  </p>
                  <form action="InsererActiviteServlet" method="post" class="forms-sample">
                    <div class="form-group">
                      <label for="exampleInputUsername1">Activite designation</label>
                      <input type="text" name="activite" class="form-control" id="exampleInputUsername1" placeholder="Activite" required>
                      
                      <label for="exampleInputUsername1">Prix</label>
                      <input type="number" name="prix" class="form-control" id="exampleInputUsername1" placeholder="Prix" required>
                      
                    </div>
                    <button type="submit" class="btn btn-primary me-2">Submit</button>
                  </form>
                </div>
              </div>
            </div>
              
              
              
                      
                      
                      
             <div class="col-md-6 grid-margin stretch-card">
              <div class="card">
                <div class="card-body">
                  <h4 class="card-title">Inserer Lieu</h4>
                  <p class="card-description">
                    Il est rare d'insérer de nouveaux lieux
                  </p>
                  <form action="InsererLieuServlet" method="post" class="forms-sample">
                    <div class="form-group">
                      <label for="exampleInputUsername1">Lieu designation</label>
                      <input type="text" name="lieu" class="form-control" id="exampleInputUsername1" placeholder="Lieu" required>
                    </div>
                    <button type="submit" class="btn btn-primary me-2" value='inserLieu'>Submit</button>
                  </form>
                </div>
              </div>
            </div>
                      
            <div class="col-md-6 grid-margin stretch-card">
              <div class="card">
                <div class="card-body">
                  <h4 class="card-title">Inserer Employee</h4>
                  <p class="card-description">
                    
                  </p>
                  <form action="InsererEmployeServlet" method="post" class="forms-sample">
                    <div class="form-group">
                       
                      <label for="exampleInputUsername1">Nom</label>
                      <input type="text" name="nom" class="form-control" id="exampleInputUsername1" placeholder="nom" required>
                        
                      <label for="exampleInputUsername1">Fonction</label>
                      <input type="text" name="fonction" class="form-control" id="exampleInputUsername1" placeholder="fonction" required>
                      
                      <label for="exampleInputUsername1">Prix horaire</label>
                      <input type="text" name="prix" class="form-control" id="exampleInputUsername1" placeholder="prix" required>
                    </div>
                    <button type="submit" class="btn btn-primary me-2" value='inserLieu'>Submit</button>
                  </form>
                </div>
              </div>
            </div>
                     
                      
              
              
              
              
              
            <div class="col-md-6 grid-margin stretch-card">
              <div class="card">
                <div class="card-body">
                  <h4 class="card-title">Activites par Bouquet</h4>
                  <p class="card-description">
                    Choisissez des activites pour un bouquet
                  </p>
                  <form action="InsererBouquetActiviteServlet" class="forms-sample" method="post">
                    <div class="form-group row">
                      <label for="exampleInputUsername2" class="col-sm-3 col-form-label">Bouquet</label>
                      <div class="col-sm-9">
                        <select name="idBouquet" class="form-control">
                        <% for(int i=0; i<bouquets.size(); i++) { %>
                        <option value=<%= bouquets.get(i).getIdBouquet() %>><%= bouquets.get(i).getBouquet() %></option>
                        <% } %>
                        </select>
                      </div>
                    </div>
                    <div class="form-group row">
                      <label for="exampleInputEmail2" class="col-sm-3 col-form-label">Activite</label>
                      <div class="col-sm-9">
                          <% for(int i=0; i<activites.size(); i++) { %>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="inlineCheckbox1" name="activites" value="<%= activites.get(i).getIdActivite() %>">
                            <label class="form-check-label" for="inlineCheckbox1"><%= activites.get(i).getActivite() %></label>
                        </div>
                        <% } %>
                      </div>
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
