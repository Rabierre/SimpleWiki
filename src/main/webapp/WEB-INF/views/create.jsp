<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" contentType="text/html; charset=UTF-8" %>
<html>
<head>
	<meta http-equiv="content-type" content="text/html; charset=utf-8"/>
	<title>Create Page</title>
</head>
<body>
	<a href="<c:url value="/create"><c:param name="title" value="${title}" /></c:url>" id="create"><b>${title}</b> 페이지 만들기</a>

</body>
</html>
