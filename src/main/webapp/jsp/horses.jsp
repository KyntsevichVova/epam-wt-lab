<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
    <head>
        <title>
            Horses
        </title>
    </head>
    <body>
        <table>
            <c:forEach items="${horses}" var="horse">
                <tr>
                    <td>${horse.id}</td>
                    <td>${horse.name}</td>
                    <td>${horse.wins}</td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>