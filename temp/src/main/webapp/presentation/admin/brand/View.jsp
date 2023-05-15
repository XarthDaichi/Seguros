<%@page import="com.progra4.Seguros.presentation.admin.brand.Model"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.progra4.Seguros.logic.*"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/presentation/Head.jsp" %>
        <title>Polizas</title> 
    </head>
    <body>
        
        <% Model model= (Model) request.getAttribute("model"); %>
        <% ArrayList<ArrayList<Vehicle>> vehicles = model.getVehicles(); %>
        
        <%@ include file="/presentation/Header.jsp" %>
        <div class="add">
            <h1>Lista de pólizas por cliente</h1>
        </div>
        <div class="add">
            <a href="presentation/admin/addVehicle/show" class="btn btn-primary">Agregar Vehículo</a>
        </div>
        <div class="container">
            <% for (ArrayList<Vehicle> vehicleList : vehicles) { %>
                <h2> <%= vehicleList.get(0).getBrand() %>: </h2>
                <table class="table custom-table">
                    <thead>
                        <tr>
                            <th>Modelo</th>
                            <th>Imagen</th>
                            <th>Año</th>
                        </tr>
                    </thead>
                    <% for (Vehicle vehicle : vehicleList) { %>
                        <tr>
                            <td>
                                <%= vehicle.getModel() %>
                            </td>
                            <td>
                                <img class="img-fluid" style="max-width: 100px; max-height: 100px;" src=" images/<%= vehicle.getId() %>.png ">
                            </td>
                            <td>
                                <%= vehicle.getYear() %>
                            </td>
                        </tr>
                    <% } %>
                </table>
            <% } %>
        </div>
        <%@ include file="/presentation/Footer.jsp" %>
    </body>
</html>