<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="jakarta.tags.core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	out.println("MyBatis 사용하기 : Hello World");
%>
<br/>

<c:forEach var="dto" items="${employees}">
	${dto.ename} / ${dto.deptno } /${dto.dname }<br/>
</c:forEach>
</body>
</html>