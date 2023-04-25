<%@page import="com.progra4.Seguros.presentation.admin.brand.Model"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.progra4.Seguros.logic.*"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/presentation/Head.jsp" %>
        <title>Añadir vehículo</title> 
    </head>
    <body>
        <%@ include file="/presentation/Header.jsp" %>
        
        <div class="register">
            <div class="row">
                <div class="col-md-4 offset-md-4 mx-auto">
                    <form name="form" action="presentation/admin/addVehicle/add" method="post" >
                        <h2 style="text-align: center">Vehículo</h2>
                        <img src="images/Logotipo.png" class="login">
                        <div class="form-group mb-3">
                            <input type="text" placeholder="Marca" class="form-control" id="marca" name="brand" required>
                        </div>
                        <div class="form-group mb-3">
                            <input type="text" placeholder="Modelo" class="form-control" id="modelo" name="model" required>
                        </div>
                        <div class="form-group mb-3">
                            <input type="int" placeholder="Año" class="form-control" id="year" name="year" required>
                        </div>
                        <div class="form-group mb-3">
                            <input id="file-upload" type="file" name="img" accept=".png">
                        </div>
                        <button class="btn btn-primary">Guardar</button>
                    </form>
                </div>
            </div>
        </div>
        
        <%@ include file="/presentation/Footer.jsp" %>
    </body>
</html>