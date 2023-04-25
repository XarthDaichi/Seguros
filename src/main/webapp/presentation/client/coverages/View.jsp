<%-- 
    Document   : View
    Created on : Apr 24, 2023, 9:13:35 PM
    Author     : lmont
--%>
<%@page import="com.progra4.Seguros.presentation.client.coverages.Model"%>
<%@page import="com.progra4.Seguros.logic.*"%>
<%@page import="java.util.List" %>
<%@page import="java.util.ArrayList" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <% Model model= (Model) request.getAttribute("model"); %>
        <% Service service = Service.instance(); %>
        
        <%@ include file="/presentation/Head.jsp" %>
        <title>Seguros</title>
    </head>
    <body>
        <%@ include file="/presentation/Header.jsp" %>
        <div class="registerCoverages">
            <div class="row">
                <div class="col-md-4 offset-md-4 mx-auto">
                    <form name="form" action="presentation/client/policies/show" method="post" >
                        <label for="checkbox-list">Coberturas:</label>
                        <ul id="checkbox-list">
                            <% for (Category cat : service.selectAllCategories()) { %>
                                <% for (Rule cov : cat.getCoverages()) { %>
                                    <li>
                                      <input type="checkbox" name="coverageId<%=cov.getId()%>" value="<%=cov.getId()%>">
                                      <label for="<%=cov.getId()%>"><%=cov.getDescription()%></label>
                                    </li>
                                <% } %>
                            <% } %>
                        </ul>
                    </form>
                </div>
            </div>
        </div>
        <%@ include file="/presentation/Footer.jsp" %>
    </body>
</html>
