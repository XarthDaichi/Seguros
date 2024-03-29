<%@page import="com.progra4.Seguros.presentation.login.Model"%>
<%@page import="java.util.List"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@ include file="/presentation/Head.jsp" %>
        <title>Seguros - Login</title>
    </head>
    <body>
        <%@ include file="/presentation/Header.jsp" %>
        <div class="container">
            <div class="row">
                <div class="col-md-4 offset-md-4">
                    <form name="form" action="presentation/login/login" method="post" >
                        <img src="images/Logotipo.png" class="login">
                        <h2 style="text-align: center">Login</h2>
                        <div class="form-group mb-3 d-flex">
                            <span class="input-group-text">
                                <img style="max-width: 20px;" src="images/profile.png">
                            </span>
                            <input type="text" placeholder="ID del usuario" class="form-control" id="id" name="id" required>
                        </div>
                        <div class="form-group mb-3">
                            <input type="password" placeholder="Contraseña" class="form-control" id="password" name="password" required>
                        </div>
                        <button class="btn btn-primary">Ingresar</button>
                        <div class="form-group mb-3">
                            <a href="presentation/client/register/show" style="background-color: red" class="btn btn-primary">Registrarse</a>
                        </div>
                    </form>
                    
                </div>
            </div>
        </div>
        <%@ include file="/presentation/Footer.jsp" %>
    </body>
</html>
