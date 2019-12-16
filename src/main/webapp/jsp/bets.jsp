<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
    <head>
        <title>
            Bets
        </title>
    </head>
    <body>
        <table>
            <c:forEach items="${bets}" var="bet">
                <tr>
                    <td>${bet.id}</td>
                    <td>${bet.user}</td>
                    <td>${bet.raceId}</td>
                    <td>${bet.horseId}</td>
                    <td>${bet.sum}</td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>