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
            <a href="#" class="btn btn-primary">Agregar Póliza</a>
        </div>
        <div class="container">
            <div class="table-responsive">
                <table class="table custom-table">
                    <thead>
                        <tr>
                            <th>#</th>
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
                                    <%= p.getId() %>
                                </td>
                                <td>
                                    1111
                                </td>
                                <td>
                                    <%= p.getInitialDate() %>
                                </td>
                                <td>@mdo</td>
                            </tr>
                        <%}%>
                    </tbody>
                </table>
            </div>
        </div>
        <%@ include file="/presentation/Footer.jsp" %>
    </body>
</html>