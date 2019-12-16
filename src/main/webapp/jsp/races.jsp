<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
    <head>
        <title>
            Races
        </title>
    </head>
    <body>
        <table>
            <c:forEach items="${races}" var="race">
                <tr>
                    <td>${race.id}</td>
                    <td>${race.date}</td>
                    <td>${race.winnerId}</td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>