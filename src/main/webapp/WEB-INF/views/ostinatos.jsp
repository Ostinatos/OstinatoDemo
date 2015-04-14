<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding ="ISO-8859-1"%>

<html>
<head>
<title>Recent Ostinatos</title>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
</head>
<body>
	 <!--<h1>Recent Ostinatos... <c:out value="${testAttr}"/></h1> -->
	<h1>Recent Ostinatos... ${testAttr}</h1>
	 
	<c:forEach items="${ostinatoList}" var="ostinato">
		<li class="ostianto_item">
			<div class="ostinato_message">
				${ostinato.message}
			</div>
			<span>${ostinato.timestamp }</span>
		</li>
	</c:forEach> 
</body>
</html>