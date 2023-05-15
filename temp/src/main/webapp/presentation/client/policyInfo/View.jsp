<%@page import="com.progra4.Seguros.presentation.client.policyInfo.Model"%>

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
        <%@ include file="/presentation/Header.jsp" %>
        <div class="register">
            <div class="row">
                <div class="col-md-4 offset-md-4 mx-auto">
                    <form name="form" action="presentation/coverage/show" method="post" >
                        <h2 style="text-align: center">Póliza</h2>
                        <div class="form-group mb-3">
                            <label for="inputText">Placa:</label>
                            <input class="form-control" type="text" placeholder="Placa" id="placa" name="placa" value="<%= model.getCurrent().getId() %>" readonly>
                        </div>
                        <div class="form-group mb-3">
                            <label for="dropdown-menu">Marca-Modelo:</label>
                            <select id="dropdown-menu" readonly>
                                <option selected><%= model.getCurrent().getVehicle().getBrand() %> - <%= model.getCurrent().getVehicle().getModel() %></option>
                            </select>
                        </div>
                        <div class="form-group mb-3">
                            <label for="dropdown-menu">Año:</label>
                            <select id="dropdown-menu" readonly>
                                <option selected> <%= model.getCurrent().getVehicle().getYear() %> </option>
                            </select>
                        </div>
                        <div class="form-group mb-3">
                            <label for="inputText">Valor:</label>
                            <input type="text" placeholder="Valor" class="form-control" id="valor" name="valor" value=" <%= model.getCurrent().getInsuredValue() %> " readonly>
                        </div>
                        <div class="form-group mb-3">
                            <legend>Modo de pago:</legend>
                            <input type="text" placeholder="Valor" class="form-control" id="valor" name="valor" value=" <%= model.getCurrent().getTerm() %> " readonly>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <%@ include file="/presentation/Footer.jsp" %>
    </body>
</html>