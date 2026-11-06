<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.example.lab5.beans.Entrenador" %>
<%@ page import="com.example.lab5.beans.Clase" %>
<%
    Entrenador entrenador = (Entrenador) request.getAttribute("entrenador");
    ArrayList<Clase> listaClases = (ArrayList<Clase>) request.getAttribute("listaClases");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Detalle de entrenador</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-4">
    <h1 class="mb-1">Clases de <%=entrenador.getNombre()%></h1>
    <p class="text-muted">Especialidad: <%=entrenador.getEspecialidad()%> · Teléfono: <%=entrenador.getTelefono()%></p>

    <% if (listaClases.isEmpty()) { %>
    <div class="alert alert-info">Este entrenador no tiene clases asignadas.</div>
    <% } else { %>
    <table class="table table-striped">
        <thead class="table-dark">
        <tr><th>ID</th><th>Clase</th><th>Cupos disponibles</th><th>Precio (S/)</th></tr>
        </thead>
        <tbody>
        <% for (Clase c : listaClases) { %>
        <tr>
            <td><%=c.getIdClase()%></td>
            <td><%=c.getNombreClase()%></td>
            <td><%=c.getCuposDisponibles()%></td>
            <td><%=c.getPrecio()%></td>
        </tr>
        <% } %>
        </tbody>
    </table>
    <% } %>
    <a class="btn btn-secondary" href="<%=request.getContextPath()%>/inicio">Regresar</a>
</div>
</body>
</html>
