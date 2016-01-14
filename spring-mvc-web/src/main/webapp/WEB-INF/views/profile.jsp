<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<%@ page isELIgnored="false" %>
<html>
<head>
<title>Spittr</title>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/style.css" />">
</head>
<body>
	<h1>Your Profile</h1>
	<c:out value="${spittr.username}" />
	<br/>
	<c:out value="${spittr.firstName}" />
	<br/>
	<c:out value="${spittr.lastName}" />
</body>
</html>