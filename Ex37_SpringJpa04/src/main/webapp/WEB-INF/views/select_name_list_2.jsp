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
	out.println("Spring JPA #04 - @Query : Name Like Paging");
%>
<br/><p>

총 글의 갯수 : ${totalElements } <br>
총 페이지 : ${totalPages } <br>
Size : ${Size } <br>
pageNumber : ${pageNumber } <br>
numberOfElements : ${numberOfElements } <br>
<hr>

<c:forEach items="${members}" var="member">
아이디 : ${member.id}<br/>
이름 : ${member.name}<br/>
이메일 : ${member.email}
<hr>
</c:forEach>

</body>
</html>