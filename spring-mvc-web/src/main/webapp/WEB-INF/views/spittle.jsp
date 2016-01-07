<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<%@ page isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<title>Spittle</title>
</head>

<body>
	<div class="spittleView">
		<div class="spittleMessage">
			<c:out value="${one.getMessage()}" />
		</div>
		<div>
			<span class="spittleId"><c:out value="${one.id}" /></span>
		</div>
	</div>
</body>
</html>