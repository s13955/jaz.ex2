<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="../partials/header.jsp">
    <jsp:param name="title" value="Profil" />
</jsp:include>

    <article class="premium">
        <img src="/images/profle.png" alt="Profil" />

        <c:if test="${not empty user}">
            <h2><a href="mailto:${user.email}">${user.username}</a></h2>
            <c:if test="${user.premium}"><strong>konto premium</strong></c:if>
        </c:if>
    </article>

<jsp:include page="../partials/footer.jsp" />