<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="partials/header.jsp">
    <jsp:param name="title" value="Flaga premium" />
</jsp:include>

<form class="form" method="post">
    <div>
        <label for="username">Nazwa użytkownika</label>
        <input type="text" name="username" id="username">
    </div>

    <button class="button" type="submit">Zmień flagę premium</button>
</form>

<jsp:include page="partials/footer.jsp" />