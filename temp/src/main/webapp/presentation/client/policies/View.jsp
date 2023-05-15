<%@page import="com.progra4.Seguros.presentation.client.policies.Model"%>
<%@page import="java.util.List"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/presentation/Head.jsp" %>
        <title>Polizas</title> 
    </head>
    <body>
        
        <% Model model= (Model) request.getAttribute("model"); %>
        <% List<Policy> policies= (List) model.getPolicies(); %>
        
        <%@ include file="/presentation/Header.jsp" %>
        <div class="add">
            <h1>Lista de pólizas</h1>
        </div>
        <div class="add">
            <a href="/Seguros/presentation/client/policy/show" class="btn btn-primary">Agregar Póliza</a>
        </div>
        <div class="container">
            <div class="table-responsive">
                <table class="table custom-table">
                    <thead>
                        <tr>
                            <th>Placa</th>
                            <th>Fecha</th>
                            <th>Auto</th>
                            <th>Imagen</th>
                            <th>Valor</th>
                        </tr>
                    </thead>
                    <tbody>
                        <% for(Policy p:policies){%>
                            <tr>
                                <td>
                                    <a href="presentation/client/policies/select?id=<%= p.getId() %>"> <%= p.getId() %> </a>
                                </td>
                                <td>
                                    <%= p.getInitialDate() %>
                                </td>
                                <td>
                                    <%= p.getVehicle().getBrand() %> - <%= p.getVehicle().getModel() %>
                                </td>
                                <td>
                                    <img class="img-fluid" style="max-width: 100px; max-height: 100px;" src=" images/<%= p.getVehicle().getId()%>.png ">
                                </td>
                                <td>
                                    <%= p.getInsuredValue() %>
                                </td>
                            </tr>
                        <%}%>
                    </tbody>
                </table>
            </div>
        </div>
        <%@ include file="/presentation/Footer.jsp" %>
    </body>
</html>