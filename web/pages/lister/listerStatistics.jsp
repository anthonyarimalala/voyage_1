<%@page import="model.voyage.Activite"%>
<%@page import="model.vue.V_Voyage"%>
<%@page import="model.vue.V_Statistic"%>


<!DOCTYPE html>

<%@page import="model.voyage.Voyage"%>
<%@page import="utils.Utils"%>
<%@page import="java.util.List"%>
<%
    List<Voyage> voyages = (List<Voyage>) request.getAttribute("voyages");
    List<V_Statistic> stats = (List<V_Statistic>) request.getAttribute("stats");
    
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
  <script src="chart.umd.js"></script>
  <script src="echarts.min.js"></script>
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
            <h2 id="content-types">Liste des voyages (<%= voyages.size() %>):</h2><br/>
            <p>Raha tsy hita ato dia jereo aloha raha validé ilay réservation. (Modifier réservation)</p>
            <div class="dropdown">
                <a class="btn btn-primary" href="ToListerStatistic" role="button">
                  All
                </a>
                 <a class="btn btn-primary dropdown-toggle" href="#" role="button" id="dropdownMenuLink" data-bs-toggle="dropdown" aria-expanded="false">
                    Find Voyage
                  </a>

                  <ul class="dropdown-menu" aria-labelledby="Find activitis">
                    <% for(int i=0; i< voyages.size(); i++) { %>
                    <li><a class="dropdown-item" href="ToListerStatistic?idVoyage=<%= voyages.get(i).getIdVoyage() %>"><%= voyages.get(i).getVoyage() %></a></li>
                    <% } %>
                  </ul>
              </div>
          
                  
                  
            <div class="col-md-4">
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title">Tableau</h5>
                    </div>
                    <table class="table">
                        <tr>
                            <th>Voyage</th>
                            <th>Hommes</th>
                            <th>Femmes</th>
                        </tr>
                        <% for(int i=0; i<stats.size(); i++) { %>
                        <tr>
                            <td><%= stats.get(i).getVoyage() %></td>
                            <td><%= stats.get(i).getSommeHomme() %></td>
                            <td><%= stats.get(i).getSommeFemme() %></td>
                        </tr>
                        <% } %>
                    </table>
                </div>
            </div>
     <% for(int i=0; i<stats.size(); i++) { %>
            <div class="col-md-4 grid-margin stretch-card">
                <div class="card" style="width: 18rem;">
                    <div class="card-body">
                        <h5 class="card-title">Graphe: <%= stats.get(i).getVoyage() %> </h5>
                    </div>
                    <div style="width: 80%; padding-left: 20%;">
                        <canvas id="barChart<%= i %>" style="max-height: 400px; display: block; box-sizing: border-box; height: 307px; width: 414px;" width="414" height="207"></canvas>
                        <script>
                            document.addEventListener("DOMContentLoaded", () => {
                                new Chart(document.querySelector('#barChart<%= i %>'), {
                                    type: 'bar',
                                    data: {
                                        labels: ['Homme', 'Femme'],
                                        datasets: [{
                                            label: '',
                                            data: [<%= stats.get(i).getSommeHomme() %>, <%= stats.get(i).getSommeFemme() %>, 0],
                                            backgroundColor: [
                                                'rgba(255, 99, 132, 0.2)',
                                                'rgba(255, 159, 64, 0.2)'
                                            ],
                                            borderColor: [
                                                'rgb(255, 99, 132)',
                                                'rgb(255, 159, 64)'
                                            ],
                                            borderColor: [
                                                'rgb(255, 99, 132)',
                                                'rgb(255, 159, 64)'
                                            ],
                                            borderWidth: 1
                                        }]
                                    },
                                    options: {
                                        scales: {
                                                y: {
                                                    beginAtZero: false,
                                                    suggestedMin: -1
                                                }
                                            }
                                        }
                                    });
                                });
                            </script>
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
