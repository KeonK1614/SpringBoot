<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="jakarta.tags.core" prefix="c" %>
    <%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
    <%@ page import="com.study.springboot.oauth2.SessionUser" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
welcome: member

<sec:authorize access="isAuthenticated()">
<p> Log-In</p>
</sec:authorize>

<sec:authorize access="!isAuthenticated()">
<p>Log-Out</p>
</sec:authorize>

USER ID : <sec:authentication property="name"/><br/>
소유권한 : <sec:authentication property="authorities" /><br/>

<%
	request.setCharacterEncoding("UTF-8");
	
	SessionUser obj = (SessionUser) session.getAttribute("user");
	String sName = (String)obj.getName();
	String sEmail = (String)obj.getEmail();
	String sPicture = (String)obj.getPicture();
%>

<%= sName %><br>
<%= sEmail %><br>
<img src=<%= sPicture %>><br>

<a href="/logout">Log Out</a> <br/>

<script>
console.log("aaaa" + window.location.hash);
if (window.location.hash == '#_=_') {
	console.log("bbbb");
	
	history.replaceState
		? history.replaceState(null, null, window.location.href.split('#')[0])
				: window.location.hash = '';
}
</script>
</body>
</html>