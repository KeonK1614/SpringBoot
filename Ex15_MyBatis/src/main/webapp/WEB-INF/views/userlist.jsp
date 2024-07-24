<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	out.println("MyBatis: Hello World!");
%>

<c:forEach var="dto" items="${users }">
	${dto.id } / ${dto.name }<br>
</c:forEach>
</body>
</html>