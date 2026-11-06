<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Nuevo entrenador</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-4">
    <h1 class="mb-3">Nuevo entrenador</h1>
    <form method="post" action="<%=request.getContextPath()%>/inicio?action=crear">
        <div class="mb-3">
            <label class="form-label">Nombre</label>
            <input class="form-control" type="text" name="nombre" maxlength="100" required>
        </div>
        <div class="mb-3">
            <label class="form-label">Especialidad</label>
            <input class="form-control" type="text" name="especialidad" maxlength="100" required>
        </div>
        <div class="mb-3">
            <label class="form-label">Teléfono</label>
            <input class="form-control" type="text" name="telefono" maxlength="15" required>
        </div>
        <a class="btn btn-secondary" href="<%=request.getContextPath()%>/inicio">Cancelar</a>
        <button class="btn btn-primary" type="submit">Guardar</button>
    </form>
</div>
</body>
</html>
