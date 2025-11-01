<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <section class="buscar">
        <h2>Busca por cualquiera de los siguientes campos:</h2>
        <div class="formulario">
            <form action="/empresa/modificar" method="get">
                <p class="dni">
                    <label for="dni">DNI: </label>
                    <input id="dni" type="search" name="dni" placeholder="12345678A" maxlength="9">
                </p>
                <p class="nombre">
                    <label for="nombre">Nombre: </label>
                    <input id="nombre" type="search" name="nombre">
                </p>
                <p class="sexo">
                    <label for="sexo">Sexo: </label>
                    <select name="sexo" id="sexo">
                        <option value="null"></option>
                        <option value="M">Masculino</option>
                        <option value="F">Femenino</option>
                    </select>
                </p>
                <p class="categoria">
                    <label for="categoria">Categoría: </label>
                    <select name="categoria" id="categoria">
                        <option value="null"></option>
                        <option value="1">1</option>
                        <option value="2">2</option>
                        <option value="3">3</option>
                        <option value="4">4</option>
                        <option value="5">5</option>
                        <option value="6">6</option>
                        <option value="7">7</option>
                        <option value="8">8</option>
                        <option value="9">9</option>
                        <option value="10">10</option>
                    </select>
                </p>
                <p class="anyos">
                    <label for="anyos">Años trabajados: </label>
                    <input id="anyos" type="search" name="anyos" pattern="[0-9]+" inputmode="numeric" placeholder="0, 1, 2...">
                </p>
                <p class="btn-buscar">
                    <input type="submit" value="Buscar">
                </p>
            </form>
        </div>
    </section>

    <section class="empleados-encontrados">
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

</body>
</html>
