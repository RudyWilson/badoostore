<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<body>
<h1>${message}</h1>
<table style="border: 1px solid; width: 500px; text-align:center">
    <thead style="background:#fcf">
    <tr>
        <th>Name</th>
        <th>Age</th>
        <th>Photo count</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${accounts}" var="account">
        <tr>
            <td><c:out value="${account.name}"/></td>
            <td><c:out value="${account.age}"/></td>
            <td><c:out value="${account.id}"/></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>