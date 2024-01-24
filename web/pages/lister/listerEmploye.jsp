
<!DOCTYPE html>

<%@page import="java.util.List"%>
<%@page import="model.vue.V_Employe"%>

<%
    List<V_Employe> employes = (List<V_Employe>)request.getAttribute("employes");

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
            <h2 id="content-types">Liste des Employes:</h2><br/>
            <!--<p>Cards support a wide variety of content, including images, text, list groups, links, and more. Below are examples of what's supported.</p>-->
           
           
                  
            
            
           <div class="row">
              
            <% for(int i = 0; i < employes.size(); i++) { %>
                <div class="col-md-4 grid-margin stretch-card">
                    <div class="card" style="width: 18rem;">
                        <!--<img src="..." class="card-img-top" alt="...">-->
                        <div class="card-body">
                            <h5 class="card-title"><%= employes.get(i).getNom() %></h5>
                            <p class="card-text"><%= employes.get(i).getFonction() %></p>

                            <p class="card-text"><strong>Taux horaire: </strong> <%= employes.get(i).getNewPrix() %></p>
                            <p class="card-text"><strong>Experience: </strong> <%= employes.get(i).getExperience() %></p>
                            <p class="card-text"><strong>Année d'Experience: </strong> <%= employes.get(i).getAnneeExperience() %></p>



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
