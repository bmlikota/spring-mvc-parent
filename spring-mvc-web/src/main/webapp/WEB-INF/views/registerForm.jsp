<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false" %>
<html>
<head>
<link rel="stylesheet" type="text/css" href="<c:url value="/static/css/style.css" />">
<title>Spittr</title>
</head>
<body>
	<h1>Register</h1>
	<sf:form method="POST" commandName="spittr">
		<sf:errors path="*" element="div" cssClass="errors" />
		First Name: <sf:input path="firstName" /><br/>
		Last Name: <sf:input path="lastName" /><br/>
		Username: <sf:input path="username" /><br/>
		Password: <sf:password path="password" /><br/>
		<input type="submit" value="Register" />
	</sf:form>
</body>
</html>