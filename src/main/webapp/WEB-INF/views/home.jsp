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
	<a href="<c:url value=""><c:param name="action" value="history" /></c:url>">History</a><br />
	<h1>${page.title}</h1>

	<div class="page_view">
		<%
		Page page1 = (Page) pageContext.getRequest().getAttribute("page");    
		pageContext.setAttribute("contents", new PegDownProcessor().markdownToHtml((page1).getContents()));
		%>
		 ${contents} 
	</div>
	<sec:authorize ifAllGranted="ROLE_USER">
	<form id="edit_form" action="<c:url value="/write" />" method="post">
		<input type="hidden" name="title" value="${page.title}" />
		<input id="contents_edit" type="textarea" class="page_edit" name="contents" value="${page.contents}" />
		<a href="#" class="page_edit" id="save">save</a>
	</form>
	</sec:authorize>
	<a href="#" class="page_view" id="edit">edit</a>
	<sec:authorize ifNotGranted="ROLE_USER">
		<a href="<c:url value="/login" />">Login</a>
		<a href="<c:url value="/signup" />">Sign Up</a>
	 </sec:authorize>	
	 
	<sec:authorize url="/login">
		<a href="<c:url value="/logout" />">Logout</a>
		<a href="<c:url value="/signout"/>">Remove me</a>
	</sec:authorize>


	<script type="text/javascript"
	        src="http://code.jquery.com/jquery-latest.js"></script>
	<script type="text/javascript">
		$(document).ready(function () {
			//Do all things using jquery in this function
			$("#signup").hide();
			$(".page_edit").hide();
			$("#edit").click(function () {
				$(".page_view").hide();
				$(".page_edit").show();
				return false;
			});
			
			$("#save").click(function () {
				$("#edit_form").submit();
				
				return false;
			});
		});
	</script>

</body>
</html>
