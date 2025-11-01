<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!doctype html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Empresa · Nóminas</title>
    <link rel="stylesheet" href="css/app.css">
</head>
<body>
<header>
    <h1>Gestor de Nóminas</h1>
    <nav>
        <ul>
            <li><a class="btn" href="${pageContext.request.contextPath}/empleados">Ver empleados</a></li>
            <li><a class="btn" href="${pageContext.request.contextPath}/nomina/consultar">Mostrar salario de un empleado</a></li>
            <li><a class="btn" href="${pageContext.request.contextPath}/empleados/buscar">Modificar empleado</a></li>
        </ul>
    </nav>
</header>
</body>
</html>
