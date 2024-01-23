<%-- 
    Document   : listerEmploye
    Created on : 23 janv. 2024, 14:45:45
    Author     : Rajo Narivony
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@page import="java.util.List"%>
<%@page import="model.voyage.Employe"%>

<% 
    List<Employe> employes = (List<Employe>)request.getAttribute("employes");
%>
<!DOCTYPE html>
<html>
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
            
            <div class="main-panel">
                <div class="content_wrapper">
                    <h2 id="content-types">Liste des employes (<%= employes.size() %>):</h2><br/>
                    <!--<p>Cards support a wide variety of content, including images, text, list groups, links, and more. Below are examples of what's supported.</p>-->
                    
                    <div class="row">
                        <% for(int i = 0; i < employes.size(); i++) { %>
                        <div class="col-md-4 grid-margin stretch-card">
                            <div class="card" style="width: 18rem;">
                                <!--<img src="..." class="card-img-top" alt="...">-->
                                <div class="card-body">
                                    <h5 class="card-title"><%= employes.get(i).getNom() %></h5>
                                    <p class="card-text"><%= employes.get(i).getFonction() %></p>
                                    <p class="card-text"><%= employes.get(i).getPrix() %></p>
                                </div>
                            </div>
                        </div>
                        <% } %>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
