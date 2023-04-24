<%@page import="com.progra4.Seguros.presentation.client.account.Model"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    
    <% Model model= (Model) request.getAttribute("model"); %>
    
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@ include file="/presentation/Head.jsp" %>
        <title>Seguros - <%= model.getCurrent().getName() %></title>
    </head>
    <body>
        <%@ include file="/presentation/Header.jsp" %>
        <div class="register">
            <div class="row">
                <div class="col-md-4 offset-md-4 mx-auto">
                    <form name="form" action="presentation/client/account/update" method="post" >
                        <img src="images/Logotipo.png" class="login">
                        <h2 style="text-align: center">Modificar Datos</h2>
                        <div class="form-group mb-3 d-flex justify-content-between">
                            <input type="text" placeholder="ID: <%= model.getCurrent().getId() %>" class="form-control" id="id" name="id" disabled>
                            <input type="password" placeholder="Contraseña" class="form-control" id="id" name="clave" value="<%= model.getCurrent().getPassword() %>" required>
                        </div>
                        <div class="form-group mb-3">
                            <input type="text" placeholder="Nombre" class="form-control" id="name" name="nombre" value="<%= model.getCurrent().getName() %>" required>
                        </div>
                        <div class="form-group mb-3">
                            <input type="text" placeholder="Teléfono" class="form-control" id="phone" name="telefono" value="<%= model.getCurrent().getCellphone() %>" required>
                        </div>
                        <div class="form-group mb-3">
                            <input type="email" placeholder="Correo electrónico" class="form-control" id="mail" name="correo" value="<%= model.getCurrent().getEmail() %>" required>
                        </div>
                        <div class="form-group mb-3">
                            <input type="text" placeholder="Número de tarjeta" class="form-control" id="card" name="tarjeta" value="<%= model.getCurrent().getCardNumber() %>" maxlength="16" required>
                        </div>
                        <button class="btn btn-primary">Ingresar</button>
                    </form>
                </div>
            </div>
        </div>
        <%@ include file="/presentation/Footer.jsp" %>
    </body>
</html>