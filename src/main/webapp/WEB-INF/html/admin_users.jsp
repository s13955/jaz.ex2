<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="partials/header.jsp">
    <jsp:param name="title" value="Lista użytkowników" />
</jsp:include>

<section class="text">
    <table>
        <thead>
            <tr>
                <th width="200">Username</th>
                <th width="300">E-mail</th>
                <th width="200">Role</th>
                <th width="200">Konto</th>
            </tr>
        </thead>
        <tbody>
        <c:forEach var="user" items="${users}">
            <tr>
                <td>${user.username}</td>
                <td class="text-right"><a href="mailto:${user.email}">${user.email}</a></td>
                <td class="text-right">${user.role}</td>
                <td class="text-right"><c:if test="${user.premium}">premium</c:if></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>






</section>

<jsp:include page="partials/footer.jsp" />