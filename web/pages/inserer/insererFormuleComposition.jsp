<%@page import="model.voyage.Bouquet"%>
<%@page import="model.voyage.Lieu"%>
<%@page import="model.voyage.Duree"%>
<%@page import="java.util.List"%>

<%
    List<Bouquet> bouquets = (List<Bouquet>)request.getAttribute("bouquets");
    List<Lieu> lieux = (List<Lieu>) request.getAttribute("lieux");
    List<Duree> durees = (List<Duree>) request.getAttribute("durees");
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
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
                    Choisissez des activites pour un bouquet
                  </p>
                  <form action="ToInsererFormuleComposition2" class="forms-sample" method="post">
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
                        <option value=<%= durees.get(i).getIdDuree() %>><%= durees.get(i).getDuree() %></option>
                        <% } %>
                        </select>
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
