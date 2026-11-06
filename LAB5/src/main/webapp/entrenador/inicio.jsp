<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.example.lab5.beans.Entrenador" %>
<% ArrayList<Entrenador> listaEntrenadores = (ArrayList<Entrenador>) request.getAttribute("listaEntrenadores"); %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>FitControl - Entrenadores</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-4">
    <div class="d-flex justify-content-between align-items-center border-bottom pb-2 mb-3">
        <h1>FitControl · Entrenadores</h1>
        <a class="btn btn-primary" href="<%=request.getContextPath()%>/inicio?action=formCrear">Nuevo entrenador</a>
    </div>

    <% if ("borrar".equals(request.getParameter("error"))) { %>
    <div class="alert alert-danger">
        No se pudo borrar el entrenador: tiene clases asignadas.
    </div>
    <% } %>

    <table class="table table-striped align-middle">
        <thead class="table-dark">
        <tr>
            <th>ID</th><th>Nombre</th><th>Especialidad</th><th>Teléfono</th><th>Acciones</th>
        </tr>
        </thead>
        <tbody>
        <% for (Entrenador e : listaEntrenadores) { %>
        <tr>
            <td><%=e.getIdEntrenador()%></td>
            <td><%=e.getNombre()%></td>
            <td><%=e.getEspecialidad()%></td>
            <td><%=e.getTelefono()%></td>
            <td>
                <a class="btn btn-success btn-sm"
                   href="<%=request.getContextPath()%>/inicio?action=editar&id=<%=e.getIdEntrenador()%>">Editar</a>
                <a class="btn btn-danger btn-sm"
                   onclick="return confirm('¿Borrar a <%=e.getNombre()%>?')"
                   href="<%=request.getContextPath()%>/inicio?action=borrar&id=<%=e.getIdEntrenador()%>">Borrar</a>
                <a class="btn btn-info btn-sm"
                   href="<%=request.getContextPath()%>/inicio?action=detalle&id=<%=e.getIdEntrenador()%>">Detalle</a>
            </td>
        </tr>
        <% } %>
        </tbody>
    </table>
</div>
</body>
</html>
