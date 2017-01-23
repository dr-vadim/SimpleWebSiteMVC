<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<html>
<head>
    <head>
        <title>${Title}</title>
        <link rel="stylesheet" type="text/css" href="/static/bootstrap/3.3.7/css/bootstrap.min.css" />
        <link rel="stylesheet" type="text/css" href="/static/bootstrap/3.3.7/css/bootstrap-theme.min.css" />
        <link rel="stylesheet/less" type="text/css" href="/static/css/style.less" />
        <script type="text/javascript" src="/static/less/2.5.3/less.min.js"></script>
        <script type="text/javascript" src="/static/jquery/3.1.1/jquery.js"></script>
        <script type="text/javascript" src="/static/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <script type="text/javascript" src="/static/js/script.js"></script>
    </head>
</head>
<body>
<div class="container main-container">
    <f:form method="post" action="user" modelAttribute="userForm">
        <f:input path="id" type="hidden" />
        <div class="form-group">
            <label for="inputName" class="col-sm-2 control-label">Модель:</label>
            <div class="col-sm-10">
                <f:input path="model" id="inputName" />
            </div>
        </div>
        <div class="form-group">
            <label for="inputAge" class="col-sm-2 control-label">Имя:</label>
            <div class="col-sm-10">
                <f:input path="color" id="inputAge" />
            </div>
        </div>
        <f:input path="user_id" type="hidden" />
        <f:button type="submit" class="btn btn-primary btn-sm">Сохранить</f:button>
    </f:form>
</div>
</body>
</html>
