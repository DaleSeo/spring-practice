<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Log In</title>
</head>
<body>

<sec:authorize access="isAnonymous()">
    <c:url value="/login" var="loginUrl"/>
    <form action="${loginUrl}" method="post">
        <c:if test="${param.error != null}">
            <p>
                Invalid username and password.
            </p>
        </c:if>
        <c:if test="${param.logout != null}">
            <p>
                You have been logged out.
            </p>
        </c:if>
        <p>
            <label for="username">Username</label>
            <input type="text" id="username" name="username"/>
        </p>
        <p>
            <label for="password">Password</label>
            <input type="password" id="password" name="password"/>
        </p>
        <sec:csrfInput />
        <button type="submit" class="btn">Log in</button>
    </form>
</sec:authorize>

<sec:authorize access="isAuthenticated()">
    <ul>
        <li>Username : <sec:authentication property="principal.username" /></li>
        <li>Password : <sec:authentication property="principal.password" /></li>
        <li>Authorities : <sec:authentication property="principal.authorities" /></li>
    </ul>
    <c:url value="/logout" var="logoutUrl"/>
    <form action="${logoutUrl}" method="post">
        <sec:csrfInput />
        <input type="submit" value="Logout"/>
    </form>
</sec:authorize>

</body>
</html>
