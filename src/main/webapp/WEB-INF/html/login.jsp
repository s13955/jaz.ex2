<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="partials/header.jsp">
    <jsp:param name="title" value="Logowanie" />
</jsp:include>

    <form class="form" method="post">
        <div>
            <label for="username">Nazwa użytkownika</label>
            <input type="text" name="username" id="username" autocomplete="off">
        </div>
        <div>
            <label for="password">Hasło</label>
            <input type="password" name="password" id="password" autocomplete="off">
        </div>

        <button class="button" type="submit">Zaloguj się</button>
    </form>

<jsp:include page="partials/footer.jsp" />