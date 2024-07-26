<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="jakarta.tags.core" prefix="c" %>
    <%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
welcome: admin

<sec:authorize access="isAuthenticated()">
<p> Log-In</p>
</sec:authorize>

<sec:authorize access="!isAuthenticated()">
<p>Log-Out</p>
</sec:authorize>

USER ID : <sec:authentication property="name"/><br/>

<c:url value="/logout" var="logoutUrl" />
<a href="${logoutUrl }">Log Out</a> <br/>
</body>
</html>