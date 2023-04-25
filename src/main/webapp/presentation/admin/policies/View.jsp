<%@page import="com.progra4.Seguros.presentation.admin.policies.Model"%>
<%@page import="java.util.List"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/presentation/Head.jsp" %>
        <title>Polizas</title> 
    </head>
    <body>
        
        
        <% Model model= (Model) request.getAttribute("model"); %>
        <% Map<String,List<Policy>> policies= model.getPoliciesByUser(); %>
        <% List<User> users= (List) model.getUsers(); %>
        
        <%@ include file="/presentation/Header.jsp" %>
        <div class="add">
            <h1>Lista de pólizas por cliente</h1>
        </div>
        <% for(User u:users) { %>
            <div class="container">
                <h2> <%= u.getName() %>: </h2>
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
                        <% for(Policy p:policies.get(u.getId())) { %>
                            <tr>
                                <td>
                                    
                                </td>
                                <td>
                                    <%= p.getId() %>
                                </td>
                                <td>
                                    <%= p.getInitialDate() %>
                                </td>
                                <td>
                                    
                                </td>
                                <td>
                                    <!-- <img class="img-fluid" style="max-width: 100px; max-height: 100px;" src=" images/.png "> -->
                                </td>
                                <td>
                                    <%= p.getInsuredValue() %>
                                </td>
                            </tr>
                        <% } %>
                    </tbody>
                    </table>
                </div>
            </div> 
        <% } %>
        <%@ include file="/presentation/Footer.jsp" %>
    </body>
</html>