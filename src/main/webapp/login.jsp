<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags"
           prefix="sec" %>
<%@ page session="false" contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <meta http-equiv="content-type" content="text/html; charset=utf-8"/>
    <title>Simple Wiki Login</title>
</head>
<body>
<form method="POST" action="<c:url value="/j_spring_security_check"/>">
    <table>
        <tr>
            <td align="right">userName</td>
            <td><input type="text" name="j_username"></td>
        </tr>
        <tr>
            <td align="right">Password</td>
            <td><input type="text" name="j_password"></td>
        </tr>
        <tr>
            <td align="right">Remember me</td>
            <td><input type="checkbox" name="_spring_security_remember_me"></td>
        </tr>

        <tr>
            <td colspan="2" align="right">
                <input type="submit" value="Login">
                <input type="reset" value="Reset">
                <a href="<c:url value="/signup" />">Sign up</a>
            </td>
        </tr>
    </table>
</form>

</body>
</html>