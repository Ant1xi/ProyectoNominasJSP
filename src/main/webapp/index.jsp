<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!doctype html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Empresa · Nóminas</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/app.css">
    <link rel="stylesheet" href="css/app.css">
</head>
<body>
<header>
    <h1>Gestor de Nóminas</h1>
    <nav>
        <ul>
            <li>
                <form action="empleados" method="get">
                    <button type="submit">Ver empleados</button>
                </form>
            </li>
            <li>
                <a href="views/sueldoEmpleadoDni.jsp">
                    <button>Mostrar salario de un empleado</button>
                </a>
            </li>
            <li>
                <a href="views/modificarEmpleados.jsp">
                    <button>Modificar empleado</button>
                </a>
            </li>
        </ul>
    </nav>
</header>
</body>
</html>
