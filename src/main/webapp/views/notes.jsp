<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title></title>
</head>
<body>
<nav class="navbar navbar-light bg-light">
    <span class="navbar-brand mb-0 h1">Note</span>
    <a href='<c:url value="/logout" />'>Выйти</a>
</nav>

<div class="container mt-3" >
    <div class="row mt-2">
        <div class="col-md-5">
            <form method="post" action="notes" >
                <div class="form-group">
                    <label for="date">Дата</label>
                    <input type="text" name="date" class="form-control" id="date" placeholder="01.12.2020">
                </div>
                <div class="form-group">
                    <label for="text">Текст</label>
                    <textarea class="form-control" id="text" rows="3"></textarea>
                </div>
                <button type="submit" class="btn btn-success">Добавить</button>
            </form>
        </div>
        <div class="col-md">
            <c:if test="${requestScope.notes.size() != 0}">
                <c:forEach var="note" items="${requestScope.notes}" varStatus="status">
                    <div class="card mt-2">
                        <h5 class="card-header"> Задача <c:out value="${status.index+1}"/> </h5>
                        <div class="card-body">
                            <h5 class="card-title"><c:out value="${note.text}"/></h5>
                            <p class="card-text"><c:out value="${note.date}"/></p>
                            <a href="#" class="btn btn-primary">Выполнить</a>
                            <a href="#" class="btn btn-danger">Удалить</a>
                        </div>
                    </div>
                </c:forEach>
            </c:if>
            <c:if test="${requestScope.notes.size() == 0}">
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title">У вас пока нет задач(</h5>
                        <p class="card-text">Вы можите их добавить</p>
                    </div>
                </div>
            </c:if>
        </div>
    </div>
</div>
</body>
</html>
