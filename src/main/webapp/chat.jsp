<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Chat</title>
</head>
<body>
    <H2>Chat as ${name}</H2>
    <form action="${pageContext.request.contextPath}/logout" method="POST">
        <input type="submit" value="Exit" />
    </form>

    <ul>
        <c:forEach var="message" items="${messages}">
            <li><c:out value="${message.datetime}" /> - <c:out value="${message.user}" />: <c:out value="${message.message}" /></li>
        </c:forEach>
    </ul>

    <c:if test="${!blockMessage.contains(name)}">
        <form action="${pageContext.request.contextPath}/message" method="POST">
            Message: <input name="message" />
            <input type="submit" value="Send" />
        </form>
    </c:if>
</body>
</html>