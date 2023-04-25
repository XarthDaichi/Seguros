<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@page import="com.progra4.Seguros.logic.*"%>
<% User user= (User) session.getAttribute("user");%>

<!DOCTYPE html>

<header class="header">
    <div class="logo">
        <img src="images/Logotipo.png">
        Seguros
    </div>
    <ul class="nav">
        <% if (user==null){%>
            <li><a href="presentation/login/show">Login</a></li>
        <%}%>
        <% if ((user!=null)&&(!user.getAdministrator())){%>
            <li><a href="/Seguros/presentation/client/policies/show">Pólizas</a></li>
            <li><a href="/Seguros/presentation/client/account/show">Cuenta: <%= user.getName() %></a></li>
            <li><a href="/Seguros/presentation/login/logout">Cerrar Sesión</a></li>
        <%} else {%>
            <li><a href="/Seguros/presentation/admin/policies/show">Pólizas</a></li>
            <li><a href="/Seguros/presentation/login/logout">Cerrar Sesión</a></li>
        <% } %>
    </ul>
</header>
