<%-- 
    Document   : list
    Created on : Jun 16, 2026, 1:16:03 AM
    Author     : sho
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Students List</h1>
        <a href="students?action=new">Add New Student</a>
<br/><br/>

<table>
    <tr>
        <th>ID</th><th>Name</th><th>Age</th><th>Actions</th>
    </tr>
    
    <c:forEach var="student" items="${students}">
        <tr>
            <td>${student.id}</td>
            <td>${student.name}</td>
            <td>${student.age}</td>
            <td>
                <a href="students?action=edit&id=${student.id}">Edit</a> 
                | 
                <a href="students?action=delete&id=${student.id}" 
                   onclick="return confirm('Are you sure?');">Delete</a>
            </td>
        </tr>
    </c:forEach>
</table>
    </body>
</html>

