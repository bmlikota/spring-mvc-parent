<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ page session="false"%>
<html>
<head>
<title>Spittr</title>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/style.css" />">
</head>
<body>
	<h1><s:message code="spittr.welcome" /></h1>
	<a href="<c:url value="/spittles" />">Spittles</a> |
	<a href="<c:url value="/spittr/register" />">Register</a>
	<br/>
	<a href="<s:url value="/spittr/register" />">s:url :: Register</a>
</body>
</html>