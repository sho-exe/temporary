<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%-- Import JSTL core for checking if we are editing or creating --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>
        <c:choose>
            <c:when test="${student != null}">Edit Student</c:when>
            <c:otherwise>Add New Student</c:otherwise>
        </c:choose>
    </title>
    <style>
        .form-group { margin-bottom: 15px; }
        label { display: inline-block; width: 100px; font-weight: bold; }
        input[type="text"], input[type="number"] { width: 200px; padding: 5px; }
        button { padding: 6px 15px; cursor: pointer; }
    </style>
</head>
<body>

    <h2>
        <c:choose>
            <c:when test="${student != null}">Edit Existing student</c:when>
            <c:otherwise>Add New student</c:otherwise>
        </c:choose>
    </h2>

    <form action="students" method="post">
        
        <input type="hidden" name="action" value="${student != null ? 'update' : 'insert'}" />
        
        <c:if test="${student != null}">
            <input type="hidden" name="id" value="<c:out value='${student.id}' />" />
        </c:if>

        <div class="form-group">
            <label for="name">Name:</label>
            <input type="text" id="name" name="name" 
                   value="<c:out value='${student.name}' />" required />
        </div>

               <div class="form-group">
            <label for="age">age</label>
            <input type="text" id="age" name="age" 
                   value="<c:out value='${student.age}' />" required />
        </div>

        <div class="form-group">
            <button type="submit">
                <c:choose>
                    <c:when test="${student != null}">Save Changes</c:when>
                    <c:otherwise>Add student</c:otherwise>
                </c:choose>
            </button>
            <a href="products">Cancel</a>
        </div>
        
    </form>

</body>
</html>