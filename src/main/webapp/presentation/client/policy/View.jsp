<%-- 
    Document   : View
    Created on : Apr 23, 2023, 3:24:50 PM
    Author     : lmont
--%>

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
                            <input type="text" placeholder="Placa" class="form-control" id="placa" name="placa" required>
                        </div>
                        <div class="form-group mb-3">
                            <label for="dropdown-menu">Marca-Modelo:</label>
                            <select id="dropdown-menu">
                                <option value="" disabled selected>Marca-Modelo</option>
                                <option value="option1">Option 1</option>
                                <option value="option2">Option 2</option>
                                <option value="option3">Option 3</option>
                            </select>
                        </div>
                        <div class="form-group mb-3">
                            <label for="dropdown-menu">Año:</label>
                            <select id="dropdown-menu">
                                <option value="" disabled selected>Año</option>
                                <option value="option1">Option 1</option>
                                <option value="option2">Option 2</option>
                                <option value="option3">Option 3</option>
                            </select>
                        </div>
                        <div class="form-group mb-3">
                            <input type="text" placeholder="Valor" class="form-control" id="valor" name="valor" required>
                        </div>
                        <div class="form-group mb-3">
                            <fieldset>
                                <legend>Modo de pago:</legend>
                                <label><input type="radio" name="Trimestral" value="Trimestral">Apple</label><br>
                                <label><input type="radio" name="Semestral" value="Semestral">Banana</label><br>
                                <label><input type="radio" name="Anual" value="Anual">Orange</label><br>
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