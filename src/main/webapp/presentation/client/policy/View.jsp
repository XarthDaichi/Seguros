<%@page import="com.progra4.Seguros.presentation.client.policy.Model"%>
<%@page import="com.progra4.Seguros.logic.*"%>
<%@page import="java.util.List" %>
<%@page import="java.util.ArrayList" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@ include file="/presentation/Head.jsp" %>
        <title>Seguros</title>
    </head>
    <body>
        
        <% Model model= (Model) request.getAttribute("model"); %>
        <% Service service = Service.instance(); %>
        
        <%@ include file="/presentation/Header.jsp" %>
        <div class="register">
            <div class="row">
                <div class="col-md-4 offset-md-4 mx-auto">
                    <form name="form" action="presentation/coverage/show" method="post" >
                        <h2 style="text-align: center">Póliza</h2>
                        <div class="form-group mb-3">
                            <input type="text" placeholder="Placa" class="form-control" id="placa" name="placa" required>
                        </div>
                        <div class="form-group mb-3">
                            <label for="dropdown-menu">Marca-Modelo-Año:</label>
                            <select id="dropdown-menu">
                                
                                <% for (ArrayList<Vehicle> vehicleList : service.selectBrandsAndModels()) { %>
                                <optgroup label="<%= vehicleList.get(0).getBrand() %>"> 
                                    <% for (Vehicle vehicle : vehicleList) { %>
                                    <option name="brandModelYear" value="<%= vehicle.getId() %>"> <%= vehicle.getBrand() %> - <%= vehicle.getModel() %> - <%= vehicle.getYear() %> </option>
                                    <% } %>
                                <% } %>
                            </select>
                        </div>
                        <div class="form-group mb-3">
                            <input type="text" placeholder="Valor" class="form-control" id="valor" name="valor" required>
                        </div>
                        <div class="form-group mb-3">
                            <fieldset>
                                <legend>Modo de pago:</legend>
                                <label><input type="radio" name="modoPago" value="QUARTERLY">Trimestre</label>
                                <label><input type="radio" name="modoPago" value="BIANNUAL">Semestre</label>
                                <label><input type="radio" name="modoPago" value="ANNUAL">Anual</label>
                            </fieldset>
                        </div>
                        <button class="btn btn-primary">Siguiente (coberturas) →</button>
                    </form>
                </div>
            </div>
        </div>
        <%@ include file="/presentation/Footer.jsp" %>
    </body>
</html>