
<!DOCTYPE html>
<%@page import="model.voyage.Bouquet"%>
<%@page import="model.voyage.Lieu"%>
<%@page import="model.voyage.Duree"%>
<%@page import="model.voyage.Employe"%>
<%@page import="java.util.List"%>

<%
    List<Bouquet> bouquets = (List<Bouquet>)request.getAttribute("bouquets");
    List<Lieu> lieux = (List<Lieu>)request.getAttribute("lieux");
    List<Duree> durees = (List<Duree>) request.getAttribute("durees");
    List<Employe> employes = (List<Employe>) request.getAttribute("employes");
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
                  <h4 class="card-title">Inserer Voyages</h4>
                  <p class="card-description">
                    Nouvelles idées de voyages? Vous pouvez mettre ici
                  </p>
                  <form action="InsererVoyageServlet" method="post" class="forms-sample">
                    <div class="form-group">
                      <label for="exampleInputUsername1">Voyage designation</label>
                      <input type="text" name="voyage" class="form-control" id="exampleInputUsername1" placeholder="voyage" required>
                    </div>
                    <div class="form-group">
                      <label for="exampleInputUsername1">Description du voyage</label>
                      <input type="text" name="description" class="form-control" id="exampleInputUsername1" placeholder="Description" required>
                    </div>
                    
                        
                    <div class="form-group row">
                      <label for="exampleInputUsername2" class="col-sm-3 col-form-label">Lieu</label>
                      <div class="col-sm-9">
                        <select name="idLieu" class="form-control">
                        <% for(int i=0; i<lieux.size(); i++) { %>
                        <option value=<%= lieux.get(i).getIdLieu() %>><%= lieux.get(i).getLieu() %></option>
                        <% } %>
                        </select>
                      </div>
                    </div>
                        
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
                      <label for="exampleInputUsername2" class="col-sm-3 col-form-label">Duree</label>
                      <div class="col-sm-9">
                        <select name="idDuree" class="form-control">
                            <% for(int i=0; i<durees.size(); i++) { %>
                                <option value="<%= durees.get(i).getIdDuree() %>"><%= durees.get(i).getDuree() %></option>
                            <% } %>
                        </select>
                      </div>
                    </div>    
                     
                        
                    <div class="form-group">
                      <label for="exampleInputUsername1">Prix</label>
                      <input type="number" name="prix"  class="form-control" id="exampleInputUsername1" placeholder="Prix" required>
                    </div>
                        
                    <div class="form-group row">
                      <label for="exampleInputEmail2" class="col-sm-3 col-form-label">Employes</label>
                      <div class="col-sm-9">
                        <% for(int i=0; i<employes.size(); i++) { %>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" name="idEmployes" value="<%= employes.get(i).getIdEmploye() %>">
                            <label class="form-check-label"><%= employes.get(i).getNom() %>: <%= employes.get(i).getFonction() %></label>
                            <input class="form-control hour-input" type="number" name="heure<%= employes.get(i).getIdEmploye() %>" placeholder="volume horaire" min="0" value="0" id="exampleInputUsername1">
                        </div>
                        <% } %>
                      </div>
                    </div>
                      
                    <button type="submit" class="btn btn-primary me-2">Submit</button>
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
