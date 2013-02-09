<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" contentType="text/html; charset=UTF-8" %>
<html>
<head>
	<meta http-equiv="content-type" content="text/html; charset=utf-8"/>
	<title>${page.title} - History</title>
</head>
<body>
	<table>
		<th>
			<td>Revision</td>
			<td>Date</td>
			<td>Contributor</td>
		</th>
		<c:forEach var="revision" items="${page.history}">
			<tr>
				<td>${revision.number}</td>
				<td>${revision.date}</td>
				<td>${revision.name}</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>
