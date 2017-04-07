<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="pl">
<head>
    <meta charset="utf-8">
    <title><c:if test="${not empty param.title}">${param.title} - </c:if> Zadanie 2</title>
    <link rel="stylesheet" href="/css/app.css">
</head>
<body>

    <header class="site-header">
        <div class="vertical">
            <h1 class="page-title"><a href="/">Zadanie 2</a></h1>
        </div>

        <jsp:include page="nav.jsp" />
    </header>

    <main class="content" role="main">