<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="../partials/header.jsp">
    <jsp:param name="title" value="Debug" />
</jsp:include>

    <article class="text">
        <c:if test="${empty user}">
            Anonymous user
        </c:if>

        <c:if test="${not empty user}">
            Username: ${user.username}<br>
            Password: ${user.password}<br>
            E-mail: ${user.email}<br>
            Role: ${user.role}<br>
        </c:if>
    </article>

<jsp:include page="../partials/footer.jsp" />