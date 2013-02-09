<%@page import="org.zeropage.simplewiki.model.Page" %>
<%@page import="org.pegdown.PegDownProcessor" %>
<%@page import="org.zeropage.simplewiki.SimpleRenderer" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags"
           prefix="sec" %>
<%@ page session="false" contentType="text/html; charset=UTF-8" %>
<html>
<head>
	<meta http-equiv="content-type" content="text/html; charset=utf-8"/>
	<title>Simple Wiki Home</title>
</head>
<body>
	<form action="<c:url value="/signup" />" method="POST">
		<label for="id">ID</label> <input type="text" id="id" name="id"/><br/>
		<label for="password">비밀번호</label> <input type="password"
		                                          id="password" name="password"/><br/> <label for="password">E-Mail</label>
		<input type="email" id="email" name="email"/><br/>
		<input type="submit" value="Join"/>
	</form>
</body>
</html>
