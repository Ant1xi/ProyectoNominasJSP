<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Empresa · Nóminas</title>
    <link rel="stylesheet" href="<c:url value='/css/styles.css'/>">
</head>
<body>
<%@ include file="/WEB-INF/views/fragmentos/header.jspf" %>

<section id="consultar-salario" class="card">
    <h2>Consultar salario</h2>

    <form action="<c:url value='/empleados/consultar'/>" method="post"
          novalidate>
        <div class="form-grid">

            <div class="form-row">
                <label for="dni">DNI</label>
                <input
                        type="text"
                        id="dni"
                        name="dni"
                        value="<c:out value='${dni}'/>"
                        placeholder="12345678A"
                        pattern="^[0-9]{7,8}[A-Z]$"
                        class="input"
                        autocomplete="off" />
                <small class="help">7–8 dígitos y letra
                    MAYÚSCULA</small>
            </div>

            <div class="form-row">
                <label for="salario">Salario</label>
                <input
                        type="text"
                        id="salario"
                        value="<c:out value='${sueldo}'/>"
                        class="input readonly"
                        readonly />
            </div>

            <div class="form-actions">
                <button type="submit" class="btn">Consultar</button>
                <a href="javascript:history.back()"
                   class="btn btn-secondary">Volver</a>
            </div>
        </div>

        <c:if test="${not empty errores}">
            <ul class="errors">
                <c:forEach var="e" items="${errores}">
                    <li><c:out value="${e}" /></li>
                </c:forEach>
            </ul>
        </c:if>

        <c:if test="${not empty aviso}">
            <p class="notice"><c:out value='${aviso}' /></p>
        </c:if>
    </form>
</section>

</body>
</html>