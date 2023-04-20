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
        <div class="register">
            <div class="row">
                <div class="col-md-4 offset-md-4 mx-auto">
                    <form name="form" action="presentation/login/show" method="post" >
                        <img src="images/Logotipo.png" class="login">
                        <h2 style="text-align: center">Registrarse</h2>
                        <div class="form-group mb-3 d-flex justify-content-between">
                            <input type="text" placeholder="ID" class="form-control" id="id" name="id" required>
                            <input type="password" placeholder="Contraseña" class="form-control" id="clave" name="clave" required>
                        </div>
                        <div class="form-group mb-3">
                            <input type="text" placeholder="Nombre" class="form-control" id="nombre" name="nombre" required>
                        </div>
                        <div class="form-group mb-3">
                            <input type="text" placeholder="Teléfono" class="form-control" id="telefono" name="telefono" required>
                        </div>
                        <div class="form-group mb-3">
                            <input type="email" placeholder="Correo electrónico" class="form-control" id="correo" name="correo" required>
                        </div>
                        <button class="btn btn-primary">Ingresar</button>
                    </form>
                </div>
            </div>
        </div>
        <%@ include file="/presentation/Footer.jsp" %>
    </body>
</html>