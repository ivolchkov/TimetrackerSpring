<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ivolchkov
  Date: 11/7/19
  Time: 6:27 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Header</title>
</head>
<body>
<nav class="navbar navbar-dark sticky-top bg-dark flex-md-nowrap p-0">
    <a class="navbar-brand col-sm-3 col-md-2 mr-0" href="index">Timetracker</a>
    <ul class="navbar-nav px-3">
        <li class="nav-item text-nowrap">
            <form action="signOut" method="get">
                <button type="submit" class="btn btn-primary btn-block"><fmt:message key="signOut.exit"/></button>
            </form>
        </li>
    </ul>
</nav>
</body>
</html>
