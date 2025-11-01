<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Listado de empleados</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/app.css">
</head>
<body>
<header>
    <h1>Gestor de Nóminas</h1>
    <nav>
        <ul>
            <li>
                <form action="${pageContext.request.contextPath}/empleados" method="get">
                    <button type="submit">Ver empleados</button>
                </form>
            </li>
            <li>
                <a href="${pageContext.request.contextPath}/WEB-INF/views/sueldoEmpleadoDni.jsp">
                    <button>Mostrar salario de un empleado</button>
                </a>
            </li>
            <li>
                <a href="${pageContext.request.contextPath}/WEB-INF/views/modificarEmpleados.jsp">
                    <button>Modificar empleado</button>
                </a>
            </li>
        </ul>
    </nav>
</header>

<main>
    <h2>Empleados</h2>

    <section class="tabla-empleados">
        <c:choose>
            <c:when test="${empty empleadosRecogidos}">
                <p class="sin-empleados">No hay empleados.</p>
            </c:when>
            <c:otherwise>
                <table>
                    <thead>
                    <tr>
                        <th>DNI</th><th>Nombre</th><th>Sexo</th><th>Categoría</th><th>Años</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="e" items="${empleadosRecogidos}">
                        <tr>
                            <td>${e.dni}</td>
                            <td>${e.nombre}</td>
                            <td>${e.sexo}</td>
                            <td>${e.categoria}</td>
                            <td>${e.anyos}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </c:otherwise>
        </c:choose>
    </section>
</main>

<p class="volver"><a href="${pageContext.request.contextPath}/">Volver</a></p>
</body>
</html>
