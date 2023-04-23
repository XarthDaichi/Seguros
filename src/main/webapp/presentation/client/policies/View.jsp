<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/presentation/Head.jsp" %>
        <title>Polizas</title> 
    </head>
    <body>
        <%@ include file="/presentation/Header.jsp" %>
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
                        <tr>
                            <td>1</td>
                            <td>Mark</td>
                            <td>Otto</td>
                            <td>@mdo</td>
                        </tr>
                        <tr>
                            <td>2</td>
                            <td>Jacob</td>
                            <td>Thornton</td>
                            <td>@fat</td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
        <%@ include file="/presentation/Footer.jsp" %>
    </body>
</html>