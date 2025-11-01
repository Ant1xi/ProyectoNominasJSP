<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Empresa · Nóminas</title>
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
    <section class="formularioSalarioDni">
        <form action="/empresa/salario" method="get">
            <label for="dni">DNI:</label>
            <input type="text" id="dni" name="dni"
                   value="<c:out value='${dni}'/>"
                   style="text-transform:uppercase"
                   placeholder="12345678A"
                   pattern="[0-9]{7,8}[A-Za-z]" required><br><br>

            <label for="salario">SALARIO:</label>
            <input type="text" id="salario" value="<c:out value='${sueldo}'/>" readonly><br><br>

            <c:if test="${not empty errores}">
                <p class="errores">
                    <c:forEach var="e" items="${errores}">
                        <c:out value="${e}"/><br/>
                    </c:forEach>
                </p>
            </c:if>

            <c:if test="${not empty aviso}">
                <p class="aviso">${aviso}</p>
            </c:if>

            <input type="submit" value="Enviar">
        </form>
    </section>
</main>
</body>
</html>
