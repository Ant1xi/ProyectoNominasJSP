<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!doctype html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Empresa · Modificar empleados</title>
    <link rel="stylesheet" href="<c:url value='/css/styles.css'/>">
</head>
<body>
<%@ include file="/WEB-INF/views/fragmentos/header.jspf" %>

<section class="buscar-modificar">

    <!-- Buscador -->
    <section class="buscar">
        <h2>Busca por cualquiera de los siguientes campos</h2>
        <div class="formulario">
            <form action="<c:url value='/empleados/buscarByDato'/>" method="post">
                <p class="dni">
                    <label for="dni">DNI</label>
                    <input id="dni" type="search" name="dni" placeholder="12345678A" maxlength="9">
                </p>
                <p class="nombre">
                    <label for="nombre">Nombre</label>
                    <input id="nombre" type="search" name="nombre">
                </p>
                <p class="sexo">
                    <label for="sexo">Sexo</label>
                    <select name="sexo" id="sexo">
                        <option value="null"></option>
                        <option value="M">Masculino</option>
                        <option value="F">Femenino</option>
                    </select>
                </p>
                <p class="categoria">
                    <label for="categoria">Categoría</label>
                    <select name="categoria" id="categoria">
                        <option value="null"></option>
                        <c:forEach var="i" begin="1" end="10">
                            <option value="${i}">${i}</option>
                        </c:forEach>
                    </select>
                </p>
                <p class="anyos">
                    <label for="anyos">Años trabajados</label>
                    <input id="anyos" type="search" name="anyos" pattern="[0-9]+" inputmode="numeric" placeholder="0, 1, 2...">
                </p>
                <p class="btn-buscar">
                    <input type="submit" value="Buscar">
                </p>
            </form>
        </div>
    </section>

    <!-- Resultados -->
    <section id="ver-empleados" class="panel">
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
                            <th>DNI</th>
                            <th>Nombre</th>
                            <th>Sexo</th>
                            <th>Categoría</th>
                            <th>Años</th>
                            <th></th>
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
                                <td>
                                    <!-- Al pulsar Modificar recargamos esta misma vista con el empleado seleccionado -->
                                    <form action="<c:url value='/empleados/editar'/>" method="get">
                                        <input type="hidden" name="dni" value="${e.dni}">
                                        <button type="submit">Modificar</button>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </c:otherwise>
            </c:choose>
        </section>
    </section>

    <!-- Edición del empleado seleccionado (en la misma página) -->
    <section class="empleado-seleccionado">
        <c:choose>
            <c:when test="${empleadoSeleccionado == null}">
                <p></p>
            </c:when>
            <c:otherwise>
                <h2>Editar empleado</h2>
                <form action="<c:url value='/empleados/guardar'/>" method="post" novalidate>
                    <div class="inputs">
                        <!-- DNI: readonly, se envía en el POST -->
                        <p>
                            <label for="dniRecogido">DNI</label>
                            <input
                                    type="text"
                                    id="dniRecogido"
                                    name="dni"
                                    value="<c:out value='${empleadoSeleccionado.dni}'/>"
                                    maxlength="9"
                                    style="text-transform:uppercase"
                                    readonly>
                        </p>

                        <p>
                            <label for="nombreRecogido">Nombre</label>
                            <input
                                    type="text"
                                    id="nombreRecogido"
                                    name="nombre"
                                    value="<c:out value='${empleadoSeleccionado.nombre}'/>"
                                    required
                                    maxlength="60"
                                    title="Solo letras y espacios">
                        </p>

                        <p>
                            <label for="sexoRecogido">Sexo</label>
                            <select id="sexoRecogido" name="sexo" required>
                                <option value="" disabled ${empty empleadoSeleccionado.sexo ? 'selected' : ''}>Seleccione</option>
                                <option value="M" ${fn:toUpperCase(empleadoSeleccionado.sexo) == 'M' ? 'selected' : ''}>M</option>
                                <option value="F" ${fn:toUpperCase(empleadoSeleccionado.sexo) == 'F' ? 'selected' : ''}>F</option>
                            </select>
                        </p>

                        <p>
                            <label for="catRecogida">Categoría</label>
                            <select id="catRecogida" name="categoria" required>
                                <c:forEach var="cat" begin="1" end="10">
                                    <option value="${cat}" ${empleadoSeleccionado.categoria == cat ? 'selected' : ''}>${cat}</option>
                                </c:forEach>
                            </select>
                        </p>

                        <p>
                            <label for="anyosRecogidos">Años trabajados</label>
                            <input
                                    type="number"
                                    id="anyosRecogidos"
                                    name="anyos"
                                    value="<c:out value='${empleadoSeleccionado.anyos}'/>"
                                    min="0" max="60" step="1" required>
                        </p>
                    </div>

                    <div class="form-actions">
                        <button type="submit">Guardar</button>
                        <a href="javascript:history.back()" class="btn btn-secondary">Cancelar</a>
                    </div>
                </form>

                <c:if test="${not empty errores}">
                    <ul class="errors">
                        <c:forEach var="err" items="${errores}">
                            <li><c:out value="${err}"/></li>
                        </c:forEach>
                    </ul>
                </c:if>
            </c:otherwise>
        </c:choose>
    </section>

</section>
</body>
</html>
