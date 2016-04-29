<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!-- The HTML 4.01 Transitional DOCTYPE declaration-->
<!-- above set at the top of the file will set     -->
<!-- the browser's rendering engine into           -->
<!-- "Quirks Mode". Replacing this declaration     -->
<!-- with a "Standards Mode" doctype is supported, -->
<!-- but may lead to some differences in layout.   -->

<html>
  <head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <title>Hello App Engine</title>
  </head>

  <body>
	<p>Sistema de gestión de TFGs</p>
	<c:if test="${user != null && user != \"\"}">
		<c:out value="Hola ${user}" />
		<p>Puedes pulsar el siguiente enlace para salir
		<a href="<c:url value="${url}"/>"><c:out value="${urlLinktext}"/></a></p>
	</c:if>
	<c:if test="${user == \"\"}">
		<p>Test</p>
		<p>Puedes pulsar en el siguiente enlace para identificarte
		<a href="<c:url value="${url}"/>"><c:out value="${urlLinktext}"/></a>	
	</c:if>
	<c:if test="${not empty user and empty tfg and empty tfgs}">
		<p>Alumno: esta es una solicitud de TFG</p>
		<form action="/nuevoTFG" method="post" acceptcharset="utf-8">
			<input type="text" name="titulo" id="titulo" maxLength="255"
				size="20" required placeholder="Titulo" />
			<input type="text"
				name="resumen" id="resumen" maxLength="255" required size="20"
				placeholder="resumen" />
			<input type="text" name="tutor" id="tutor"
				maxLength="255" required size="20" placeholder="tutor" />
			<input type="submit" value="Solicitud" />
		</form>
	</c:if>
	<c:forEach items="${tfgs}" var="tfg">
		<tr>
			<td><c:out value="${tfg.autor}" /></td>
			<td><c:out value="${tfg.titulo}" /></td>
			<td><c:out value="${tfg.resumen}" /></td>
			<td><c:out value="${tfg.tutor}" /></td>
			<td><c:out value="${tfg.estado}" /></td>
		</tr>
	</c:forEach>
  </body>
</html>
