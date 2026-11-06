<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.example.lab5.beans.Entrenador" %>
<% Entrenador entrenador = (Entrenador) request.getAttribute("entrenador"); %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Editar entrenador</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-4">
    <h1 class="mb-3">Editar entrenador #<%=entrenador.getIdEntrenador()%></h1>
    <form method="post" action="<%=request.getContextPath()%>/inicio?action=actualizar">
        <!-- El ID no se edita: va oculto para saber a quién actualizar -->
        <input type="hidden" name="idEntrenador" value="<%=entrenador.getIdEntrenador()%>">
        <div class="mb-3">
            <label class="form-label">Nombre</label>
            <input class="form-control" type="text" name="nombre" maxlength="100"
                   value="<%=entrenador.getNombre()%>" required>
        </div>
        <div class="mb-3">
            <label class="form-label">Especialidad</label>
            <input class="form-control" type="text" name="especialidad" maxlength="100"
                   value="<%=entrenador.getEspecialidad()%>" required>
        </div>
        <div class="mb-3">
            <label class="form-label">Teléfono</label>
            <input class="form-control" type="text" name="telefono" maxlength="15"
                   value="<%=entrenador.getTelefono()%>" required>
        </div>
        <a class="btn btn-secondary" href="<%=request.getContextPath()%>/inicio">Cancelar</a>
        <button class="btn btn-primary" type="submit">Actualizar</button>
    </form>
</div>
</body>
</html>
